package com.meloafc.estacionamento.mapper;

import com.meloafc.estacionamento.dto.MovimentoDTO;
import com.meloafc.estacionamento.model.Movimento;

public class MovimentoMapper implements GenericMapper<Movimento, MovimentoDTO> {

    @Override
    public MovimentoDTO convertToDTO(Movimento entity) {
        MovimentoDTO dto = new MovimentoDTO();
        dto.setId(entity.getId());
        dto.setPlaca(entity.getPlaca());
        dto.setModelo(entity.getModelo());
        dto.setCor(entity.getCor());
        dto.setFeriado(entity.isFeriado());
        dto.setValor(entity.getValor());
        dto.setDataInicial(entity.getDataInicial());
        dto.setDataFinal(entity.getDataFinal());
        return dto;
    }

    @Override
    public Movimento convertToEntity(MovimentoDTO dto) {
        Movimento movimento = new Movimento();
        movimento.setId(dto.getId());
        movimento.setPlaca(dto.getPlaca());
        movimento.setModelo(dto.getModelo());
        movimento.setCor(dto.getCor());
        movimento.setFeriado(dto.isFeriado());
        movimento.setValor(dto.getValor());
        movimento.setDataInicial(dto.getDataInicial());
        movimento.setDataFinal(dto.getDataFinal());
        return movimento;
    }
}
