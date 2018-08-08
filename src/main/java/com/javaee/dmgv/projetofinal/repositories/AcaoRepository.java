package com.javaee.dmgv.projetofinal.repositories;

import java.util.List;

import com.javaee.dmgv.projetofinal.domain.Acao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcaoRepository extends MongoRepository<Acao, String> {

  List<Acao> findByAcionistaIdAndValorCorrente(String acionistaId, Double valorCorrent);

}
