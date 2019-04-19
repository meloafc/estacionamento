package com.meloafc.estacionamento.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimentoDTO extends BaseDTO {

    private Long id;
    private String placa;
    private String modelo;
    private String cor;
    private boolean feriado;
    private Double valor;
    private Date dataInicial;
    private Date dataFinal;

}
