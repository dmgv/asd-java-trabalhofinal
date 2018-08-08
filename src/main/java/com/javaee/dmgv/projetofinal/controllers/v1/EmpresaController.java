package com.javaee.dmgv.projetofinal.controllers.v1;


import java.util.List;

import com.javaee.dmgv.projetofinal.domain.Empresa;
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
import com.javaee.dmgv.projetofinal.services.EmpresaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("API para Empresas")
@RestController
@RequestMapping(EmpresaController.BASE_URL)
public class EmpresaController {

    public static final String BASE_URL = "/api/v1/empresas";

    @Autowired
    private EmpresaService empresaService;

    // GET

    @ApiOperation(value = "Buscar empresas", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Empresa> findAll() {
        return empresaService.findAll();
    }

    @ApiOperation(value = "Buscar empresa por identificador", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Empresa findById(@PathVariable String id) {
        return empresaService.findById(id);
    }

    // POST

    @ApiOperation(value = "Adicionar empresa", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Empresa save(@RequestBody Empresa empresa) {
        return empresaService.save(empresa);
    }

    // PUT

    @ApiOperation(value = "Atualizar empresa", produces = MediaType.APPLICATION_JSON_VALUE)
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Empresa update(@PathVariable String id, @RequestBody Empresa empresa) {
        return empresaService.update(id, empresa);
    }

    // DELETE

    @ApiOperation(value = "Deletar emrpresa", produces = MediaType.APPLICATION_JSON_VALUE)
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) {
        empresaService.deleteById(id);
    }

}
