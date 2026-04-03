package com.frigocezar.logistica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MotoristaDTO {

    private Long id;

    private String nome;

    private String cpf;

    private String cnh;
}
