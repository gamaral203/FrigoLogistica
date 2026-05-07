package com.frigocezar.logistica.controller;

import com.frigocezar.logistica.dto.MotoristaDTO;
import com.frigocezar.logistica.dto.VeiculoDTO;
import com.frigocezar.logistica.service.MotoristaService;
import com.frigocezar.logistica.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/veiculos")
@Validated
@Tag(name = "veiculos", description = "Gerenciamento de veiculos")
public class VeiculoController {

    private final MotoristaService motoristaService;
    private VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService, MotoristaService motoristaService) {
        this.veiculoService = veiculoService;
        this.motoristaService = motoristaService;
    }

    @Operation(summary = "Cadastrar veículo", description = "Cadastra um novo veículo no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Veículo cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })

    @PostMapping("/criar")
    public ResponseEntity<VeiculoDTO> cadastrarVeiculo(@RequestBody @Valid VeiculoDTO veiculo) {

        log.debug("cadastrando Veiculo: {}", veiculo);

        VeiculoDTO veiculoDTO = veiculoService.cadastrarVeiculo(veiculo);

        log.info("Veiculo cadastrado com sucesso : {}", veiculoDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(veiculoDTO);

    }

    @Operation(summary = "Listar veículos", description = "Retorna todos os veículos cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum veículo encontrado")
    })

    @GetMapping("/listar")
    public ResponseEntity<List<VeiculoDTO>> listarVeiculo() {

        log.debug("Listando veiculos: ");
        List<VeiculoDTO> veiculosDTO = veiculoService.listarVeiculos();
        log.info("quantidade de veiculos encontrados: {}", veiculosDTO.size());
        return ResponseEntity.ok(veiculosDTO);
    }

    @Operation(summary = "Buscar veículo por ID", description = "Retorna um veículo pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Veículo encontrado"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<?> buscarVeiculoPorID(@PathVariable Long id) {
        log.debug("Buscando Veiculo por id: {}", id);

        VeiculoDTO veiculoDTO = veiculoService.buscarVeiculoPorId(id);
        return ResponseEntity.ok(veiculoDTO);

    }

    @Operation(summary = "Editar veículo por ID", description = "Atualiza os dados de um veículo existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Veículo atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })

    @PutMapping("/EditarPorId/{id}")
    public ResponseEntity<VeiculoDTO> editarVeiculoPorID(@PathVariable @Valid Long id,
                                                         @RequestBody VeiculoDTO veiculo) {

        log.debug("Editando Veiculo por id: {}", id);

        VeiculoDTO veiculoAtualizado = veiculoService.atualizarVeiculo(id, veiculo);
        return ResponseEntity.ok(veiculoAtualizado);
    }


    @Operation(summary = "Deletar veículo por ID", description = "Remove um veículo do sistema pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Veículo deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })

    @DeleteMapping("/deletarPorId/{id}")
    public ResponseEntity<String> deletarVeiculoPorID(@PathVariable Long id) {
        log.debug("Deletando Veiculo por id: {}", id);


        veiculoService.deletarVeiculo(id);

        log.info("Veiculo deletado com sucesso: {}", id);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Veiculo deletado com sucesso");

    }
}
