package com.javaee.dmgv.projetofinal.controllers.v1;

import java.util.List;

import com.javaee.dmgv.projetofinal.domain.Acao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.javaee.dmgv.projetofinal.domain.EmitirAcao;
import com.javaee.dmgv.projetofinal.domain.ComprarAcao;
import com.javaee.dmgv.projetofinal.services.AcaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("API para ações")
@RestController
@RequestMapping(AcaoController.BASE_URL)
public class AcaoController {

    public static final String BASE_URL = "/api/v1/acoes";

    @Autowired
    private AcaoService acaoService;

    // GET

    @ApiOperation(value = "Buscar todas as ações", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Acao> findAll() {
        return acaoService.findAll();
    }

    @ApiOperation(value = "Buscar ação por ideGet a stock by identificador", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Acao findById(@PathVariable String id) {
        return acaoService.findById(id);
    }

    // POST

    @ApiOperation(value = "Emitir ações", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping({"/emitir"})
    @ResponseStatus(HttpStatus.CREATED)
    public List<Acao> issue(@RequestBody EmitirAcao emitirAcao) {
        return acaoService.issue(emitirAcao);
    }

    @ApiOperation(value = "Comprar ações",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping({"/comprar"})
    @ResponseStatus(HttpStatus.CREATED)
    public String purchase(@RequestBody ComprarAcao comprarAcao) {
        acaoService.requestPurchase(comprarAcao);
        return "Ordem de compra enviada.";
    }

}
