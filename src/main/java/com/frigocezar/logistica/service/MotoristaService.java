package com.frigocezar.logistica.service;

import com.frigocezar.logistica.dto.MotoristaDTO;
import com.frigocezar.logistica.mapper.MotoristaMapper;
import com.frigocezar.logistica.model.MotoristaModel;
import com.frigocezar.logistica.repository.MotoristaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MotoristaService {

    private final MotoristaRepository motoristaRepository;
    private final MotoristaMapper motoristaMapper;

    public MotoristaService(MotoristaRepository motoristaRepository, MotoristaMapper motoristaMapper) {
        this.motoristaRepository = motoristaRepository;
        this.motoristaMapper = motoristaMapper;
    }

    //cadastra Motorista
    public MotoristaDTO cadastrarMotorista(MotoristaDTO motoristaDTO) {
        MotoristaModel motoristaModel = motoristaMapper.map(motoristaDTO);
        MotoristaModel motorista = motoristaRepository.save(motoristaModel);
        return motoristaMapper.map(motorista);
    }

    //Listar Todos os motoristas

    public List<MotoristaDTO> listarMotoristas() {
        List<MotoristaModel> motoristas = motoristaRepository.findAll();
        return motoristas.stream()
                .map(motoristaMapper::map)
                .collect(Collectors.toList());
    }

    //Buscar por ID

    public MotoristaDTO buscarMotoristaPorId(Long id) {
        Optional<MotoristaModel> motoristaModel = motoristaRepository.findById(id);
        if (motoristaModel.isPresent()) {
            return motoristaMapper.map(motoristaModel.get());
        } else {
            return null;
        }
    }
    // atualizar o motorista por id

    public MotoristaDTO atualizarMotorista(Long id, MotoristaDTO motoristaDTO) {
        Optional<MotoristaModel> motoristaExistente = motoristaRepository.findById(id);
        if (motoristaExistente.isPresent()) {
            MotoristaModel motorista = motoristaExistente.get();
            motorista.setNome(motoristaDTO.getNome());
            motorista.setCnh(motoristaDTO.getCnh());
            motorista.setCpf(motoristaDTO.getCpf());

            MotoristaModel motoristaAtualizado = motoristaRepository.save(motorista);

            return motoristaMapper.map(motoristaAtualizado);

        }
        return null;
    }
    // Deletar Por Id
    public void deleteMotoristaPorId(Long id) {
        motoristaRepository.deleteById(id);
    }
}
