package com.meloafc.estacionamento.service;

import com.meloafc.estacionamento.model.Horario;
import com.meloafc.estacionamento.model.Movimento;

import java.util.List;

public interface MovimentoService extends GenericService<Movimento, Long> {

    Movimento sair(String placa);
    Movimento calcularValor(Movimento movimento, List<Horario> horarios);

}
