package com.frigocezar.logistica.mapper;

import com.frigocezar.logistica.dto.ProdutoDTO;
import com.frigocezar.logistica.model.ProdutoModel;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public ProdutoModel map(ProdutoDTO produtoDTO) {
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setId(produtoDTO.getId());
        produtoModel.setNome(produtoDTO.getNome());
        produtoModel.setDescricao(produtoDTO.getDescricao());
        produtoModel.setPreco(produtoDTO.getPreco());
        produtoModel.setQuantidadeEstoque(produtoDTO.getQuantidadeEstoque());
        produtoModel.setTipoProduto(produtoDTO.getTipoProduto());
        return produtoModel;
    }
    public ProdutoDTO map(ProdutoModel produtoModel) {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(produtoModel.getId());
        produtoDTO.setNome(produtoModel.getNome());
        produtoDTO.setDescricao(produtoModel.getDescricao());
        produtoDTO.setPreco(produtoModel.getPreco());
        produtoDTO.setTipoProduto(produtoModel.getTipoProduto());
        produtoDTO.setQuantidadeEstoque(produtoModel.getQuantidadeEstoque());
        return produtoDTO;
    }
}
