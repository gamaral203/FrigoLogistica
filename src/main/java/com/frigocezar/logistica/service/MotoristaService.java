package com.frigocezar.logistica.service;

import com.frigocezar.logistica.dto.MotoristaDTO;
import com.frigocezar.logistica.exceptions.DuplicateResourceException;
import com.frigocezar.logistica.exceptions.MotoristaNotFoundException;
import com.frigocezar.logistica.mapper.MotoristaMapper;
import com.frigocezar.logistica.model.MotoristaModel;
import com.frigocezar.logistica.repository.MotoristaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MotoristaService {

    private final MotoristaRepository motoristaRepository;
    private final MotoristaMapper motoristaMapper;

    public MotoristaService(MotoristaRepository motoristaRepository, MotoristaMapper motoristaMapper) {
        this.motoristaRepository = motoristaRepository;
        this.motoristaMapper = motoristaMapper;
    }

    @Transactional
    public MotoristaDTO cadastrarMotorista(MotoristaDTO motoristaDTO) {
        log.debug("Cadastrando motorista: {} ", motoristaDTO);
        validarMotorista(motoristaDTO);

        MotoristaModel motoristaModel = motoristaMapper.map(motoristaDTO);
        MotoristaModel motorista = motoristaRepository.save(motoristaModel);

        log.info("Motorista cadastrado com sucesso. id: {} ", motorista.getId());
        return motoristaMapper.map(motorista);
    }


    @Transactional(readOnly = true)
    public List<MotoristaDTO> listarMotoristas() {

        log.debug("Listando motoristas");

        List<MotoristaModel> motoristas = motoristaRepository.findAll();
        log.info("quantidade de motoristas encontrados : {}", motoristas.size());
        return motoristas.stream()
                .map(motoristaMapper::map)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public MotoristaDTO buscarMotoristaPorId(Long id) {
        log.debug("Buscando motorista por id: {}", id);

        MotoristaModel motorista = motoristaRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Motorista não encontrado. id: {}", id);
                    return new MotoristaNotFoundException();
                });
        log.info("Motorista encontrado com sucesso. id: {}", id);
        return motoristaMapper.map(motorista);
    }


    @Transactional
    public MotoristaDTO atualizarMotoristaPorId(Long id, MotoristaDTO motoristaDTO) {
        log.debug("Atualizando motorista por id: {}", id);

        MotoristaModel motorista = motoristaRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Motorista não encontrado. id: {}", id);
                    return new MotoristaNotFoundException();
                });

        motorista.setNome(motoristaDTO.getNome());
        motorista.setCnh(motoristaDTO.getCnh());
        motorista.setCpf(motoristaDTO.getCpf());

        MotoristaModel motoristaAtualizado = motoristaRepository.save(motorista);

        log.info("Motorista atualizado com sucesso. id: {}", id);

        return motoristaMapper.map(motoristaAtualizado);
    }


    @Transactional
    public void deletarMotoristaPorId(Long id) {
        log.debug("Deletando motorista por id: {}", id);
        MotoristaModel motorista = motoristaRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Motorista não encontrado. id: {}", id);
                    return new MotoristaNotFoundException();
                });
        motoristaRepository.deleteById(id);
        log.info("Motorista deletado com sucesso. id: {}", id);
    }


    private void validarMotorista(MotoristaDTO motoristaDTO) {
        log.debug("Validando motorista: {}", motoristaDTO);
        if (motoristaRepository.existsByCnh(motoristaDTO.getCnh())) {
            log.warn("CNH já cadastrada");
            throw new DuplicateResourceException("CNH já cadastrada");
        }
        if(motoristaRepository.existsByCpf(motoristaDTO.getCpf())) {
            log.warn("CPF já cadastrado");
            throw new DuplicateResourceException("CPF já cadastrado");
        }
    }
}
