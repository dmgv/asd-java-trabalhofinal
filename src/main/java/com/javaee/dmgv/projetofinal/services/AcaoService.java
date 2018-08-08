package com.javaee.dmgv.projetofinal.services;

import java.util.List;

import com.javaee.dmgv.projetofinal.domain.Acao;
import com.javaee.dmgv.projetofinal.domain.EmitirAcao;
import com.javaee.dmgv.projetofinal.domain.ComprarAcao;

public interface AcaoService {

    List<Acao> findAll();

    Acao findById(String id);

    List<Acao> findByOwnerIdAndCurrentValue(String ownerId, Double currentValue);

    List<Acao> issue(EmitirAcao emitirAcao);

    void requestPurchase(ComprarAcao comprarAcao);

    void processPurchase(ComprarAcao comprarAcao);
}
