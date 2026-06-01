package com.frigocezar.logistica.service;

import com.frigocezar.logistica.repository.DespesasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final DespesasRepository despesasRepository;

    public String motoristaMaisDespesas(Integer mes, Integer ano) {

        List<Object[]> resultado =
                despesasRepository.motoristaMaisDespesas(mes, ano);

        if (resultado.isEmpty()) {
            return "Nenhuma despesa encontrada.";
        }

        Object[] dados = resultado.get(0);

        return String.format(
                "Motorista com maior gasto: %s%nValor total: R$ %.2f",
                dados[0],
                dados[1]
        );
    }

    public String veiculoMaisCustoso(Integer mes, Integer ano) {

        List<Object[]> resultado =
                despesasRepository.veiculoMaisCustoso(mes, ano);

        if (resultado.isEmpty()) {
            return "Nenhuma despesa encontrada.";
        }

        Object[] dados = resultado.get(0);

        return String.format(
                "Veículo com maior custo: %s%nValor total: R$ %.2f",
                dados[0],
                dados[1]
        );
    }

    public String gastoCombustivel(Integer mes, Integer ano) {

        BigDecimal total =
                despesasRepository.gastoCombustivel(mes, ano);

        return String.format(
                "Gasto total com combustível: R$ %.2f",
                total
        );
    }
}