package com.frigocezar.logistica.repository;

import com.frigocezar.logistica.model.VeiculoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<VeiculoModel, Long> {
    boolean existsByPlaca(String placa);
    boolean existsByRenavam(String renavam);
}
