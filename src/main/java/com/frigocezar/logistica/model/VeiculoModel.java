package com.frigocezar.logistica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tb_veiculo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    private String renavam;

    @Column(name = "cor")
    private String cor;


    @ManyToMany(mappedBy = "veiculos")
    @JsonIgnore
    private List<MotoristaModel> motoristas;

}
