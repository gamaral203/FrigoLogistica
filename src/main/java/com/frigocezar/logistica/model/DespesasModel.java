package com.frigocezar.logistica.model;

import com.frigocezar.logistica.enums.FormaPagamento;
import com.frigocezar.logistica.enums.StatusDespesa;
import com.frigocezar.logistica.enums.TipoDespesa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_despesas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DespesasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false, name = "descricao")
    private String descricao;

    @Enumerated(EnumType.STRING)
    private TipoDespesa tipoDespesa;

    @Column(nullable = false, name = "preco")
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @Enumerated(EnumType.STRING)
    private StatusDespesa status;

    @Column(nullable = false, name = "dataDespesa")
    private LocalDate dataDespesa;

    @ManyToOne
    @JoinColumn(name = "motorista_id")
    private MotoristaModel motorista;


    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private VeiculoModel veiculo;

}
