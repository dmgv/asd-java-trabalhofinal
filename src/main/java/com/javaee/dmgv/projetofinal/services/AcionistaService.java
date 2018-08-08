package com.javaee.dmgv.projetofinal.services;

import java.util.List;

import com.javaee.dmgv.projetofinal.domain.Acionista;

public interface AcionistaService {

    List<Acionista> findAll();

    Acionista findById(String id);

    Acionista save(Acionista acionista);

    Acionista update(String id, Acionista acionista);

    void deleteById(String id);
}
