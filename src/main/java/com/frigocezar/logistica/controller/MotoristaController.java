package com.frigocezar.logistica.controller;

import com.frigocezar.logistica.docs.MotoristaControllerDoc;
import com.frigocezar.logistica.dto.MotoristaDTO;
import com.frigocezar.logistica.service.MotoristaService;
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
public class MotoristaController implements MotoristaControllerDoc {

    private final MotoristaService motoristaService;

    public MotoristaController(MotoristaService motoristaService) {
        this.motoristaService = motoristaService;
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<MotoristaDTO> cadastrarMotorista(@RequestBody @Valid MotoristaDTO motorista) {

        log.debug("cadastrando Motorista: {} ", motorista);

        MotoristaDTO motoristaDTO = motoristaService.cadastrarMotorista(motorista);

        log.info("motorista cadastrado com sucesso. ID: {} ", motoristaDTO.getId());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(motoristaDTO);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<MotoristaDTO>> listarMotoristas() {

        log.debug("Listando motoristas: ");

        List<MotoristaDTO> motoristas = motoristaService.listarMotoristas();

        log.info("quantidade de motoristas encontrados: {} ", motoristas.size());

        return ResponseEntity.ok(motoristas);
    }

    @GetMapping("buscarPorId/{id}")
    public ResponseEntity<MotoristaDTO> buscarMotoristaPorId(@Positive @PathVariable Long id) {
        log.debug("Buscando motorista por ID: {}", id);
        MotoristaDTO motoristaDTO = motoristaService.buscarMotoristaPorId(id);
        log.info("motorista por ID: {}", motoristaDTO);
        return ResponseEntity.ok(motoristaDTO);
    }

    @PutMapping("atualizarPorId/{id}")
    public ResponseEntity<MotoristaDTO> atualizarMotorista(@PathVariable @Positive Long id,
                                                           @RequestBody @Valid MotoristaDTO motoristaDTO) {
        log.debug("Atualizando Motorista: {}", id);
        MotoristaDTO motoristaAtualizado = motoristaService.atualizarMotoristaPorId(id, motoristaDTO);
        log.info("Motorista atualizado: {}", motoristaAtualizado.getId());
        return ResponseEntity.ok(motoristaAtualizado);
    }

    @DeleteMapping("/deletarPorId/{id}")
    public ResponseEntity<String> deletarMotoristaPorId(@PathVariable @Positive Long id) {
        log.debug("Deletando motorista por ID: {}", id);

        motoristaService.deletarMotoristaPorId(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Motorista deletado com sucesso");

    }
}
