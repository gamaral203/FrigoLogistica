package com.frigocezar.logistica.model;

import com.frigocezar.logistica.enums.TipoProduto;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_produto")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false, name = "nome")
    private String nome;

    @Column(length = 255, name = "descricao")
    private String descricao;

    @Column(nullable = false, name = "preco")
    @DecimalMin(value = "0.01", message = "o preço deve ser menor que 0")
    private BigDecimal preco;

    @Column(nullable = false, name = "quantidade_estoque")
    @Min(value = 0, message = "a quantidade no estoque deve ser positiva")
    private Integer quantidadeEstoque;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "tipo_produto")
    private TipoProduto tipoProduto;

    private LocalDate dataCadastro;

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDate.now();

    }
}
