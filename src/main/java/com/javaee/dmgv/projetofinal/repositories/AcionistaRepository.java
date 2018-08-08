package com.javaee.dmgv.projetofinal.repositories;

import java.util.List;

import com.javaee.dmgv.projetofinal.domain.Acionista;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcionistaRepository extends MongoRepository<Acionista, String> {

    List<Acionista> findByNome(String name);

}
