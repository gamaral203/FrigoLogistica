package com.frigocezar.logistica.service;

import com.frigocezar.logistica.dto.DespesasDTO;
import com.frigocezar.logistica.dto.MotoristaDTO;
import com.frigocezar.logistica.exceptions.DespesasNotFoundException;
import com.frigocezar.logistica.exceptions.MotoristaNotFoundException;
import com.frigocezar.logistica.exceptions.VeiculoNotFoundException;
import com.frigocezar.logistica.mapper.DespesasMapper;
import com.frigocezar.logistica.model.DespesasModel;
import com.frigocezar.logistica.model.MotoristaModel;
import com.frigocezar.logistica.model.VeiculoModel;
import com.frigocezar.logistica.repository.DespesasRepository;
import com.frigocezar.logistica.repository.MotoristaRepository;
import com.frigocezar.logistica.repository.VeiculoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DespesasService {

    private final DespesasRepository despesasRepository;
    private final DespesasMapper despesasMapper;
    private final MotoristaRepository motoristaRepository;
    private final VeiculoRepository veiculoRepository;

    public DespesasService(DespesasRepository despesasRepository, DespesasMapper despesasMapper, MotoristaRepository motoristaRepository, VeiculoRepository veiculoRepository) {
        this.despesasRepository = despesasRepository;
        this.despesasMapper = despesasMapper;
        this.motoristaRepository = motoristaRepository;
        this.veiculoRepository = veiculoRepository;
    }

    @Transactional
    public DespesasDTO novaDespesa(DespesasDTO despesasDTO) {
        log.debug("Criando nova despesa {}", despesasDTO);

        MotoristaModel motorista = motoristaRepository.findById(despesasDTO.getMotoristaId())
                .orElseThrow(MotoristaNotFoundException::new);

        VeiculoModel veiculo = veiculoRepository.findById(despesasDTO.getVeiculoId())
                .orElseThrow(VeiculoNotFoundException::new);

        DespesasModel despesasModel = despesasMapper.map(despesasDTO);

        despesasModel.setMotorista(motorista);
        despesasModel.setVeiculo(veiculo);
        DespesasModel despesas = despesasRepository.save(despesasModel);

        log.info("Despesa criada: {}", despesas);

        return despesasMapper.map(despesas);
    }

    @Transactional(readOnly = true)
    public List<DespesasDTO> listaDespesas() {
        log.debug("Listando despesas");
        List<DespesasModel> despesas = despesasRepository.findAll();
        log.info("Quantidade de despesas encontradas {}", despesas.size());
        return despesas.stream()
                .map(despesasMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DespesasDTO buscarDespesaPorId(Long id) {
        log.debug("Buscando despesa com id {}", id);
        DespesasModel despesasModel = despesasRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Despesa não encontrada {}", id);
                    return new DespesasNotFoundException();
                });
        log.info("Despesa encontrada, id: {} ", id);
        return despesasMapper.map(despesasModel);
    }

    @Transactional
    public DespesasDTO atualizarDespesa(Long id, DespesasDTO despesasDto) {
        log.debug("Atualizando despesa com id {}", id);

        DespesasModel despesasModel = despesasRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Despesa não encontrada {}", id);
                    return new DespesasNotFoundException();
                });

        despesasModel.setDescricao(despesasDto.getDescricao());
        despesasModel.setDataDespesa(despesasDto.getDataDespesa());
        despesasModel.setFormaPagamento(despesasDto.getFormaPagamento());
        despesasModel.setPreco(despesasDto.getPreco());
        despesasModel.setStatus(despesasDto.getStatus());
        despesasModel.setTipoDespesa(despesasDto.getTipoDespesa());

        log.info("Despesa atualizada com id {}", id);
        despesasRepository.save(despesasModel);

        return despesasMapper.map(despesasModel);

    }

    @Transactional
    public void deletarDespesa(Long id) {
        log.debug("Excluindo despesa com id {}", id);
        DespesasModel despesasModel = despesasRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Despesa não encontrada {}", id);
                    return new DespesasNotFoundException();
                });
        despesasRepository.delete(despesasModel);
        log.info("Despesa excluida com id {}", id);
    }
}
