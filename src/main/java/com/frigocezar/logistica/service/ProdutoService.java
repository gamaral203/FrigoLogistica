package com.frigocezar.logistica.service;

import com.frigocezar.logistica.dto.ProdutoDTO;
import com.frigocezar.logistica.exceptions.ProdutoNotFoundException;
import com.frigocezar.logistica.mapper.ProdutoMapper;
import com.frigocezar.logistica.model.ProdutoModel;
import com.frigocezar.logistica.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
        log.debug("Criando produto: {}", produtoDTO);
        ProdutoModel produtoModel = produtoMapper.map(produtoDTO);
        produtoModel = produtoRepository.save(produtoModel);
        log.info("Produto criado com sucesso. id: {}", produtoModel.getId());
        return produtoMapper.map(produtoModel);
    }

    public List<ProdutoDTO> listarProdutos() {
        log.debug("Listando produtos");
        List<ProdutoModel> produtos = produtoRepository.findAll();
        log.info("quantidade de produtos encontrados: {}", produtos.size());
        return produtos.stream()
                .map(produtoMapper::map)
                .collect(Collectors.toList());
    }

    public ProdutoDTO buscarProdutoPorId(Long id) {
        log.debug("Buscando produto por id: {}", id);
        ProdutoModel produto = produtoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Produto não encontrado. id: {}", id);
                    return new ProdutoNotFoundException();
                });
        log.info("Produto encontrado com sucesso. id: {}", id);
        return produtoMapper.map(produto);
    }

    public ProdutoDTO editarProduto(Long id, ProdutoDTO produtoDTO) {
        log.debug("Editando produto por id: {}", id);
        ProdutoModel produto = produtoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Produto não encontrado. id: {}", id);
                    return new ProdutoNotFoundException();
                });
        produto.setTipoProduto(produtoDTO.getTipoProduto());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        produto.setTipoProduto(produtoDTO.getTipoProduto());
        produto.setQuantidadeEstoque(produtoDTO.getQuantidadeEstoque());

        ProdutoModel produtoAtualido = produtoRepository.save(produto);

        log.info("Produto atualizado com sucesso. id: {}", id);

        return produtoMapper.map(produtoAtualido);


    }

    public void excluirProduto(Long id) {

        ProdutoModel produto = produtoRepository.findById(id)
                .orElseThrow(() -> {
                    return new ProdutoNotFoundException();
                });
        produtoRepository.delete(produto);
    }
}
