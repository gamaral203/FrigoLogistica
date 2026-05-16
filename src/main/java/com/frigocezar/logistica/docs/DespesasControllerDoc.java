package com.frigocezar.logistica.docs;

import com.frigocezar.logistica.dto.DespesasDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
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
            @RequestBody @Valid DespesasDTO despesas
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
            description = "Busca uma despesa pelo ID informado"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Despesa encontrada"),
            @ApiResponse(responseCode = "404", description = "Despesa não encontrada")
    })
    ResponseEntity<DespesasDTO> buscarPorId(
            @PathVariable @Positive Long id
    );

    @Operation(
            summary = "Atualizar despesa",
            description = "Atualiza uma despesa existente pelo ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Despesa atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Despesa não encontrada")
    })
    ResponseEntity<DespesasDTO> atualizarDespesa(
            @PathVariable @Positive long id,
            @RequestBody @Valid DespesasDTO despesasDTO
    );

    @Operation(
            summary = "Deletar despesa",
            description = "Remove uma despesa do sistema pelo ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Despesa deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Despesa não encontrada")
    })
    ResponseEntity<String> deletarPorId(
            @PathVariable @Positive Long id
    );
}