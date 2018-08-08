package com.javaee.dmgv.projetofinal.controllers.v1;


import java.util.List;

import com.javaee.dmgv.projetofinal.domain.Acionista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.javaee.dmgv.projetofinal.services.AcionistaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("API para Acionistas")
@RestController
@RequestMapping(AcionistaController.BASE_URL)
public class AcionistaController {

    public static final String BASE_URL = "/api/v1/acionistas";

    @Autowired
    private AcionistaService acionistaService;

    // GET

    @ApiOperation(value = "Buscar acionistas", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Acionista> findAll() {
        return acionistaService.findAll();
    }

    @ApiOperation(value = "Buscar acionista por identificador", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Acionista findById(@PathVariable String id) {
        return acionistaService.findById(id);
    }

    // POST

    @ApiOperation(value = "Adicionar acionista", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Acionista save(@RequestBody Acionista acionista) {
        return acionistaService.save(acionista);
    }

    // PUT

    @ApiOperation(value = "Atualizar acionista", produces = MediaType.APPLICATION_JSON_VALUE)
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Acionista update(@PathVariable String id, @RequestBody Acionista acionista) {
        return acionistaService.update(id, acionista);
    }

    // DELETE

    @ApiOperation(value = "Deletar acionista", produces = MediaType.APPLICATION_JSON_VALUE)
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) {
        acionistaService.deleteById(id);
    }

}