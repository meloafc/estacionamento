package com.meloafc.estacionamento.service.impl;

import com.meloafc.estacionamento.model.Horario;
import com.meloafc.estacionamento.model.Movimento;
import com.meloafc.estacionamento.repository.MovimentoRepository;
import com.meloafc.estacionamento.service.HorarioService;
import com.meloafc.estacionamento.utils.DateTimeUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MovimentoServiceImplTest {

    @InjectMocks
    MovimentoServiceImpl movimentoService;

    @Mock
    HorarioService horarioService;

    @Mock
    MovimentoRepository movimentoRepository;

    private List<Horario> horarios;

    @BeforeEach
    protected void setup() {
        horarios = new ArrayList<>();
        horarios.add(Horario.builder()
                .diaDaSemana(2)
                .horaInicial(DateTimeUtils.convertStrHHMMToDate("08:00"))
                .horaFinal(DateTimeUtils.convertStrHHMMToDate("11:59"))
                .valor(2d)
                .build());
        horarios.add(Horario.builder()
                .diaDaSemana(2)
                .horaInicial(DateTimeUtils.convertStrHHMMToDate("18:00"))
                .horaFinal(DateTimeUtils.convertStrHHMMToDate("20:00"))
                .valor(4d)
                .build());
        horarios.add(Horario.builder()
                .diaDaSemana(2)
                .horaInicial(DateTimeUtils.convertStrHHMMToDate("12:00"))
                .horaFinal(DateTimeUtils.convertStrHHMMToDate("17:59"))
                .valor(3d)
                .build());
    }

    @Test
    void deveDurarUmHorarioCompleto() throws ParseException {
        Movimento movimento = Movimento.builder()
                .dataInicial(DateTimeUtils.convertStringHHMMSSToDate("18:00:00"))
                .dataFinal(DateTimeUtils.convertStringHHMMSSToDate("20:00:00"))
                .build();
        movimentoService.calcularValor(movimento, horarios);
        assertEquals(8d, movimento.getValor(), 0);
    }

    @Test
    void deveOcorrerEmApenasUmHorario() throws ParseException {
        Movimento movimento = Movimento.builder()
                .dataInicial(DateTimeUtils.convertStringHHMMSSToDate("13:00:00"))
                .dataFinal(DateTimeUtils.convertStringHHMMSSToDate("15:00:00"))
                .build();
        movimentoService.calcularValor(movimento, horarios);
        assertEquals(6d, movimento.getValor(), 0);
    }

    @Test
    void deveOcorrerEmDoisHorarios() throws ParseException {
        Movimento movimento = Movimento.builder()
                .dataInicial(DateTimeUtils.convertStringHHMMSSToDate("11:00:00"))
                .dataFinal(DateTimeUtils.convertStringHHMMSSToDate("13:00:00"))
                .build();
        movimentoService.calcularValor(movimento, horarios);
        assertEquals(5d, movimento.getValor(), 0);
    }

    @Test
    void deveDurarODiaInteiro() throws ParseException {
        Movimento movimento = Movimento.builder()
                .dataInicial(DateTimeUtils.convertStringHHMMSSToDate("08:00:00"))
                .dataFinal(DateTimeUtils.convertStringHHMMSSToDate("20:00:00"))
                .build();
        movimentoService.calcularValor(movimento, horarios);
        assertEquals(34d, movimento.getValor(), 0);
    }

    @Test
    void deveDurarMenosDeUmaHora() throws ParseException {
        Movimento movimento = Movimento.builder()
                .dataInicial(DateTimeUtils.convertStringHHMMSSToDate("08:10:00"))
                .dataFinal(DateTimeUtils.convertStringHHMMSSToDate("08:13:00"))
                .build();
        movimentoService.calcularValor(movimento, horarios);
        assertEquals(0.1d, movimento.getValor(), 0);
    }
}
