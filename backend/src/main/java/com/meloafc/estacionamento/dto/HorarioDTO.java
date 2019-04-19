package com.meloafc.estacionamento.dto;

import com.meloafc.estacionamento.utils.DateTimeUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HorarioDTO extends BaseDTO {

    private Long id;

    @NotNull(message = "horario.diaDaSemana.isNullOrBlank")
    private Integer diaDaSemana;

    private Double valor;

    @NotNull(message = "horario.horaInicial.isNullOrBlank")
    @Size(min = DateTimeUtils.LENGTH_TIME_HHMM, max = DateTimeUtils.LENGTH_TIME_HHMM, message = "horario.horaInicial.invalidLength")
    private String horaInicial;

    @NotNull(message = "horario.horaFinal.isNullOrBlank")
    @Size(min = DateTimeUtils.LENGTH_TIME_HHMM, max = DateTimeUtils.LENGTH_TIME_HHMM, message = "horario.horaFinal.invalidLength")
    private String horaFinal;

}
