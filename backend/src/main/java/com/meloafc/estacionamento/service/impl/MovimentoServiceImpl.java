package com.meloafc.estacionamento.service.impl;

import com.meloafc.estacionamento.enums.DiaDaSemana;
import com.meloafc.estacionamento.exception.InvalidValueException;
import com.meloafc.estacionamento.model.Horario;
import com.meloafc.estacionamento.model.Movimento;
import com.meloafc.estacionamento.repository.MovimentoRepository;
import com.meloafc.estacionamento.service.HorarioService;
import com.meloafc.estacionamento.service.MovimentoService;
import com.meloafc.estacionamento.utils.DateTimeUtils;
import com.meloafc.estacionamento.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.*;

@Service
public class MovimentoServiceImpl extends GenericServiceImpl<Movimento, Long> implements MovimentoService {

    @Autowired
    private MovimentoRepository movimentoRepository;

    @Autowired
    private HorarioService horarioService;

    @Override
    public List<Movimento> getAll() {
        List<Movimento> movimentos = movimentoRepository.findAllByOrderByIdDesc();
        calcularValor(movimentos);
        return movimentos;
    }

    @Override
    public Movimento add(Movimento entity) {
        Calendar agora = Calendar.getInstance();

        entity = registrarDataDeEntrada(entity, agora.getTime());

        if(isHorarioFechado(agora, entity)) {
            throw new InvalidValueException("movimento.horario.notFound");
        }

        if(isPlacaRepetida(entity.getPlaca())) {
            throw new InvalidValueException("placa.duplicated");
        }

        return super.add(entity);
    }

    @Override
    public void validate(Movimento entity) {
        ValidateUtils.checkNotNullOrEmpty(entity.getPlaca(), "placa.mustBeFilled");
        super.validate(entity);
    }

    private boolean isPlacaRepetida(String placa) {
        Movimento movimento = movimentoRepository.findFirstByPlacaAndDataFinalIsNull(placa);
        if(movimento != null) {
            return true;
        }
        return false;
    }

    private boolean isHorarioFechado(Calendar agora, Movimento movimento) {
        int diaDaSemana = getDiaDaSemana(agora, movimento);
        List<Horario> horarios = horarioService.findByDiaDaSemana(diaDaSemana);

        Time timeAgora = DateTimeUtils.convert(agora);
        for(Horario horario : horarios) {
            Time timeInicio = DateTimeUtils.convert(horario.getHoraInicial());
            Time timeFim = DateTimeUtils.convert(horario.getHoraFinal());

            if(timeAgora.after(timeInicio) && timeAgora.before(timeFim)) {
                return false;
            }
        }

        return true;
    }

    private int getDiaDaSemana(Calendar data, Movimento movimento) {
        if(movimento.isFeriado()) {
            return DiaDaSemana.FERIADO.id;
        }
        return data.get(Calendar.DAY_OF_WEEK);
    }

    private int getDiaDaSemana(Date data, Movimento movimento) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        return getDiaDaSemana(calendar, movimento);
    }

    private Movimento registrarDataDeEntrada(Movimento entity, Date data) {
        entity.setDataInicial(data);
        entity.setDataFinal(null);
        return entity;
    }

    private Movimento registrarDataDeSaida(Movimento entity, Date data) {
        entity.setDataFinal(data);
        return entity;
    }

    @Override
    public List<Movimento> pesquisarNoPeriodo(Date dataInicial, Date dataFinal) {
        List<Movimento> movimentos = movimentoRepository.findAllByDataInicialBetween(dataInicial, dataFinal);
        calcularValor(movimentos);
        return movimentos;
    }

    @Override
    public Movimento sair(String placa) {
        Movimento movimento = movimentoRepository.findFirstByPlacaAndDataFinalIsNull(placa);
        if(movimento == null) {
            throw new InvalidValueException("placa.notFound");
        }

        movimento = registrarDataDeSaida(movimento, Calendar.getInstance().getTime());

        int diaDaSemana = getDiaDaSemana(movimento.getDataInicial(), movimento);
        List<Horario> horarios = horarioService.findByDiaDaSemana(diaDaSemana);
        calcularValor(movimento, horarios);

        return update(movimento);
    }

    @Override
    public Movimento calcularValor(Movimento movimento, List<Horario> horarios) {
        // TODO: verificar se os horarios sao do mesmo dia.

        if(movimento.getDataInicial() == null || movimento.getDataFinal() == null) {
            movimento.setValor(0d);
            return movimento;
        }

        Time entrada = DateTimeUtils.convert(movimento.getDataInicial());
        Time saida = DateTimeUtils.convert(movimento.getDataFinal());
        double valorTotal = 0d;
        double valorDoHorarioAtual = 0d;
        boolean isHouveIntercecao = false;

        horarios.sort(Comparator.comparing(o -> o.getHoraInicial()));

        for(Horario horario : horarios) {

            boolean isPeriodoSemHorario = isHouveIntercecao && !isPossuiIntercecao(entrada, horario);

            if(isPeriodoSemHorario) {
                long duracaoNoPeriodoSemHorario = horario.getHoraInicial().getTime() - entrada.getTime();
                BigDecimal horas = DateTimeUtils.convertMillisecondsToHours(duracaoNoPeriodoSemHorario);
                valorTotal += valorDoHorarioAtual * horas.doubleValue();
                entrada = DateTimeUtils.convert(horario.getHoraInicial());
            }

            if(isPossuiIntercecao(entrada, horario)) {
                isHouveIntercecao = true;
                valorDoHorarioAtual = horario.getValor();

                long duracaoNoHorario = 0;

                boolean isSaidaMenorQueFinalDoHorario = saida.before(horario.getHoraFinal());

                if(isSaidaMenorQueFinalDoHorario) {
                    duracaoNoHorario = saida.getTime() - entrada.getTime();
                    entrada = saida;
                } else {
                    duracaoNoHorario = horario.getHoraFinal().getTime() - entrada.getTime();
                    entrada = DateTimeUtils.convert(horario.getHoraFinal());
                }

                BigDecimal horas = DateTimeUtils.convertMillisecondsToHours(duracaoNoHorario);
                valorTotal += horario.getValor() * horas.doubleValue();

                if(entrada.equals(saida) || entrada.after(saida)) {
                    break;
                }
            }
        }

        movimento.setValor(valorTotal);
        return movimento;
    }

    @Override
    public List<Movimento> calcularValor(List<Movimento> movimentos) {
        Map<Integer, List<Horario>> horariosMap = new HashMap<>();

        for(Movimento movimento : movimentos) {
            int diaDaSemana = getDiaDaSemana(movimento.getDataInicial(), movimento);
            List<Horario> horarios = getHorarios(diaDaSemana, horariosMap);
            calcularValor(movimento, horarios);
        }

        return movimentos;
    }

    private List<Horario> getHorarios(int diaDaSemana, Map<Integer, List<Horario>> horariosMap) {
        if(horariosMap == null) {
            horariosMap = new HashMap<>();
        }

        if(!horariosMap.containsKey(diaDaSemana)) {
            horariosMap.put(diaDaSemana, horarioService.findByDiaDaSemana(diaDaSemana));
        }

        return horariosMap.get(diaDaSemana);
    }

    private boolean isPossuiIntercecao(Date date, Horario horario) {
        return (horario.getHoraInicial().before(date) || horario.getHoraInicial().equals(date))
                && (horario.getHoraFinal().after(date) || horario.getHoraFinal().equals(date));
    }

}
