package com.frigocezar.logistica.service;

import com.frigocezar.logistica.dto.VeiculoDTO;
import com.frigocezar.logistica.exceptions.BusinessException;
import com.frigocezar.logistica.exceptions.VeiculoNotFoundException;
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
        log.debug("Iniciando cadastro de Veiculo: {} ", veiculoDTO);

        validarCadastro(veiculoDTO);

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
        VeiculoModel veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Veículo Não encontrado. id: {}", id);
                    return new VeiculoNotFoundException();
                });
        return veiculoMapper.map(veiculo);
    }

    public VeiculoDTO atualizarVeiculo(Long id, VeiculoDTO veiculoDTO) {
        log.debug("Atualizando Veiculo com id: {}", id);

        VeiculoModel veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Veículo não encontrado. id: {}", id);
                    return new VeiculoNotFoundException();
                });


        veiculo.setMarca(veiculoDTO.getMarca());
        veiculo.setPlaca(veiculoDTO.getPlaca());
        veiculo.setModelo(veiculoDTO.getModelo());
        veiculo.setTipoVeiculo(veiculoDTO.getTipoVeiculo());
        veiculo.setRenavam(veiculoDTO.getRenavam());
        veiculo.setCor(veiculoDTO.getCor());

        VeiculoModel veiculoAtualizado = veiculoRepository.save(veiculo);

        log.info("Veiculo atualizado com sucesso id: {}", id);

        return veiculoMapper.map(veiculoAtualizado);

    }

    public void deletarVeiculo(Long id) {
        log.debug("Deletando Veiculo com id: {}", id);

        VeiculoModel veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Veiculo não encontrado. id: {}", id);
                    return new VeiculoNotFoundException();
                });
        veiculoRepository.deleteById(id);

        log.info("Veiculo deletado com sucesso id: {}", id);
    }

    private void validarCadastro(VeiculoDTO veiculoDTO) {
        if (veiculoRepository.existsByPlaca(veiculoDTO.getPlaca())) {
            log.warn("Placa já cadastrada");
            throw new BusinessException(
                    "Essa placa já foi cadastrada"
            );
        }
        if (veiculoRepository.existsByRenavam(veiculoDTO.getRenavam())) {
            log.warn("Renavam já cadastrado");
            throw  new BusinessException(
                    "esse renavam já está cadastrado"
            );
        }
    }
}
