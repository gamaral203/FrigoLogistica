package com.frigocezar.logistica.mapper;

import com.frigocezar.logistica.dto.MotoristaDTO;
import com.frigocezar.logistica.model.MotoristaModel;
import com.frigocezar.logistica.model.VeiculoModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

        List<Long> veiculosIds = new ArrayList<>();

        if (motoristaModel.getVeiculos() != null) {
            for (VeiculoModel veiculo : motoristaModel.getVeiculos()) {
                veiculosIds.add(veiculo.getId());
            }
        }
        motoristaDTO.setVeiculosIds(veiculosIds);

        return motoristaDTO;
    }
}
