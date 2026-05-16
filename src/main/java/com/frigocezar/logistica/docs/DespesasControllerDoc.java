package com.frigocezar.logistica.docs;

import com.frigocezar.logistica.dto.DespesasDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "despesas", description = "Gerenciamento de despesas")
public interface DespesasControllerDoc {

    @Operation(
            summary = "Cadastrar despesa",
            description = "Cadastra uma nova despesa no sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Despesa cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    ResponseEntity<DespesasDTO> cadastrarDespesa(
            @RequestBody @Valid DespesasDTO despesa
    );

    @Operation(
            summary = "Listar despesas",
            description = "Retorna todas as despesas cadastradas"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listagem realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma despesa encontrada")
    })
    ResponseEntity<List<DespesasDTO>> listarDespesas();

    @Operation(
            summary = "Buscar despesa por ID",
            description = "Retorna uma despesa pelo seu ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Despesa encontrada"),
            @ApiResponse(responseCode = "404", description = "Despesa não encontrada")
    })
    ResponseEntity<DespesasDTO> buscarDespesaPorId(
            @PathVariable Long id
    );

    @Operation(
            summary = "Editar despesa por ID",
            description = "Atualiza os dados de uma despesa existente"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Despesa atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Despesa não encontrada")
    })
    ResponseEntity<DespesasDTO> editarDespesaPorId(
            @PathVariable Long id,
            @RequestBody @Valid DespesasDTO despesa
    );

    @Operation(
            summary = "Excluir despesa por ID",
            description = "Remove uma despesa do sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Despesa removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Despesa não encontrada")
    })
    ResponseEntity<Void> deletarDespesaPorId(
            @PathVariable Long id
    );
}