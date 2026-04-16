package com.frigocezar.logistica.controller;

import com.frigocezar.logistica.dto.MotoristaDTO;
import com.frigocezar.logistica.dto.VeiculoDTO;
import com.frigocezar.logistica.service.MotoristaService;
import com.frigocezar.logistica.service.VeiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        VeiculoDTO veiculoDTO = veiculoService.cadastrarVeiculo(veiculo);
        return ResponseEntity.ok(veiculoDTO);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<VeiculoDTO>> listarVeiculo() {
        List<VeiculoDTO> veiculosDTO = veiculoService.listarVeiculos();
        return ResponseEntity.ok(veiculosDTO);
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<?> buscarVeiculoPorID(@PathVariable Long id) {
        VeiculoDTO veiculoDTO = veiculoService.buscarVeiculoPorId(id);

        if (veiculoDTO != null) {
            return ResponseEntity.ok(veiculoDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Veículo com o Id " + id + " não encontrado");
        }
    }

    @PutMapping("/EditarPorId/{id}")
    public ResponseEntity<?> editarVeiculoPorID(@PathVariable Long id, @RequestBody VeiculoDTO veiculo) {

        VeiculoDTO veiculoAtualizado = veiculoService.buscarVeiculoPorId(id);

        if (veiculoAtualizado != null) {
            return ResponseEntity.ok(veiculoAtualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Id " + id + " não encontrado");
        }
    }

    @DeleteMapping("/deletarPorId/{id}")
    public ResponseEntity<String> deletarVeiculoPorID(@PathVariable Long id) {
        if (veiculoService.buscarVeiculoPorId(id) != null) {
            veiculoService.buscarVeiculoPorId(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Veiculo deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("id " + id + " não encontrado");
        }
    }

}
