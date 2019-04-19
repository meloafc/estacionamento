package com.meloafc.estacionamento.enums;

import com.meloafc.estacionamento.exception.InvalidValueException;

public enum DiaDaSemana {
    FERIADO(0),
    DOMINGO(1),
    SEGUNDA(2),
    TERCA(3),
    QUARTA(4),
    QUINTA(5),
    SEXTA(6),
    SABADO(7);

    public final int id;

    DiaDaSemana(int id) {
        this.id = id;
    }

    public static DiaDaSemana get(int id) {
        for (DiaDaSemana item : values()) {
            if (item.equalsId(id)) {
                return item;
            }
        }
        throw new InvalidValueException("dayOfWeek.invalid", String.valueOf(id));
    }

    public boolean equalsId(int id) {
        return ((int) this.id) == id;
    }
}
