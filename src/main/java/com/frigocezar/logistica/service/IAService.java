package com.frigocezar.logistica.service;

import com.frigocezar.logistica.dto.IntencaoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IAService {

    private final OpenAIService openAIService;
    private final RelatorioService relatorioService;

    public String processar(String pergunta) {

        IntencaoDTO intencao =
                openAIService.interpretar(pergunta);

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

            default -> "Pergunta não reconhecida.";
        };

    }
}

