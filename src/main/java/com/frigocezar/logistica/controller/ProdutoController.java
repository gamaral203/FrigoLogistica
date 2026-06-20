package com.frigocezar.logistica.controller;

import com.frigocezar.logistica.dto.ProdutoDTO;
import com.frigocezar.logistica.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/listar")
    public ResponseEntity<List<ProdutoDTO>> listarProduto() {
        List<ProdutoDTO> produtoDTO = produtoService.listarProdutos();
        return ResponseEntity.ok(produtoDTO);
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Long id) {
        ProdutoDTO produtoDTO = produtoService.buscarProdutoPorId(id);
        return ResponseEntity.ok(produtoDTO);
    }

    @PutMapping("/editarPorId/{id}")
    public ResponseEntity<ProdutoDTO> editarPorId(@PathVariable Long id, @RequestBody @Valid ProdutoDTO produto) {
        ProdutoDTO produtoDTO = produtoService.editarProduto(id, produto);
        return ResponseEntity.ok(produtoDTO);
    }

    @DeleteMapping("/deletarPorId/{id}")
    public ResponseEntity<String> deletarPorId(@PathVariable Long id) {
        produtoService.excluirProduto(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Deletado com sucesso");
    }

}
