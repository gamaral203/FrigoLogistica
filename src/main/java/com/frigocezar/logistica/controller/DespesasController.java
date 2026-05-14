package com.frigocezar.logistica.controller;

import com.frigocezar.logistica.dto.DespesasDTO;
import com.frigocezar.logistica.service.DespesasService;
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
public class DespesasController {

    private final DespesasService despesasService;

    public DespesasController(DespesasService despesasService) {
        this.despesasService = despesasService;
    }

    @PostMapping("/cadastrarDespesa")
    public ResponseEntity<DespesasDTO> cadastrarDespesa(@RequestBody @Validated DespesasDTO  despesas) {

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

}
