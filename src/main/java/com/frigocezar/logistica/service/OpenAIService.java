package com.frigocezar.logistica.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frigocezar.logistica.dto.IntencaoDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class OpenAIService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    @Value("${openai.api-key}")
    private String apiKey;

    @Value("${openai.model}")
    private String model;

    @Transactional
    public IntencaoDTO interpretar(String pergunta) {

        int mesAtual = LocalDate.now().getMonthValue();
        int anoAtual = LocalDate.now().getYear();

        String systemPrompt = """
                Você é um assistente especializado em análise de despesas logísticas de frotas de transporte frigorífico.
                
                Sua ÚNICA responsabilidade é classificar a intenção do usuário e retornar um JSON estruturado.
                Você NÃO responde perguntas fora do escopo de logística e despesas de frota.
                
                ════════════════════════════════════════
                AÇÕES DISPONÍVEIS
                ════════════════════════════════════════
                
                MOTORISTA_MAIS_DESPESAS
                  → Usuário quer saber qual motorista gerou mais despesas no período.
                  → Exemplos: "quem gastou mais?", "motorista com maior custo", "qual motorista tem mais despesas em março?"
                
                VEICULO_MAIS_CUSTOSO
                  → Usuário quer saber qual veículo teve o maior custo no período.
                  → Exemplos: "veículo mais caro", "qual caminhão gerou mais custo?", "frota com mais gasto"
                
                GASTO_COMBUSTIVEL
                  → Usuário quer saber o total gasto com combustível no período.
                  → Exemplos: "quanto gastamos com combustível?", "total de abastecimento", "gasto de diesel"
                
                GASTO_PEDAGIO
                  → Usuário quer saber o total gasto com pedágios no período.
                  → Exemplos: "quanto pagamos de pedágio?", "custo de pedágios", "total em praças de pedágio"
                
                GASTO_MANUTENCAO
                  → Usuário quer saber o total gasto com manutenção no período.
                  → Exemplos: "gasto com manutenção", "quanto foi em consertos?", "total de revisões e reparos"
                
                GASTO_ALIMENTACAO
                  → Usuário quer saber o total gasto com alimentação dos motoristas.
                  → Exemplos: "gasto com alimentação", "quanto foi em refeições?", "total de diárias de comida"
                
                GASTO_HOSPEDAGEM
                  → Usuário quer saber o total gasto com hospedagem.
                  → Exemplos: "gasto com hotel", "total em hospedagem", "quanto foi em pernoites?"
                
                GASTO_MULTA
                  → Usuário quer saber o total gasto com multas.
                  → Exemplos: "total de multas", "quanto pagamos em infrações?", "gasto com autuações"
                
                GASTO_TOTAL
                  → Usuário quer saber o gasto total geral de todas as despesas no período.
                  → Exemplos: "quanto gastamos no total?", "total de despesas", "custo total da frota"
                
                DESCONHECIDO
                  → A pergunta não corresponde a nenhuma das ações acima, está fora do escopo de logística,
                    ou não foi possível identificar a intenção com clareza.
                
                ════════════════════════════════════════
                REGRAS OBRIGATÓRIAS
                ════════════════════════════════════════
                
                1. Responda SOMENTE com JSON. Nenhum texto antes ou depois.
                2. Não use blocos de código (não use ``` em hipótese alguma).
                3. Se o usuário NÃO informar o mês, use o mês atual: %d
                4. Se o usuário NÃO informar o ano, use o ano atual: %d
                5. Nunca assuma datas arbitrárias que o usuário não mencionou.
                6. "mes" e "ano" devem ser números inteiros (não strings).
                7. O campo "acao" deve conter exatamente uma das strings listadas acima.
                8. Quando a ação for DESCONHECIDO, ainda assim retorne mes e ano com os valores atuais.
                
                ════════════════════════════════════════
                FORMATO DE RESPOSTA (sempre este, sem variações)
                ════════════════════════════════════════
                
                {"acao":"NOME_DA_ACAO","mes":6,"ano":2026}
                
                ════════════════════════════════════════
                EXEMPLOS
                ════════════════════════════════════════
                
                Pergunta: "qual motorista gastou mais esse mês?"
                Resposta: {"acao":"MOTORISTA_MAIS_DESPESAS","mes":%d,"ano":%d}
                
                Pergunta: "veículo mais custoso em janeiro de 2025"
                Resposta: {"acao":"VEICULO_MAIS_CUSTOSO","mes":1,"ano":2025}
                
                Pergunta: "quanto foi o gasto com combustível em março?"
                Resposta: {"acao":"GASTO_COMBUSTIVEL","mes":3,"ano":%d}
                
                Pergunta: "total de multas no ano passado em novembro"
                Resposta: {"acao":"GASTO_MULTA","mes":11,"ano":%d}
                
                Pergunta: "me conta uma piada"
                Resposta: {"acao":"DESCONHECIDO","mes":%d,"ano":%d}
                """.formatted(
                mesAtual, anoAtual,
                mesAtual, anoAtual,
                anoAtual,
                anoAtual,
                anoAtual - 1,
                mesAtual, anoAtual
        );

        log.debug("Interpretando pergunta: {}", pergunta);

        Map<String, Object> request = Map.of(
                "model", model,
                "temperature", 0,
                "messages", List.of(
                        Map.of("role", "system", "content", systemPrompt),
                        Map.of("role", "user", "content", pergunta)
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
                    .asText()
                    .replace("```json", "")
                    .replace("```", "")
                    .trim();

            log.debug("Resposta da OpenAI: {}", conteudo);

            IntencaoDTO intencao = objectMapper.readValue(conteudo, IntencaoDTO.class);

            // Garante fallback seguro caso a IA retorne campos nulos
            if (intencao.getMes() == null) intencao.setMes(mesAtual);
            if (intencao.getAno() == null) intencao.setAno(anoAtual);
            if (intencao.getAcao() == null) intencao.setAcao("DESCONHECIDO");

            log.info("Intenção identificada: acao={}, mes={}, ano={}",
                    intencao.getAcao(), intencao.getMes(), intencao.getAno());

            return intencao;

        } catch (Exception e) {
            log.error("Erro ao processar resposta da OpenAI. Resposta recebida: {}", resposta, e);
            throw new RuntimeException("Erro ao processar resposta da OpenAI", e);
        }
    }
}