package com.meloafc.estacionamento.service;

import com.meloafc.estacionamento.model.Horario;

import java.util.List;

public interface HorarioService extends GenericService<Horario, Long> {

    List<Horario> findByDiaDaSemana(int diaDaSemana);

}
