package com.frigocezar.logistica.repository;

import com.frigocezar.logistica.enums.TipoDespesa;
import com.frigocezar.logistica.model.DespesasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface DespesasRepository extends JpaRepository<DespesasModel, Long> {

    @Query("""
        SELECT d.motorista.nome, SUM(d.preco)
        FROM DespesasModel d
        WHERE MONTH(d.dataDespesa) = :mes
        AND YEAR(d.dataDespesa) = :ano
        GROUP BY d.motorista.nome
        ORDER BY SUM(d.preco) DESC
    """)
    List<Object[]> motoristaMaisDespesas(
            @Param("mes") Integer mes,
            @Param("ano") Integer ano
    );

    @Query("""
        SELECT d.veiculo.placa, SUM(d.preco)
        FROM DespesasModel d
        WHERE MONTH(d.dataDespesa) = :mes
        AND YEAR(d.dataDespesa) = :ano
        GROUP BY d.veiculo.placa
        ORDER BY SUM(d.preco) DESC
    """)
    List<Object[]> veiculoMaisCustoso(
            @Param("mes") Integer mes,
            @Param("ano") Integer ano
    );

    @Query("""
        SELECT COALESCE(SUM(d.preco), 0)
        FROM DespesasModel d
        WHERE d.tipoDespesa = com.frigocezar.logistica.enums.TipoDespesa.COMBUSTIVEL
        AND MONTH(d.dataDespesa) = :mes
        AND YEAR(d.dataDespesa) = :ano
    """)
    BigDecimal gastoCombustivel(
            @Param("mes") Integer mes,
            @Param("ano") Integer ano
    );

    @Query("""
        SELECT COALESCE(SUM(d.preco), 0)
        FROM DespesasModel d
        WHERE d.tipoDespesa = :tipo
        AND MONTH(d.dataDespesa) = :mes
        AND YEAR(d.dataDespesa) = :ano
    """)
    BigDecimal gastoPorTipo(
            @Param("tipo") String tipo,
            @Param("mes") Integer mes,
            @Param("ano") Integer ano
    );

    @Query("""
        SELECT COALESCE(SUM(d.preco), 0)
        FROM DespesasModel d
        WHERE MONTH(d.dataDespesa) = :mes
        AND YEAR(d.dataDespesa) = :ano
    """)
    BigDecimal gastoTotal(
            @Param("mes") Integer mes,
            @Param("ano") Integer ano
    );
}