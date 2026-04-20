package com.frigocezar.logistica.mapper;

import com.frigocezar.logistica.dto.VeiculoDTO;
import com.frigocezar.logistica.model.MotoristaModel;
import com.frigocezar.logistica.model.VeiculoModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VeiculoMapper {

    public VeiculoModel map(VeiculoDTO veiculoDTO) {
        VeiculoModel veiculoModel = new VeiculoModel();
        veiculoModel.setId(veiculoDTO.getId());
        veiculoModel.setTipoVeiculo(veiculoDTO.getTipoVeiculo());
        veiculoModel.setMarca(veiculoDTO.getMarca());
        veiculoModel.setPlaca(veiculoDTO.getPlaca());
        veiculoModel.setModelo(veiculoDTO.getModelo());
        veiculoModel.setCor(veiculoDTO.getCor());
        veiculoModel.setRenavam(veiculoDTO.getRenavam());

        List<MotoristaModel> motoristas = new ArrayList<>();

        if (veiculoDTO.getMotoristasIds() != null) {
            for (Long id : veiculoDTO.getMotoristasIds()) {
                MotoristaModel motorista = new MotoristaModel();
                motorista.setId(id);
                motoristas.add(motorista);
            }
        }

        veiculoModel.setMotoristas(motoristas);

        return veiculoModel;
    }

    public VeiculoDTO map(VeiculoModel veiculoModel) {
        VeiculoDTO veiculoDTO = new VeiculoDTO();
        veiculoDTO.setId(veiculoModel.getId());
        veiculoDTO.setTipoVeiculo(veiculoModel.getTipoVeiculo());
        veiculoDTO.setMarca(veiculoModel.getMarca());
        veiculoDTO.setPlaca(veiculoModel.getPlaca());
        veiculoDTO.setModelo(veiculoModel.getModelo());
        veiculoDTO.setCor(veiculoModel.getCor());
        veiculoDTO.setRenavam(veiculoModel.getRenavam());

        List<Long> motoristasIds = new ArrayList<>();

        if (veiculoModel.getMotoristas() != null) {
            for (MotoristaModel motorista : veiculoModel.getMotoristas()) {
                motoristasIds.add(motorista.getId());
            }
        }

        veiculoDTO.setMotoristasIds(motoristasIds);


        return veiculoDTO;
    }
}
