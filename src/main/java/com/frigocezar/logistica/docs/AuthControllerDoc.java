package com.frigocezar.logistica.docs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.frigocezar.logistica.dto.AutenticacaoDTO;
import com.frigocezar.logistica.dto.LoginResponseDTO;
import com.frigocezar.logistica.dto.RegistroDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "auth", description = "Autenticação e registro de usuários")
public interface AuthControllerDoc {

    @Operation(summary = "Autenticar usuário", description = "Gera token JWT para usuário autenticado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Autenticado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AutenticacaoDTO auth);


    @Operation(summary = "Registrar usuário", description = "Cria um novo usuário no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou usuário já existente")
    })
    ResponseEntity registrar(@RequestBody @Valid RegistroDTO registroDto);

}
