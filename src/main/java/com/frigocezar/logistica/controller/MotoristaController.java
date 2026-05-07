package com.frigocezar.logistica.controller;

import com.frigocezar.logistica.dto.MotoristaDTO;
import com.frigocezar.logistica.service.MotoristaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/motoristas")
@Validated
@Tag(name = "Motoristas", description = "Gerenciamento de Motoristas")
public class MotoristaController {

    private final MotoristaService motoristaService;

    public MotoristaController(MotoristaService motoristaService) {
        this.motoristaService = motoristaService;
    }


    @Operation(summary = "Criar um novo motorista", description = "Rota para criação de um novo motorista")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Motorista cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "CPF ou CNH já cadastrado")
    })

    @PostMapping("/cadastrar")
    public ResponseEntity<MotoristaDTO> cadastrarMotorista(@RequestBody @Valid MotoristaDTO motorista) {

        log.info("cadastrando Motorista: {} ", motorista);

        MotoristaDTO motoristaDTO = motoristaService.cadastrarMotorista(motorista);

        log.info("motorista cadastrado com sucesso. ID: {} ", motoristaDTO.getId());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(motoristaDTO);
    }


    @Operation(summary = "Listar todos os motoristas", description = "Rota que lista todos os motoristas cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Motorista encontrado"),
            @ApiResponse(responseCode = "404", description = "Motorista não encontrado")
    })

    @GetMapping("/listar")
    public ResponseEntity<List<MotoristaDTO>> listarMotorista() {

        log.info("Listando motoristas: ");

        List<MotoristaDTO> motoristas = motoristaService.listarMotoristas();

        log.info("quantidade de motoristas encontrados: {} ", motoristas.size());

        return ResponseEntity.ok(motoristas);
    }


    @Operation(summary = "buscar motorista por Id", description = "Rota que busca o motoristas pelo seu id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Motorista encontrado"),
            @ApiResponse(responseCode = "404", description = "Motorista não encontrado")
    })

    @GetMapping("buscarPorId/{id}")
    public ResponseEntity<MotoristaDTO> buscarMotoristaPorId(@PathVariable Long id) {
        log.info("Buscando motorista por ID: {}", id);
        MotoristaDTO motoristaDTO = motoristaService.buscarMotoristaPorId(id);
        return ResponseEntity.ok(motoristaDTO);
    }


    @Operation(summary = "Atualizar motorista por Id", description = "Verifica se o motorista existe e atualiza seus dados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Motorista não encontrado")
    })

    @PutMapping("atualizarPorId/{id}")
    public ResponseEntity<MotoristaDTO> atualizarMotorista(@PathVariable @Positive Long id,
                                                           @RequestBody @Valid MotoristaDTO motoristaDTO) {

        log.info("Atualizando Motorista: {}", id);
        MotoristaDTO motoristaAtualizado = motoristaService.atualizarMotoristaPorId(id, motoristaDTO);
        return ResponseEntity.ok(motoristaAtualizado);
    }

    @Operation(summary = "Deletar motorista", description = "Verifica s eo motorista existe no banco de dados e deleta")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Motorista não encontrado")
    })

    @DeleteMapping("/deletarPorId/{id}")
    public ResponseEntity<String> deletarMotoristaPorId(@PathVariable Long id) {
        log.info("Deletando motorista por ID: {}", id);

        motoristaService.deletarMotoristaPorId(id);

        return ResponseEntity.ok("Motorista deletado com sucesso");
    }
}
