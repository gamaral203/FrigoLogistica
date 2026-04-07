package com.frigocezar.logistica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_veiculo")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VeiculoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_veiculo", nullable = false)
    private String tipoVeiculo;

    @Column(name = "placa", length = 6, nullable = false, unique = true)
    private String placa;

    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "renavam",length = 11, nullable = false, unique = true)
    private String renvam;

    @Column(name = "cor")
    private String cor;

}
