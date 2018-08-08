package com.javaee.dmgv.projetofinal.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter

public class Usuario {
    @Id
    private String id;
    private String nome;
    private String email;

    public Usuario() {
    }
}
