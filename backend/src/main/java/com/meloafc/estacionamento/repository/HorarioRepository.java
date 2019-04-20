package com.meloafc.estacionamento.repository;

import com.meloafc.estacionamento.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    List<Horario> findAllByOrderByIdDesc();
    List<Horario> findByDiaDaSemana(int diaDaSemana);

}
