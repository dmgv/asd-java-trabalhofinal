package com.javaee.dmgv.projetofinal.services;


import java.util.List;
import java.util.Optional;

import com.javaee.dmgv.projetofinal.domain.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.javaee.dmgv.projetofinal.repositories.EmpresaRepository;


@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    @Override
    public Empresa findById(String id) {
        return getCompanyById(id);
    }

    private Empresa getCompanyById(String id) {
        Optional<Empresa> companyOptional = empresaRepository.findById(id);

        if (!companyOptional.isPresent()) {
            throw new IllegalArgumentException("Empresa não econtrada: " + id);
        }

        return companyOptional.get();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Empresa save(Empresa empresa) {
        if (empresaRepository.findByNome(empresa.getNome()).isEmpty()) {
            return empresaRepository.save(empresa);
        } else {
            throw new IllegalArgumentException("Empresa já cadastrada: " + empresa.getNome());
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Empresa update(String id, Empresa empresa) {
        empresa.setId(id);
        return empresaRepository.save(empresa);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(String id) {
        empresaRepository.deleteById(id);
    }

}
