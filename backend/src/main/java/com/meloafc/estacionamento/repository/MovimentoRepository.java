package com.meloafc.estacionamento.repository;

import com.meloafc.estacionamento.model.Movimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MovimentoRepository extends JpaRepository<Movimento, Long> {

    List<Movimento> findAllByOrderByIdDesc();
    List<Movimento> findAllByDataInicialBetween(Date inicio, Date fim);
    Movimento findFirstByPlacaAndDataFinalIsNull(String placa);

}
