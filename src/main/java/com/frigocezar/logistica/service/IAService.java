package com.frigocezar.logistica.service;

import com.frigocezar.logistica.dto.IntencaoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class IAService {

    private final OpenAIService openAIService;
    private final RelatorioService relatorioService;

    public String processar(String pergunta) {

        if (pergunta == null || pergunta.isBlank()) {
            return "Por favor, envie uma pergunta para que eu possa ajudá-lo.";
        }

        log.info("Processando pergunta da IA: {}", pergunta);

        IntencaoDTO intencao = openAIService.interpretar(pergunta);

        return switch (intencao.getAcao()) {

            case "MOTORISTA_MAIS_DESPESAS" -> relatorioService.motoristaMaisDespesas(
                    intencao.getMes(),
                    intencao.getAno());

            case "VEICULO_MAIS_CUSTOSO" -> relatorioService.veiculoMaisCustoso(
                    intencao.getMes(),
                    intencao.getAno());

            case "GASTO_COMBUSTIVEL" -> relatorioService.gastoCombustivel(
                    intencao.getMes(),
                    intencao.getAno());

            case "GASTO_PEDAGIO" -> relatorioService.gastoPorTipo(
                    "PEDAGIO",
                    intencao.getMes(),
                    intencao.getAno());

            case "GASTO_MANUTENCAO" -> relatorioService.gastoPorTipo(
                    "MANUTENCAO",
                    intencao.getMes(),
                    intencao.getAno());

            case "GASTO_ALIMENTACAO" -> relatorioService.gastoPorTipo(
                    "ALIMENTACAO",
                    intencao.getMes(),
                    intencao.getAno());

            case "GASTO_HOSPEDAGEM" -> relatorioService.gastoPorTipo(
                    "HOSPEDAGEM",
                    intencao.getMes(),
                    intencao.getAno());

            case "GASTO_MULTA" -> relatorioService.gastoPorTipo(
                    "MULTA",
                    intencao.getMes(),
                    intencao.getAno());

            case "GASTO_TOTAL" -> relatorioService.gastoTotal(
                    intencao.getMes(),
                    intencao.getAno());

            case "DESCONHECIDO" ->
                    "Não entendi sua pergunta. Posso ajudá-lo com informações sobre: " +
                            "motoristas com mais despesas, veículos mais custosos, gastos com combustível, " +
                            "pedágios, manutenção, alimentação, hospedagem, multas e total de despesas. " +
                            "Tente reformular sua pergunta!";

            default -> {
                log.warn("Ação não mapeada recebida: {}", intencao.getAcao());
                yield "Não foi possível processar sua solicitação. Tente novamente com uma pergunta diferente.";
            }
        };
    }
}