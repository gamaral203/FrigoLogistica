package com.frigocezar.logistica.service;

import com.frigocezar.logistica.dto.DespesasDTO;
import com.frigocezar.logistica.mapper.DespesasMapper;
import com.frigocezar.logistica.model.DespesasModel;
import com.frigocezar.logistica.repository.DespesasRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DespesasService {

    private final DespesasRepository despesasRepository;
    private final DespesasMapper despesasMapper;

    public DespesasService(DespesasRepository despesasRepository, DespesasMapper despesasMapper) {
        this.despesasRepository = despesasRepository;
        this.despesasMapper = despesasMapper;
    }

    public DespesasDTO novaDespesa(DespesasDTO despesasDTO) {
        log.debug("Criando nova despesa {}", despesasDTO);
        DespesasModel despesasModel = despesasMapper.map(despesasDTO);
        DespesasModel despesas = despesasRepository.save(despesasModel);
        log.info("Despesa criada: {}", despesas);

        return despesasMapper.map(despesas);
    }
}
