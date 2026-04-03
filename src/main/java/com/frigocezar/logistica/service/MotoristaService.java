package com.frigocezar.logistica.service;

import com.frigocezar.logistica.dto.MotoristaDTO;
import com.frigocezar.logistica.mapper.MotoristaMapper;
import com.frigocezar.logistica.model.MotoristaModel;
import com.frigocezar.logistica.repository.MotoristaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public MotoristaDTO atualizarMotorista(MotoristaDTO motoristaDTO) {

    }

    public void deleteMotoristaPorId(Long id) {
        motoristaRepository.deleteById(id);
    }
}
