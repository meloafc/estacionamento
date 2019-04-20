package com.meloafc.estacionamento.service;

import com.meloafc.estacionamento.model.Horario;
import com.meloafc.estacionamento.model.Movimento;

import java.util.Date;
import java.util.List;

public interface MovimentoService extends GenericService<Movimento, Long> {

    List<Movimento> pesquisarNoPeriodo(Date dataInicial, Date dataFinal);
    Movimento sair(String placa);
    Movimento calcularValor(Movimento movimento, List<Horario> horarios);
    List<Movimento> calcularValor(List<Movimento> movimentos);

}
