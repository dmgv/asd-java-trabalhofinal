package com.javaee.dmgv.projetofinal.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.javaee.dmgv.projetofinal.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.javaee.dmgv.projetofinal.config.EmailConfig;
import com.javaee.dmgv.projetofinal.domain.Acionista;
import com.javaee.dmgv.projetofinal.repositories.EmpresaRepository;
import com.javaee.dmgv.projetofinal.repositories.AcionistaRepository;
import com.javaee.dmgv.projetofinal.repositories.AcaoRepository;

@Service
public class AcaoServiceImpl implements AcaoService {

    @Autowired
    private AcaoRepository acaoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private AcionistaRepository acionistaRepository;

    @Autowired
    private MessageService messageService;

    @Override
    public List<Acao> findAll() {
        return acaoRepository.findAll();
    }

    @Override
    public Acao findById(String id) {
        return getStockById(id);
    }

    private Acao getStockById(String id) {
        Optional<Acao> stockOptional = acaoRepository.findById(id);

        if (!stockOptional.isPresent()) {
            throw new IllegalArgumentException("Acão não encontrada: " + id);
        }

        return stockOptional.get();
    }

    @Override
    public List<Acao> findByOwnerIdAndCurrentValue(String companyId, Double currentValue) {
        return acaoRepository.findByAcionistaIdAndValorCorrente(companyId, currentValue);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Acao> issue(EmitirAcao emitirAcao) {
        ArrayList<Acao> acaos = new ArrayList<Acao>();

        for (int i = 0; i < emitirAcao.getQuantidade(); i++) {
            Acao acao = new Acao();
            acao.setValorIPO(emitirAcao.getValorInicial());
            acao.setValorCorrente(emitirAcao.getValorInicial());
            acao.setDataEmissao(new Date());
            acao.setAcionistaId(emitirAcao.getAcionistaId());
            acaos.add(acaoRepository.save(acao));
        }

        return acaos;

    }

    @Override
    public void requestPurchase(ComprarAcao comprarAcao) {
        Usuario buyer = getUserById(comprarAcao.getCompradorId());
        if (buyer == null) {
            throw new IllegalArgumentException(
                    "Comprador não encontrado: " + comprarAcao.getCompradorId());
        }

        Usuario seller = getUserById(comprarAcao.getVedendorId());
        if (seller == null) {
            throw new IllegalArgumentException(
                    "Vedendor não encontrado: " + comprarAcao.getCompradorId());
        }

        List<Acao> sellerAcaos = findByOwnerIdAndCurrentValue(seller.getId(), comprarAcao.getValor());
        if (sellerAcaos.size() < comprarAcao.getQuantidade()) {
            throw new IllegalArgumentException(
                    "Número de acões insuficientes.");
        }

        Message message = new Message();
        message.setComprarAcao(comprarAcao);
        messageService.sendMessage(message);
    }

    @Override
    public void processPurchase(ComprarAcao comprarAcao) {
        Usuario buyer = getUserById(comprarAcao.getCompradorId());
        Usuario seller = getUserById(comprarAcao.getVedendorId());

        List<Acao> sellerAcaos = findByOwnerIdAndCurrentValue(seller.getId(), comprarAcao.getValor());

        for (int i = 0; i < comprarAcao.getQuantidade(); i++) {
            Acao acao = sellerAcaos.get(i);
            acao.setAcionistaId(buyer.getId());
            acao.setDataCompra(new Date());
            acao.setValorCorrente(comprarAcao.getValor());
            acaoRepository.save(acao);
        }

        EmailConfig config = new EmailConfig();
        config.sendMail(buyer.getEmail(), "Ordem de compa realizada!", String.format(
                "Sua ordem de compra de %d ação(es) foi realizada.",
                comprarAcao.getQuantidade(), comprarAcao.getValor(), seller.getNome()));
        config.sendMail(seller.getEmail(), "Sua ordem de venda foi realizada",
                String.format("%d ação(es) no valor de R$ %.2f.",
                        comprarAcao.getQuantidade(), comprarAcao.getValor(), buyer.getNome()));
    }

    private Usuario getUserById(String id) {
        Optional<Empresa> companyUser = empresaRepository.findById(id);
        Optional<Acionista> personUser = acionistaRepository.findById(id);

        if (companyUser.isPresent())
            return (Usuario) companyUser.get();
        else if (personUser.isPresent())
            return (Usuario) personUser.get();
        else
            return null;
    }

}
