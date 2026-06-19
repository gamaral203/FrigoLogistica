package com.frigocezar.logistica.service;

import com.frigocezar.logistica.dto.ProdutoDTO;
import com.frigocezar.logistica.exceptions.ProdutoNotFoundException;
import com.frigocezar.logistica.mapper.ProdutoMapper;
import com.frigocezar.logistica.model.ProdutoModel;
import com.frigocezar.logistica.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoService(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    @Transactional
    public ProdutoDTO criarProduto(ProdutoDTO produtoDTO) {
        ProdutoModel produtoModel = produtoMapper.map(produtoDTO);
        produtoModel = produtoRepository.save(produtoModel);
        return produtoMapper.map(produtoModel);
    }

    public List<ProdutoDTO> listarProdutos() {
        List<ProdutoModel> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(produtoMapper::map)
                .collect(Collectors.toList());
    }

    public ProdutoDTO buscarProdutoPorId(Long id) {

        ProdutoModel produto = produtoRepository.findById(id)
                .orElseThrow(() -> {
                    return new ProdutoNotFoundException();
                });
        return produtoMapper.map(produto);
    }

    public ProdutoDTO EditarProduto(Long id, ProdutoDTO produtoDTO) {
        ProdutoModel produto = produtoRepository.findById(id)
                .orElseThrow(() -> {
                    return new ProdutoNotFoundException();
                });
    }
}
