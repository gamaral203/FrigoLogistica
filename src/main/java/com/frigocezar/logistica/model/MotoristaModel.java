package com.frigocezar.logistica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_motorista")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MotoristaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, name = "nome")
    private String nome;

    @Column(length = 11, nullable = false, name = "cpf", unique = true )
    private String cpf;

    @Column(length = 11, nullable = false, name = "cnh", unique = true )
    private String cnh;


    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "motorista_veiculo",
            joinColumns = @JoinColumn(name = "motorista_id"),
            inverseJoinColumns = @JoinColumn(name = "veiculo_id")
    )
    private List<VeiculoModel> veiculos;

}
