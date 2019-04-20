package com.meloafc.estacionamento.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HorarioDTO extends BaseDTO {

    private Long id;
    private Integer diaDaSemana;
    private Double valor;
    private String horaInicial;
    private String horaFinal;

}
