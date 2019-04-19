package com.meloafc.estacionamento.service.impl;

import com.meloafc.estacionamento.enums.DiaDaSemana;
import com.meloafc.estacionamento.exception.InvalidValueException;
import com.meloafc.estacionamento.model.Horario;
import com.meloafc.estacionamento.repository.HorarioRepository;
import com.meloafc.estacionamento.service.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioServiceImpl extends GenericServiceImpl<Horario, Long> implements HorarioService {

    @Autowired
    HorarioRepository horarioRepository;

    @Override
    public Horario add(Horario entity) {
        return super.add(entity);
    }

    @Override
    public void validate(Horario entity) {

        // TODO: validar conflito de horario.

        if(entity.getHoraInicial().after(entity.getHoraFinal())) {
            throw new InvalidValueException("horario.horaInicial.isAfter.horaFinal");
        }
        DiaDaSemana.get(entity.getDiaDaSemana());
        super.validate(entity);
    }

    @Override
    public List<Horario> findByDiaDaSemana(int diaDaSemana) {
        return horarioRepository.findByDiaDaSemana(diaDaSemana);
    }
}
