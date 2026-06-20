package com.frigocezar.logistica.docs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.frigocezar.logistica.dto.PerguntaDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "ia", description = "Endpoints relacionados à IA")
public interface IAControllerDoc {

    @Operation(summary = "Enviar pergunta", description = "Envia uma pergunta para o serviço de IA e retorna a resposta")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Resposta gerada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Pergunta inválida")
    })
    ResponseEntity<String> pergunta(@RequestBody @Valid PerguntaDTO perguntaDTO);

}
