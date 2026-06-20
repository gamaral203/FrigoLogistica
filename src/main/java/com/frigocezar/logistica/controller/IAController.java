package com.frigocezar.logistica.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frigocezar.logistica.dto.PerguntaDTO;
import com.frigocezar.logistica.service.IAService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/ia")
@RequiredArgsConstructor
public class IAController implements com.frigocezar.logistica.docs.IAControllerDoc {

    private final IAService iaService;


    @PostMapping("/pergunta")

    public ResponseEntity<String> pergunta(@RequestBody PerguntaDTO perguntaDTO) {
        log.debug("Pergunta recebida: {}", perguntaDTO.getPergunta());
        String resposta = iaService.processar(perguntaDTO.getPergunta());
        log.info("Resposta gerada (tamanho={}): {}", resposta != null ? resposta.length() : 0, resposta);
        return ResponseEntity.ok(resposta);
    }
}
