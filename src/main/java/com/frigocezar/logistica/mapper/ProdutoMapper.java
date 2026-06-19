package com.frigocezar.logistica.mapper;

import com.frigocezar.logistica.dto.ProdutoDTO;
import com.frigocezar.logistica.model.ProdutoModel;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public ProdutoModel map(ProdutoDTO produtoDTO) {
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setId(produtoDTO.getId());
        produtoModel.setNome(produtoDTO.getNome());
        produtoModel.setDescricao(produtoDTO.getDescricao());
        produtoModel.setPreco(produtoDTO.getPreco());
        return produtoModel;
    }
    public ProdutoDTO map(ProdutoModel produtoModel) {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(produtoModel.getId());
        produtoDTO.setNome(produtoModel.getNome());
        produtoDTO.setDescricao(produtoModel.getDescricao());
        produtoDTO.setPreco(produtoModel.getPreco());
        return produtoDTO;
    }
}
