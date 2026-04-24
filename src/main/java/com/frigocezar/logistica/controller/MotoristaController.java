package com.frigocezar.logistica.controller;

import com.frigocezar.logistica.dto.MotoristaDTO;
import com.frigocezar.logistica.service.MotoristaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/motoristas")
public class MotoristaController {

    private final MotoristaService motoristaService;

    public MotoristaController(MotoristaService motoristaService) {
        this.motoristaService = motoristaService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<MotoristaDTO> cadastrarMotorista(@RequestBody MotoristaDTO motorista) {

        log.info("cadastrando Motorista: {} ", motorista);

        MotoristaDTO motoristaDTO = motoristaService.cadastrarMotorista(motorista);

        log.info("motorista cadastrado com sucesso. ID: {} ", motoristaDTO.getId());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(motoristaDTO);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<MotoristaDTO>> listarMotorista() {

        log.info("Listando motoristas: ");

        List<MotoristaDTO> motoristas = motoristaService.listarMotoristas();

        log.info("quantidade de motoristas encontrados: {} ", motoristas.size());

        return ResponseEntity.ok(motoristas);
    }

    @GetMapping("buscarPorId/{id}")
    public ResponseEntity<?> buscarMotoristaPorId(@PathVariable Long id) {

        log.info("Buscando motorista por ID: {}", id);

        MotoristaDTO motoristaDTO = motoristaService.buscarMotoristaPorId(id);

        if (motoristaDTO != null) {
            return ResponseEntity.ok(motoristaDTO);
        } else {

            log.warn("motorista com o id: {}", id);

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Id " + id + " nao encontrado");
        }
    }

    @PutMapping("atualizarPorId/{id}")
    public ResponseEntity<?> atualizarMotorista(@PathVariable Long id,
                                                @RequestBody MotoristaDTO motoristaDTO) {
        log.info("Buscando motorista por ID: {}", id);

        MotoristaDTO motoristaAtualizado = motoristaService.atualizarMotoristaPorId(id, motoristaDTO);

        if (motoristaAtualizado != null) {

            log.info("Motorista atualizado com sucesso. ID: {} ", id);

            return ResponseEntity.ok(motoristaAtualizado);

        } else {

            log.warn("não foi possível encontrar o motorista com id : {}", id);

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Motorista não encontrado");
        }
    }

    @DeleteMapping("/deletarPorId/{id}")
    public ResponseEntity<String> deletarMotoristaPorId(@PathVariable Long id) {
        log.info("Buscando motorista por ID: {}", id);

        if (motoristaService.buscarMotoristaPorId(id) != null) {
            motoristaService.deletarMotoristaPorId(id);

            log.info("Motorista deletado com sucesso. ID: {} ", id);

            return ResponseEntity.ok("Motorista deletado com sucesso");
        } else {
            log.warn("não foi possível encontrar o motorista com id : {}", id);

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Id " + id + " nao encontrado");
        }
    }
}
