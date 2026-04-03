package com.frigocezar.logistica.repository;

import com.frigocezar.logistica.model.MotoristaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoristaRepository extends JpaRepository<MotoristaModel, Long> {
}
