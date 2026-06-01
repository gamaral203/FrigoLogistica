package com.frigocezar.logistica.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frigocezar.logistica.dto.IntencaoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class OpenAIService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    @Value("${openai.api-key}")
    private String apiKey;
    @Value("${openai.model}")
    private String model;

    public IntencaoDTO interpretar(String pergunta) {

        String prompt = """
            Você é um assistente de logística.

            Analise a pergunta e responda APENAS com JSON válido.

            Exemplos:

            {
              "acao":"MOTORISTA_MAIS_DESPESAS",
              "mes":6,
              "ano":2026
            }

            {
              "acao":"VEICULO_MAIS_CUSTOSO",
              "mes":6,
              "ano":2026
            }

            {
              "acao":"GASTO_COMBUSTIVEL",
              "mes":6,
              "ano":2026
            }

            Pergunta:
            """ + pergunta;

        String resposta = webClient.post()
                .uri("https://api.openai.com/v1/chat/completions")
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue("""
                {
                  "model":"gpt-4o-mini",
                  "messages":[
                    {
                      "role":"user",
                      "content":"%s"
                    }
                  ]
                }
                """.formatted(prompt.replace("\"", "\\\"")))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        try {

            JsonNode root = objectMapper.readTree(resposta);

            String conteudo = root
                    .get("choices")
                    .get(0)
                    .get("message")
                    .get("content")
                    .asText();

            return objectMapper.readValue(conteudo, IntencaoDTO.class);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar resposta da OpenAI", e);
        }
    }


}
