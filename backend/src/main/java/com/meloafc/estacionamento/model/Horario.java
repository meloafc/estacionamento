package com.meloafc.estacionamento.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="horarios")
public class Horario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer diaDaSemana;

    //@DateTimeFormat(pattern="HH:mm:ss" )
    //@Temporal(TemporalType.TIME)
    //@Column(name = "end_time")
    private Time horaInicial;

    private Time horaFinal;

    private Double valor;
}
