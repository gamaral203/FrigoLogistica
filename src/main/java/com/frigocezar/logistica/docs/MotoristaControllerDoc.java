package com.frigocezar.logistica.docs;

import com.frigocezar.logistica.dto.MotoristaDTO;
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

@Tag(name = "Motoristas", description = "Endpoints para gerenciamento de Motoristas")
public interface MotoristaControllerDoc {


    @Operation(summary = "Criar um novo motorista", description = "Rota para criação de um novo motorista")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Motorista cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "CPF ou CNH já cadastrado")
    })
    ResponseEntity<MotoristaDTO> cadastrarMotorista(@RequestBody @Valid MotoristaDTO motorista);


    @Operation(summary = "Listar todos os motoristas", description = "Rota que lista todos os motoristas cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Motorista encontrado"),
            @ApiResponse(responseCode = "404", description = "Motorista não encontrado")
    })
    ResponseEntity<List<MotoristaDTO>> listarMotoristas();


    @Operation(summary = "buscar motorista por Id", description = "Rota que busca o motoristas pelo seu id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Motorista encontrado"),
            @ApiResponse(responseCode = "404", description = "Motorista não encontrado")
    })
    ResponseEntity<MotoristaDTO> buscarMotoristaPorId(@PathVariable @Positive Long id);


    @Operation(summary = "Atualizar motorista por Id", description = "Verifica se o motorista existe e atualiza seus dados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Motorista não encontrado")
    })
    ResponseEntity<MotoristaDTO> atualizarMotorista(@PathVariable @Positive Long id,
                                                    @RequestBody @Valid MotoristaDTO motoristaDTO);


    @Operation(summary = "Deletar motorista", description = "Verifica s eo motorista existe no banco de dados e deleta")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Motorista não encontrado")
    })
    ResponseEntity<String> deletarMotoristaPorId(@PathVariable @Positive Long id);
}
