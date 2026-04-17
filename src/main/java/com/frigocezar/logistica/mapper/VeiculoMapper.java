package com.frigocezar.logistica.mapper;

import com.frigocezar.logistica.dto.VeiculoDTO;
import com.frigocezar.logistica.model.MotoristaModel;
import com.frigocezar.logistica.model.VeiculoModel;
import org.springframework.stereotype.Component;

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

        return veiculoDTO;
    }
}
