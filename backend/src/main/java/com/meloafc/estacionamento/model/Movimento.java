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
@Table(name="movimentos")
public class Movimento implements BaseModel<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "placa", nullable = false, length = 20)
    private String placa;

    @Column(name = "modelo", length = 30)
    private String modelo;

    @Column(name = "cor", length = 30)
    private String cor;

    private boolean feriado;

    @Transient
    private Double valor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data_inicial")
    private Date dataInicial;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data_final")
    private Date dataFinal;

}
