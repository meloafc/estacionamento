package com.meloafc.estacionamento.service;

import com.meloafc.estacionamento.model.Movimento;

public interface MovimentoService extends GenericService<Movimento, Long> {

    Movimento sair(String placa);
}
