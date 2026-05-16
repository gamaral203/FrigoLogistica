package com.frigocezar.logistica.repository;

import com.frigocezar.logistica.model.DespesasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesasRepository extends JpaRepository<DespesasModel,Long> {
}
