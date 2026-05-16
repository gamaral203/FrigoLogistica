package com.frigocezar.logistica.mapper;

import com.frigocezar.logistica.dto.DespesasDTO;
import com.frigocezar.logistica.model.DespesasModel;
import org.springframework.stereotype.Component;

@Component
public class DespesasMapper {

    public DespesasDTO map(DespesasModel model) {

        DespesasDTO despesasDTO = new DespesasDTO();

        despesasDTO.setId(model.getId());
        despesasDTO.setDescricao(model.getDescricao());
        despesasDTO.setTipoDespesa(model.getTipoDespesa());
        despesasDTO.setPreco(model.getPreco());
        despesasDTO.setFormaPagamento(model.getFormaPagamento());
        despesasDTO.setStatus(model.getStatus());
        despesasDTO.setDataDespesa(model.getDataDespesa());

        despesasDTO.setMotoristaId(model.getMotorista().getId());
        despesasDTO.setVeiculoId(model.getVeiculo().getId());

        return despesasDTO;
    }

    public DespesasModel map(DespesasDTO dto) {

        DespesasModel despesasModel = new DespesasModel();

        despesasModel.setId(dto.getId());
        despesasModel.setDescricao(dto.getDescricao());
        despesasModel.setTipoDespesa(dto.getTipoDespesa());
        despesasModel.setPreco(dto.getPreco());
        despesasModel.setFormaPagamento(dto.getFormaPagamento());
        despesasModel.setStatus(dto.getStatus());
        despesasModel.setDataDespesa(dto.getDataDespesa());

        return despesasModel;
    }
}