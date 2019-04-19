package com.meloafc.estacionamento.mapper;

import com.meloafc.estacionamento.dto.HorarioDTO;
import com.meloafc.estacionamento.exception.InvalidValueException;
import com.meloafc.estacionamento.model.Horario;
import com.meloafc.estacionamento.utils.DateTimeUtils;

public class HorarioMapper implements GenericMapper<Horario, HorarioDTO> {

    @Override
    public HorarioDTO convertToDTO(Horario entity) {
        HorarioDTO dto = new HorarioDTO();
        dto.setId(entity.getId());
        dto.setDiaDaSemana(entity.getDiaDaSemana());
        dto.setValor(entity.getValor());
        dto.setHoraInicial(DateTimeUtils.convertDateToHHMM(entity.getHoraInicial()));
        dto.setHoraFinal(DateTimeUtils.convertDateToHHMM(entity.getHoraFinal()));
        return dto;
    }

    @Override
    public Horario convertToEntity(HorarioDTO dto) {
        Horario horario = new Horario();
        horario.setId(dto.getId());
        horario.setDiaDaSemana(dto.getDiaDaSemana());
        horario.setValor(dto.getValor());

        try {
            horario.setHoraInicial(DateTimeUtils.convertStrHHMMToDate(dto.getHoraInicial()));
        } catch (Exception e) {
            throw new InvalidValueException("horario.horaInicial.invalid", e, dto.getHoraInicial());
        }

        try {
            horario.setHoraFinal(DateTimeUtils.convertStrHHMMToDate(dto.getHoraFinal()));
        } catch (Exception e) {
            throw new InvalidValueException("horario.horaFinal.invalid", e, dto.getHoraFinal());
        }

        return horario;
    }

}
