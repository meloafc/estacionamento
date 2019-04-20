package com.meloafc.estacionamento.controller;

import com.meloafc.estacionamento.dto.MovimentoDTO;
import com.meloafc.estacionamento.exception.InvalidValueException;
import com.meloafc.estacionamento.mapper.MovimentoMapper;
import com.meloafc.estacionamento.model.Movimento;
import com.meloafc.estacionamento.service.MovimentoService;
import com.meloafc.estacionamento.utils.DateTimeUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/movimentos")
public class MovimentoController {

    @Autowired
    MovimentoService movimentoService;

    private final MovimentoMapper movimentoMapper = new MovimentoMapper();

    @GetMapping()
    @ApiOperation(value = "Listar todos os movimentos")
    public List<MovimentoDTO> getAll(@RequestParam("dataInicial") Optional<String> dataInicial,
                                     @RequestParam("dataFinal") Optional<String> dataFinal) {
        if(dataInicial.isPresent() &&  dataFinal.isPresent()) {
            Date inicio = null;
            Date fim = null;

            try {
                inicio = DateTimeUtils.convertStringDDMMYYYYToDate(dataInicial.get());
            } catch (ParseException e) {
                throw new InvalidValueException("data.inicio.invalid");
            }

            try {
                fim = DateTimeUtils.convertStringDDMMYYYYToDate(dataFinal.get());
            } catch (ParseException e) {
                throw new InvalidValueException("data.final.invalid");
            }

            return movimentoMapper.convertToListDTO(movimentoService.pesquisarNoPeriodo(inicio, fim));
        }
        return movimentoMapper.convertToListDTO(movimentoService.getAll());
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Consultar por id")
    public MovimentoDTO findById(@ApiParam(value = "id", required = true) @PathVariable Long id) {
        return movimentoMapper.convertToDTO(movimentoService.get(id));
    }

    @PostMapping()
    @ApiOperation(value = "Salvar movimento")
    public MovimentoDTO create(@Valid @RequestBody MovimentoDTO horarioDTO) {
        Movimento horario = movimentoMapper.convertToEntity(horarioDTO);
        return movimentoMapper.convertToDTO(movimentoService.add(horario));
    }

    @PutMapping()
    @ApiOperation(value = "Atualizar movimento")
    public MovimentoDTO update(@Valid @RequestBody MovimentoDTO horarioDTO) {
        Movimento horario = movimentoMapper.convertToEntity(horarioDTO);
        return movimentoMapper.convertToDTO(movimentoService.update(horario));
    }

    @PutMapping(value = "/sair/{placa}")
    @ApiOperation(value = "Registrar saida do movimento")
    public MovimentoDTO update(@ApiParam(value = "placa", required = true) @PathVariable String placa) {
        return movimentoMapper.convertToDTO(movimentoService.sair(placa));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Deletar movimento")
    public void delete(@ApiParam(value = "id", required = true) @PathVariable Long id) {
        movimentoService.removeById(id);
    }

}
