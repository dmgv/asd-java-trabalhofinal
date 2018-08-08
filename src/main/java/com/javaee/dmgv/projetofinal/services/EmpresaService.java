package com.javaee.dmgv.projetofinal.services;

import java.util.List;

import com.javaee.dmgv.projetofinal.domain.Empresa;

public interface EmpresaService {

    List<Empresa> findAll();

    Empresa findById(String id);

    Empresa save(Empresa empresa);

    Empresa update(String id, Empresa empresa);

    void deleteById(String id);
}
