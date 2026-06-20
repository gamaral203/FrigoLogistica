package com.frigocezar.logistica.dto;

import com.frigocezar.logistica.enums.TipoProduto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProdutoDTO {

    private Long id;

    @NotNull(message = "o nome é obrigatório")
    @Size(min = 3, max = 255)
    private String nome;

    @Size(min = 3, max = 255)
    private String descricao;

    @NotNull(message = "o preço é obrigatório")
    @DecimalMin(value = "0.01", message = "o preço deve ser maior que 0")
    private BigDecimal preco;

    @NotNull(message = "a quantidade é obrigatória")
    @Min(value = 0, message = "a quantidade no estoque deve ser positiva")
    private Integer quantidadeEstoque;

    @NotNull(message = "o tipo do produto é obrigatório")
    private TipoProduto tipoProduto;

}
