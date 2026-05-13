package com.frigocezar.logistica.dto;

import com.frigocezar.logistica.enums.FormaPagamento;
import com.frigocezar.logistica.enums.StatusDespesa;
import com.frigocezar.logistica.enums.TipoDespesa;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DespesasDTO {

    private Long id;

    private String descricao;

    private TipoDespesa tipoDespesa;

    private BigDecimal preco;

    private FormaPagamento formaPagamento;

    private StatusDespesa status;

    private LocalDate dataDespesa;

    private Long motoristaId;

    private Long veiculoId;

}
