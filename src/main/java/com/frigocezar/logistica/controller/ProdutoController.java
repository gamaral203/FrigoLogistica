package com.frigocezar.logistica.controller;

import com.frigocezar.logistica.dto.ProdutoDTO;
import com.frigocezar.logistica.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produto")
@Validated
public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/criar")
    public ResponseEntity<ProdutoDTO> criarProduto(@RequestBody @Valid ProdutoDTO produto) {

        ProdutoDTO produtoDTO = produtoService.criarProduto(produto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produtoDTO);
    }

}
