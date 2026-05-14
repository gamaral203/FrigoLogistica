package com.frigocezar.logistica.dto;

import com.frigocezar.logistica.enums.FormaPagamento;
import com.frigocezar.logistica.enums.StatusDespesa;
import com.frigocezar.logistica.enums.TipoDespesa;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DespesasDTO {

    private Long id;

    @NotNull(message = "A descrição é obrigatória")
    @Size(min = 3,max = 255)
    private String descricao;

    @NotNull(message = "O tipo da despesa é obrigatória")
    private TipoDespesa tipoDespesa;

    @NotNull(message = "o preço é obrigatório")
    @Positive
    private BigDecimal preco;

    @NotNull(message = "a forma de pagamento é obrigatória")
    private FormaPagamento formaPagamento;

    @NotNull(message = "O Status do pagamento é obrigatório")
    private StatusDespesa status;

    @NotNull(message = "a data da despesa é obrigatória")
    private LocalDate dataDespesa;

    private Long motoristaId;

    private Long veiculoId;

}
