package com.meloafc.estacionamento.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="horarios")
public class Horario implements BaseModel<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer diaDaSemana;

    private Double valor;

    @Temporal(TemporalType.TIME)
    @Column(name="hora_inicial")
    private Date horaInicial;

    @Temporal(TemporalType.TIME)
    @Column(name="hora_final")
    private Date horaFinal;

}
