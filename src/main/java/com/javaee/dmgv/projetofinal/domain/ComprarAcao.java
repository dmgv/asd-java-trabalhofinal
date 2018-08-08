package com.javaee.dmgv.projetofinal.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComprarAcao {

    private String compradorId;
    private String vedendorId;
    private Double valor;
    private Integer quantidade;

}

