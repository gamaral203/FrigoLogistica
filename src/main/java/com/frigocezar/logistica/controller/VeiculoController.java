package com.frigocezar.logistica.controller;

import com.frigocezar.logistica.docs.VeiculoControllerDoc;
import com.frigocezar.logistica.dto.VeiculoDTO;
import com.frigocezar.logistica.service.VeiculoService;
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
public class VeiculoController implements VeiculoControllerDoc {


    private VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;

    }

    @PostMapping("/criar")
    public ResponseEntity<VeiculoDTO> cadastrarVeiculo(@RequestBody @Valid VeiculoDTO veiculo) {

        log.debug("cadastrando Veiculo: {}", veiculo);

        VeiculoDTO veiculoDTO = veiculoService.cadastrarVeiculo(veiculo);

        log.info("Veiculo cadastrado com sucesso : {}", veiculoDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(veiculoDTO);

    }

    @GetMapping("/listar")
    public ResponseEntity<List<VeiculoDTO>> listarVeiculo() {

        log.debug("Listando veiculos: ");
        List<VeiculoDTO> veiculosDTO = veiculoService.listarVeiculos();
        log.info("quantidade de veiculos encontrados: {}", veiculosDTO.size());
        return ResponseEntity.ok(veiculosDTO);
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<VeiculoDTO> buscarVeiculoPorID(@PathVariable Long id) {
        log.debug("Buscando Veiculo por id: {}", id);

        VeiculoDTO veiculoDTO = veiculoService.buscarVeiculoPorId(id);
        return ResponseEntity.ok(veiculoDTO);

    }

    @PutMapping("/EditarPorId/{id}")
    public ResponseEntity<VeiculoDTO> editarVeiculoPorID(@PathVariable @Valid Long id,
                                                         @RequestBody VeiculoDTO veiculo) {

        log.debug("Editando Veiculo por id: {}", id);

        VeiculoDTO veiculoAtualizado = veiculoService.atualizarVeiculo(id, veiculo);
        return ResponseEntity.ok(veiculoAtualizado);
    }

    @DeleteMapping("/deletarPorId/{id}")
    public ResponseEntity<String> deletarVeiculoPorID(@PathVariable Long id) {
        log.debug("Deletando Veiculo por id: {}", id);


        veiculoService.deletarVeiculo(id);

        log.info("Veiculo deletado com sucesso: {}", id);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Veiculo deletado com sucesso");

    }
}
