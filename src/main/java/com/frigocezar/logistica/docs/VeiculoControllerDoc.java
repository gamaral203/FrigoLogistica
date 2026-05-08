package com.frigocezar.logistica.docs;

import com.frigocezar.logistica.dto.VeiculoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "veiculos", description = "Gerenciamento de veiculos")
public interface VeiculoControllerDoc {

    @Operation(summary = "Cadastrar veículo", description = "Cadastra um novo veículo no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Veículo cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    ResponseEntity<VeiculoDTO> cadastrarVeiculo(@RequestBody @Valid VeiculoDTO veiculo);


    @Operation(summary = "Listar veículos", description = "Retorna todos os veículos cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum veículo encontrado")
    })
    ResponseEntity<List<VeiculoDTO>> listarVeiculo();


    @Operation(summary = "Buscar veículo por ID", description = "Retorna um veículo pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Veículo encontrado"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })
    ResponseEntity<VeiculoDTO> buscarVeiculoPorID(@PathVariable Long id);

    @Operation(summary = "Editar veículo por ID", description = "Atualiza os dados de um veículo existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Veículo atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado")

    })
    ResponseEntity<VeiculoDTO> editarVeiculoPorID(@PathVariable @Valid Long id,
                                                  @RequestBody VeiculoDTO veiculo);


    @Operation(summary = "Deletar veículo por ID", description = "Remove um veículo do sistema pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Veículo deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })
    ResponseEntity<String> deletarVeiculoPorID(@PathVariable Long id);

}
