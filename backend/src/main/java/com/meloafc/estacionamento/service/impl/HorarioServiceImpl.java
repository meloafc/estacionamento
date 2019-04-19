package com.meloafc.estacionamento.service.impl;

import com.meloafc.estacionamento.enums.DiaDaSemana;
import com.meloafc.estacionamento.model.Horario;
import com.meloafc.estacionamento.service.HorarioService;
import org.springframework.stereotype.Service;

@Service
public class HorarioServiceImpl extends GenericServiceImpl<Horario, Long> implements HorarioService {

    @Override
    public void validate(Horario entity) {
        DiaDaSemana.get(entity.getDiaDaSemana());
        super.validate(entity);
    }

}
