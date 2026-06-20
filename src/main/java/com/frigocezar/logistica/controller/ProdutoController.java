package com.frigocezar.logistica.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frigocezar.logistica.dto.ProdutoDTO;
import com.frigocezar.logistica.service.ProdutoService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/produto")
@Validated
public class ProdutoController implements com.frigocezar.logistica.docs.ProdutoControllerDoc {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/criar")
    public ResponseEntity<ProdutoDTO> criarProduto(@RequestBody @Valid ProdutoDTO produto) {
        log.debug("Criando produto: {}", produto);

        ProdutoDTO produtoDTO = produtoService.criarProduto(produto);

        log.info("Produto criado com id={}", produtoDTO != null ? produtoDTO.getId() : null);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produtoDTO);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProdutoDTO>> listarProduto() {
        log.debug("Listando produtos");

        List<ProdutoDTO> produtoDTO = produtoService.listarProdutos();

        log.info("Quantidade de produtos retornados={}", produtoDTO != null ? produtoDTO.size() : 0);
        return ResponseEntity.ok(produtoDTO);
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Long id) {
        log.debug("Buscando produto por id={}", id);
        ProdutoDTO produtoDTO = produtoService.buscarProdutoPorId(id);
        log.info("Produto encontrado id={}", produtoDTO != null ? produtoDTO.getId() : null);
        return ResponseEntity.ok(produtoDTO);
    }

    @PutMapping("/editarPorId/{id}")
    public ResponseEntity<ProdutoDTO> editarPorId(@PathVariable Long id, @RequestBody @Valid ProdutoDTO produto) {
        log.debug("Editando produto id={} payload={}", id, produto);
        ProdutoDTO produtoDTO = produtoService.editarProduto(id, produto);
        log.info("Produto editado id={}", produtoDTO != null ? produtoDTO.getId() : null);
        return ResponseEntity.ok(produtoDTO);
    }

    @DeleteMapping("/deletarPorId/{id}")
    public ResponseEntity<String> deletarPorId(@PathVariable Long id) {
        log.debug("Deletando produto id={}", id);
        produtoService.excluirProduto(id);
        log.info("Produto deletado id={}", id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Deletado com sucesso");
    }

}
