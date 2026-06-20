package com.frigocezar.logistica.docs;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.frigocezar.logistica.dto.ProdutoDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@Tag(name = "produtos", description = "Gerenciamento de produtos")
public interface ProdutoControllerDoc {

    @Operation(summary = "Criar produto", description = "Cadastra um novo produto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    ResponseEntity<ProdutoDTO> criarProduto(@RequestBody @Valid ProdutoDTO produto);


    @Operation(summary = "Listar produtos", description = "Retorna todos os produtos cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listagem realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum produto encontrado")
    })
    ResponseEntity<List<ProdutoDTO>> listarProduto();


    @Operation(summary = "Buscar produto por ID", description = "Retorna um produto pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto encontrado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable @Positive Long id);


    @Operation(summary = "Editar produto por ID", description = "Atualiza os dados de um produto existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    ResponseEntity<ProdutoDTO> editarPorId(@PathVariable @Positive Long id, @RequestBody @Valid ProdutoDTO produto);


    @Operation(summary = "Deletar produto por ID", description = "Remove um produto pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    ResponseEntity<String> deletarPorId(@PathVariable @Positive Long id);

}
