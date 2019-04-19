package com.meloafc.estacionamento.service.impl;

import com.meloafc.estacionamento.exception.InvalidValueException;
import com.meloafc.estacionamento.model.Horario;
import com.meloafc.estacionamento.model.Movimento;
import com.meloafc.estacionamento.repository.MovimentoRepository;
import com.meloafc.estacionamento.service.HorarioService;
import com.meloafc.estacionamento.service.MovimentoService;
import com.meloafc.estacionamento.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MovimentoServiceImpl extends GenericServiceImpl<Movimento, Long> implements MovimentoService {

    @Autowired
    private MovimentoRepository movimentoRepository;

    @Autowired
    private HorarioService horarioService;

    @Override
    public Movimento add(Movimento entity) {
        Calendar agora = Calendar.getInstance();

        entity = registrarDataDeEntrada(entity, agora.getTime());

        if(isHorarioFechado(agora)) {
            throw new InvalidValueException("movimento.horario.notFound");
        }

        if(isPlacaRepetida(entity.getPlaca())) {
            throw new InvalidValueException("placa.duplicated");
        }

        return super.add(entity);
    }

    private boolean isPlacaRepetida(String placa) {
        Movimento movimento = movimentoRepository.findFirstByPlacaAndDataFinalIsNull(placa);
        if(movimento != null) {
            return true;
        }
        return false;
    }

    private boolean isHorarioFechado(Calendar agora) {
        int diaDaSemana = getDiaDaSemana(agora);
        List<Horario> horarios = horarioService.findByDiaDaSemana(diaDaSemana);

        Time timeAgora = DateTimeUtils.convert(agora);
        for(Horario horario : horarios) {
            if(timeAgora.after(horario.getHoraInicial()) && timeAgora.before(horario.getHoraFinal())) {
                return false;
            }
        }

        return true;
    }

    private int getDiaDaSemana(Calendar data) {
        return data.get(Calendar.DAY_OF_WEEK);
    }

    private Movimento registrarDataDeEntrada(Movimento entity, Date data) {
        entity.setDataInicial(data);
        entity.setDataFinal(null);
        return entity;
    }

    private Movimento registrarDataDeSaida(Movimento entity, Date data) {
        entity.setDataFinal(data);
        return entity;
    }

    @Override
    public Movimento sair(String placa) {
        Movimento movimento = movimentoRepository.findFirstByPlacaAndDataFinalIsNull(placa);
        if(movimento == null) {
            throw new InvalidValueException("placa.notFound");
        }

        Calendar agora = Calendar.getInstance();
        movimento = registrarDataDeSaida(movimento, agora.getTime());

        return update(movimento);
    }

}
