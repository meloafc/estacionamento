package com.meloafc.estacionamento.repository;

import com.meloafc.estacionamento.model.Movimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentoRepository extends JpaRepository<Movimento, Long> {

    Movimento findFirstByPlacaAndDataFinalIsNull(String placa);

}
