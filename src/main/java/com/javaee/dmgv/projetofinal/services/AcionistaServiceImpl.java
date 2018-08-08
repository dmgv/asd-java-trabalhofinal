package com.javaee.dmgv.projetofinal.services;

import java.util.List;
import java.util.Optional;

import com.javaee.dmgv.projetofinal.domain.Acionista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.javaee.dmgv.projetofinal.repositories.AcionistaRepository;

@Service
public class AcionistaServiceImpl implements AcionistaService {

    @Autowired
    private AcionistaRepository acionistaRepository;

    @Override
    public List<Acionista> findAll() {
        return acionistaRepository.findAll();
    }

    @Override
    public Acionista findById(String id) {
        return getPersonById(id);
    }

    private Acionista getPersonById(String id) {
        Optional<Acionista> personOptional = acionistaRepository.findById(id);

        if (!personOptional.isPresent()) {
            throw new IllegalArgumentException("Acionista não encotrado: " + id);
        }

        return personOptional.get();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Acionista save(Acionista acionista) {
        if (acionistaRepository.findByNome(acionista.getNome()).isEmpty()) {
            return acionistaRepository.save(acionista);
        } else {
            throw new IllegalArgumentException("Acionista já cadastrado: " + acionista.getNome());
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Acionista update(String id, Acionista acionista) {
        acionista.setId(id);
        return acionistaRepository.save(acionista);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(String id) {
        acionistaRepository.deleteById(id);
    }

}
