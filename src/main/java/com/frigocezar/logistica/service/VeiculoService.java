package com.frigocezar.logistica.service;

import com.frigocezar.logistica.dto.VeiculoDTO;
import com.frigocezar.logistica.mapper.VeiculoMapper;
import com.frigocezar.logistica.model.VeiculoModel;
import com.frigocezar.logistica.repository.VeiculoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class VeiculoService {

    private final VeiculoMapper veiculoMapper;
    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoMapper veiculoMapper, VeiculoRepository veiculoRepository) {
        this.veiculoMapper = veiculoMapper;
        this.veiculoRepository = veiculoRepository;
    }


    public VeiculoDTO cadastrarVeiculo(VeiculoDTO veiculoDTO) {
        log.debug("Iniciando cadastro de Veiculo", veiculoDTO);

        VeiculoModel veiculoModel = veiculoMapper.map(veiculoDTO);
        VeiculoModel veiculo = veiculoRepository.save(veiculoModel);

        log.info("Veiculo cadastrado com sucesso id: {}", veiculo.getId());
        return veiculoMapper.map(veiculo);
    }


    public List<VeiculoDTO> listarVeiculos() {
        log.debug("Iniciando listagem de Veiculos");
        List<VeiculoModel> veiculos = veiculoRepository.findAll();
        log.info("quantidade de veículos encontrados: {}", veiculos.size());
        return veiculos.stream()
                .map(veiculoMapper::map)
                .collect(Collectors.toList());
    }


    public VeiculoDTO buscarVeiculoPorId(Long id) {
        log.debug("Buscando veiculo por id: {}", id);
        Optional<VeiculoModel> veiculo = veiculoRepository.findById(id);
        if (veiculo.isPresent()) {
            log.info("Veiculo encontrado com sucesso id: {}", id);
            return veiculoMapper.map(veiculo.get());
        } else {
            log.warn("Veículo Não encontrado. id: {}", id);
            return null;
        }
    }

    public VeiculoDTO AtualizarVeiculo(Long id, VeiculoDTO veiculoDTO) {
        Optional<VeiculoModel> veiculoExistente = veiculoRepository.findById(id);
        log.debug("Atualizando Veiculo com id: {}", id);

        if (veiculoExistente.isPresent()) {
            VeiculoModel veiculo = veiculoExistente.get();
            veiculo.setMarca(veiculoDTO.getMarca());
            veiculo.setPlaca(veiculoDTO.getPlaca());
            veiculo.setModelo(veiculoDTO.getModelo());
            veiculo.setTipoVeiculo(veiculoDTO.getTipoVeiculo());
            veiculo.setRenavam(veiculoDTO.getRenavam());
            veiculo.setCor(veiculoDTO.getCor());

            VeiculoModel veiculoAtualizado = veiculoRepository.save(veiculo);

            log.info("Veiculo atualizado com sucesso id: {}", id);

            return veiculoMapper.map(veiculoAtualizado);

        } else {
            log.warn("Veículo não encontrado. id: {}", id);
            return null;
        }
    }

    public void DeletarVeiculo(Long id) {
        log.debug("Deletando Veiculo com id: {}", id);
        veiculoRepository.deleteById(id);
        log.info("Veiculo deletado com sucesso id: {}", id);
    }
}
