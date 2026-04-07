package com.frigocezar.logistica.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VeiculoDTO {

    private Long id;


    private String tipoVeiculo;


    private String placa;


    private String marca;


    private String modelo;


    private String renvam;


    private String cor;
}
