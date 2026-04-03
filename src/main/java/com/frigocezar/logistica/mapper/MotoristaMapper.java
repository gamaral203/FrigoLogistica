package com.frigocezar.logistica.mapper;

import com.frigocezar.logistica.dto.MotoristaDTO;
import com.frigocezar.logistica.model.MotoristaModel;
import org.springframework.stereotype.Component;

@Component
public class MotoristaMapper {

    public MotoristaModel map(MotoristaDTO motoristaDTO) {
        MotoristaModel motoristaModel = new MotoristaModel();
        motoristaModel.setId(motoristaDTO.getId());
        motoristaModel.setNome(motoristaDTO.getNome());
        motoristaModel.setCpf(motoristaDTO.getCpf());
        motoristaModel.setCnh(motoristaDTO.getCnh());
        return motoristaModel;
    }

    public MotoristaDTO map(MotoristaModel motoristaModel) {
        MotoristaDTO motoristaDTO = new MotoristaDTO();
        motoristaDTO.setId(motoristaModel.getId());
        motoristaDTO.setNome(motoristaModel.getNome());
        motoristaDTO.setCpf(motoristaModel.getCpf());
        motoristaDTO.setCnh(motoristaModel.getCnh());
        return motoristaDTO;
    }
}
