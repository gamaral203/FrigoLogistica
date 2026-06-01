package com.frigocezar.logistica.controller;

import com.frigocezar.logistica.dto.PerguntaDTO;
import com.frigocezar.logistica.service.IAService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/ia")
@RequiredArgsConstructor
public class IAController {

    private final IAService iaService;


    @PostMapping("/pergunta")

    public ResponseEntity<String> pergunta(@RequestBody PerguntaDTO perguntaDTO) {
        return ResponseEntity.ok(
                iaService.processar(perguntaDTO.getPergunta())
        );
    }
}
