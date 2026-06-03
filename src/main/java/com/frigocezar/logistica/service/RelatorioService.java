package com.frigocezar.logistica.service;

import com.frigocezar.logistica.repository.DespesasRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class RelatorioService {

    private final DespesasRepository despesasRepository;


    private static final Map<String, String> LABELS_TIPO = Map.of(
            "COMBUSTIVEL",  "Combustível",
            "PEDAGIO",      "Pedágios",
            "ALIMENTACAO",  "Alimentação",
            "HOSPEDAGEM",   "Hospedagem",
            "MULTA",        "Multas",
            "MANUTENCAO",   "Manutenção",
            "OUTROS",       "Outros"
    );

    @Transactional
    public String motoristaMaisDespesas(Integer mes, Integer ano) {
        log.debug("Buscando motorista com mais despesas: mes={}, ano={}", mes, ano);

        List<Object[]> resultado = despesasRepository.motoristaMaisDespesas(mes, ano);

        if (resultado.isEmpty()) {
            return String.format("Nenhuma despesa encontrada para %02d/%d.", mes, ano);
        }

        Object[] dados = resultado.get(0);
        return String.format(
                "Motorista com maior gasto em %02d/%d:%n" +
                        "  Nome: %s%n" +
                        "  Total: R$ %.2f",
                mes, ano,
                dados[0],
                dados[1]
        );
    }

    public String veiculoMaisCustoso(Integer mes, Integer ano) {
        log.debug("Buscando veículo mais custoso: mes={}, ano={}", mes, ano);

        List<Object[]> resultado = despesasRepository.veiculoMaisCustoso(mes, ano);

        if (resultado.isEmpty()) {
            return String.format("Nenhuma despesa encontrada para %02d/%d.", mes, ano);
        }

        Object[] dados = resultado.get(0);
        return String.format(
                "Veículo com maior custo em %02d/%d:%n" +
                        "  Veículo: %s%n" +
                        "  Total: R$ %.2f",
                mes, ano,
                dados[0],
                dados[1]
        );
    }

    public String gastoCombustivel(Integer mes, Integer ano) {
        log.debug("Buscando gasto com combustível: mes={}, ano={}", mes, ano);

        BigDecimal total = despesasRepository.gastoCombustivel(mes, ano);

        if (total == null || total.compareTo(BigDecimal.ZERO) == 0) {
            return String.format("Nenhum gasto com combustível registrado em %02d/%d.", mes, ano);
        }

        return String.format(
                "Gasto total com combustível em %02d/%d:%n  R$ %.2f",
                mes, ano, total
        );
    }

    public String gastoPorTipo(String tipo, Integer mes, Integer ano) {
        log.debug("Buscando gasto por tipo={}, mes={}, ano={}", tipo, mes, ano);

        BigDecimal total = despesasRepository.gastoPorTipo(tipo, mes, ano);
        String label = LABELS_TIPO.getOrDefault(tipo, tipo);

        if (total == null || total.compareTo(BigDecimal.ZERO) == 0) {
            return String.format("Nenhum gasto com %s registrado em %02d/%d.", label.toLowerCase(), mes, ano);
        }

        return String.format(
                "Gasto total com %s em %02d/%d:%n  R$ %.2f",
                label, mes, ano, total
        );
    }

    public String gastoTotal(Integer mes, Integer ano) {
        log.debug("Buscando gasto total: mes={}, ano={}", mes, ano);

        BigDecimal total = despesasRepository.gastoTotal(mes, ano);

        if (total == null || total.compareTo(BigDecimal.ZERO) == 0) {
            return String.format("Nenhuma despesa registrada em %02d/%d.", mes, ano);
        }

        return String.format(
                "Gasto total da frota em %02d/%d:%n  R$ %.2f",
                mes, ano, total
        );
    }
}