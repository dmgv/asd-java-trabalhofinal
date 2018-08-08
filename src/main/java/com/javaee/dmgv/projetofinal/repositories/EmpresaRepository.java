package com.javaee.dmgv.projetofinal.repositories;


import java.util.List;

import com.javaee.dmgv.projetofinal.domain.Empresa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmpresaRepository extends MongoRepository<Empresa, String> {

    List<Empresa> findByNome(String name);

}
