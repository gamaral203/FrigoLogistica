package com.frigocezar.logistica.controller;

import com.frigocezar.logistica.docs.DespesasControllerDoc;
import com.frigocezar.logistica.dto.DespesasDTO;
import com.frigocezar.logistica.dto.MotoristaDTO;
import com.frigocezar.logistica.service.DespesasService;
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
@RequestMapping("/despesas")
@Validated
public class DespesasController implements DespesasControllerDoc {

    private final DespesasService despesasService;

    public DespesasController(DespesasService despesasService) {
        this.despesasService = despesasService;
    }

    @PostMapping("/cadastrarDespesa")
    public ResponseEntity<DespesasDTO> cadastrarDespesa(@RequestBody @Valid DespesasDTO despesas) {

        log.info("Cadastrando despesa: {}", despesas);
        DespesasDTO despesasDTO = despesasService.novaDespesa(despesas);
        log.info("Despesa cadastrada: {}", despesasDTO.getId());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(despesasDTO);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<DespesasDTO>> listarDespesas() {
        log.debug("Listando despesas: ");

        List<DespesasDTO> despesasDTO = despesasService.listaDespesas();

        log.info("Quantidade de despesas encontada: {}", despesasDTO.size());

        return ResponseEntity.ok(despesasDTO);
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<DespesasDTO> buscarPorId(@Positive @PathVariable Long id) {
        log.debug("Buscando despesa com id {}", id);

        DespesasDTO despesasDTO = despesasService.buscarDespesaPorId(id);

        log.info("Despesas encontrada: {}", despesasDTO.getId());

        return ResponseEntity.ok(despesasDTO);
    }


    @PutMapping("/atualizarDespesaPorId/{id}")
    public ResponseEntity<DespesasDTO> atualizarDespesa(@PathVariable @Positive long id,
                                                        @RequestBody @Valid DespesasDTO despesasDTO) {
        log.debug("Atualizando despesa: {}", id);
        DespesasDTO despesasAtualizada = despesasService.atualizarDespesa(id, despesasDTO);
        log.info("Despesa atualizada: {}", despesasAtualizada.getId());

        return ResponseEntity.ok(despesasAtualizada);
    }

    @DeleteMapping("/deletarPorId/{id}")
    public ResponseEntity<String> deletarPorId(@PathVariable @Positive Long id) {
        log.debug("Deletando despesa com id {}", id);

        despesasService.deletarDespesa(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Despesa deletada com sucesso! ");

    }
}
