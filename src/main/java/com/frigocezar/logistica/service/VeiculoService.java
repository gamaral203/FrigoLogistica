package com.frigocezar.logistica.service;

import com.frigocezar.logistica.dto.VeiculoDTO;
import com.frigocezar.logistica.mapper.VeiculoMapper;
import com.frigocezar.logistica.model.VeiculoModel;
import com.frigocezar.logistica.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VeiculoService {

    private final VeiculoMapper veiculoMapper;
    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoMapper veiculoMapper, VeiculoRepository veiculoRepository) {
        this.veiculoMapper = veiculoMapper;
        this.veiculoRepository = veiculoRepository;
    }


    private VeiculoDTO criarVeiculo(VeiculoDTO veiculoDTO) {
        VeiculoModel veiculoModel = veiculoMapper.map(veiculoDTO);
        VeiculoModel veiculo = veiculoRepository.save(veiculoModel);
        return veiculoMapper.map(veiculo);
    }


    public List<VeiculoDTO> listarVeiculos() {
        List<VeiculoModel> veiculos = veiculoRepository.findAll();
        return veiculos.stream()
                .map(veiculoMapper::map)
                .collect(Collectors.toList());
    }


    public VeiculoDTO buscarVeiculoPorId(Long id) {
        Optional<VeiculoModel> veiculo = veiculoRepository.findById(id);
        if (veiculo.isPresent()) {
            return veiculoMapper.map(veiculo.get());
        } else {
            return null;
        }
    }

    public VeiculoDTO AtualizarVeiculo(Long id, VeiculoDTO veiculoDTO) {
        Optional<VeiculoModel> veiculoExistente = veiculoRepository.findById(id);

        if (veiculoExistente.isPresent()) {
            VeiculoModel veiculo = veiculoExistente.get();
            veiculo.setMarca(veiculoDTO.getMarca());
            veiculo.setPlaca(veiculoDTO.getPlaca());
            veiculo.setModelo(veiculoDTO.getModelo());
            veiculo.setTipoVeiculo(veiculoDTO.getTipoVeiculo());
            veiculo.setRenvam(veiculoDTO.getRenvam());
            veiculo.setCor(veiculoDTO.getCor());

            VeiculoModel veiculoAtualizado = veiculoRepository.save(veiculo);
            return veiculoMapper.map(veiculoAtualizado);

        } else {
            return null;
        }
    }

    public void DeletarVeiculo(Long id) {
        veiculoRepository.deleteById(id);
    }
}
