package com.frigocezar.logistica.controller;

import com.frigocezar.logistica.dto.VeiculoDTO;
import com.frigocezar.logistica.service.MotoristaService;
import com.frigocezar.logistica.service.VeiculoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    private final MotoristaService motoristaService;
    private VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService, MotoristaService motoristaService) {
        this.veiculoService = veiculoService;
        this.motoristaService = motoristaService;
    }

    @PostMapping("/criar")
    public ResponseEntity<VeiculoDTO> cadastrarVeiculo(@RequestBody VeiculoDTO veiculo) {

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
    public ResponseEntity<?> buscarVeiculoPorID(@PathVariable Long id) {

        log.debug("Buscando Veiculo por id: {}", id);
        VeiculoDTO veiculoDTO = veiculoService.buscarVeiculoPorId(id);

        if (veiculoDTO != null) {
            log.info("Veículo encontrado com sucesso: {}", veiculoDTO);
            return ResponseEntity.ok(veiculoDTO);
        } else {
            log.warn("Veículo não encontrado, tente novamente");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Veículo com o Id " + id + " não encontrado");
        }
    }

    @PutMapping("/EditarPorId/{id}")
    public ResponseEntity<?> editarVeiculoPorID(@PathVariable Long id, @RequestBody VeiculoDTO veiculo) {

        log.debug("Editando Veiculo por id: {}", id);
        VeiculoDTO veiculoAtualizado = veiculoService.atualizarVeiculo(id,veiculo);

        if (veiculoAtualizado != null) {
            log.info("Veículo editado com sucesso: {}", veiculoAtualizado);
            return ResponseEntity.ok(veiculoAtualizado);
        } else {
            log.warn("Veículo não encontrado, tente novamente");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Id " + id + " não encontrado");
        }
    }

    @DeleteMapping("/deletarPorId/{id}")
    public ResponseEntity<String> deletarVeiculoPorID(@PathVariable Long id) {
        log.debug("Deletando Veiculo por id: {}", id);


        if (veiculoService.buscarVeiculoPorId(id) != null) {
            veiculoService.deletarVeiculo(id);

            log.info("Veiculo deletado com sucesso: {}", id);

            return ResponseEntity.status(HttpStatus.OK)
                    .body("Veiculo deletado com sucesso");
        } else {
            log.warn("Não foi possível deletar veiculo, id não encontrado: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("id " + id + " não encontrado");
        }
    }

}
