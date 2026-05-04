package com.frigocezar.logistica.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VeiculoDTO {

    private Long id;

    @NotNull(message = "o tipo de veículo é obrigatório")
    @Size(min = 3,max = 55)
    @Pattern(regexp = "[a-zA-ZÀ-ÿ ]+", message = "Nome inválido, coloque apenas Letras")
    private String tipoVeiculo;

    @NotNull(message = "A placa é obrigatória")
    @Size(min = 6,max = 6)
    private String placa;

    @NotNull(message = "A marca do veículo é obrigatória")
    @Size(min = 3,max = 30)
    private String marca;

    @NotNull(message = "O modelo é obrigatório")
    @Size(min = 3,max = 30)
    private String modelo;

    @NotNull(message = "O renavam é obrigatória")
    @Size(min = 9,max = 11)
    @Pattern(regexp = "\\d+", message = "Erro, coloque somente Números")
    private String renavam;

    @NotNull(message = "a cor do veículo é obrigatória")
    @Size(min = 3,max = 55)
    private String cor;

    private List<Long> motoristasIds;
}
