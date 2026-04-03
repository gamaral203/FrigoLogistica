package com.frigocezar.logistica.controller;

import com.frigocezar.logistica.dto.MotoristaDTO;
import com.frigocezar.logistica.service.MotoristaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motoristas")
public class MotoristaController {

    private final MotoristaService motoristaService;

    public MotoristaController(MotoristaService motoristaService) {
        this.motoristaService = motoristaService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<MotoristaDTO> cadastrarMotorista(@RequestBody MotoristaDTO motorista) {
        MotoristaDTO motoristaDTO = motoristaService.cadastrarMotorista(motorista);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(motoristaDTO);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<MotoristaDTO>> listarMotorista() {
        List<MotoristaDTO> motoristas = motoristaService.listarMotoristas();
        return ResponseEntity.ok(motoristas);
    }

    @GetMapping("buscarPorId/{id}")
    public ResponseEntity<?> buscarMotoristaPorId(@PathVariable Long id) {
        MotoristaDTO motoristaDTO = motoristaService.buscarMotoristaPorId(id);

        if (motoristaDTO != null) {
            return ResponseEntity.ok(motoristaDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Id " + id + " nao encontrado");
        }
    }

    @PutMapping("atualizarPorId/{id}")
    public ResponseEntity<?> atualizarMotorista(@PathVariable Long id,
                                                @RequestBody MotoristaDTO motoristaDTO) {

        MotoristaDTO motoristaAtualizado = motoristaService.atualizarMotoristaPorId(id, motoristaDTO);

        if (motoristaAtualizado != null) {
            return ResponseEntity.ok(motoristaAtualizado);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Motorista não encontrado");
    }

    @DeleteMapping("/deletarPorId/{id}")
    public ResponseEntity<String> deletarMotoristaPorId(@PathVariable Long id) {
        if (motoristaService.buscarMotoristaPorId(id) != null) {
            motoristaService.deletarMotoristaPorId(id);
            return ResponseEntity.ok("Motorista deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Id " + id + " nao encontrado");
        }
    }
}
