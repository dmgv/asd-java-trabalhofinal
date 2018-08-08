package com.javaee.dmgv.projetofinal.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Acao {

    @Id
    private String id;
    private String acionistaId;
    private Double valorIPO;
    private Double valorCorrente;
    private Date dataEmissao;
    private Date dataCompra;

}