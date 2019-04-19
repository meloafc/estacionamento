package com.meloafc.estacionamento.service.impl;

import com.meloafc.estacionamento.exception.InvalidValueException;
import com.meloafc.estacionamento.model.Movimento;
import com.meloafc.estacionamento.repository.MovimentoRepository;
import com.meloafc.estacionamento.service.MovimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class MovimentoServiceImpl extends GenericServiceImpl<Movimento, Long> implements MovimentoService {

    @Autowired
    private MovimentoRepository movimentoRepository;

    @Override
    public Movimento add(Movimento entity) {
        Calendar agora = Calendar.getInstance();

        entity = registrarDataDeEntrada(entity, agora.getTime());

        if(isHorarioFechado(entity)) {
            // lancar exception
        }

        if(isPlacaRepetida(entity.getPlaca())) {
            throw new InvalidValueException("placa.duplicated");
        }

        //int diaDaSemana = agora.get(Calendar.DAY_OF_WEEK);
        //System.out.println(diaDaSemana);

        return super.add(entity);
    }

    private boolean isPlacaRepetida(String placa) {
        Movimento movimento = movimentoRepository.findFirstByPlacaAndDataFinalIsNull(placa);
        if(movimento != null) {
            return true;
        }
        return false;
    }

    private boolean isHorarioFechado(Movimento entity) {
        return false;
    }

    private Movimento registrarDataDeEntrada(Movimento entity, Date data) {
        entity.setDataInicial(data);
        entity.setDataFinal(null);
        return entity;
    }

}
