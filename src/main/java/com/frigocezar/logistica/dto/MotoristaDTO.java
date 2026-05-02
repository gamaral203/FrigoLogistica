package com.frigocezar.logistica.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MotoristaDTO {


    private Long id;

    @NotNull(message = "O nome não pode ser nulo")
    @Pattern(regexp = "[a-zA-ZÀ-ÿ ]+", message = "Nome inválido, coloque apenas Letras")
    @Size(min = 3,max = 75)
    private String nome;

    @CPF(message = "CPF inválido")
    @NotNull(message = "Erro, o CPF é o obrigatório")
    private String cpf;

    @NotNull(message = "A CNH é obrigatótia")
    @Pattern(regexp = "\\d+", message = "Erro, coloque somente Números")
    @Size(min = 11,max = 11)
    private String cnh;

    private List<Long> veiculosIds;
}
