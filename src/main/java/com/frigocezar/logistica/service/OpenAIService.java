package com.frigocezar.logistica.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frigocezar.logistica.dto.IntencaoDTO;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

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
                
                Regras:
                - Se o usuário não informar mês, utilize o mês atual.
                - Se o usuário não informar ano, utilize o ano atual.
                - Nunca invente datas antigas.
                
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

        Map<String, Object> request = Map.of(
                "model", "gpt-4o-mini",
                "messages", List.of(
                        Map.of(
                                "role", "user",
                                "content", prompt
                        )
                )
        );

        String resposta = webClient.post()
                .uri("https://api.openai.com/v1/chat/completions")
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(request)
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

            conteudo = conteudo
                    .replace("```json", "")
                    .replace("```", "")
                    .trim();

            return objectMapper.readValue(
                    conteudo,
                    IntencaoDTO.class
            );

        } catch (Exception e) {
            throw new RuntimeException(
                    "Erro ao processar resposta da OpenAI",
                    e
            );
        }
    }
}


