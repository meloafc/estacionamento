package com.meloafc.estacionamento.controller;

import com.meloafc.estacionamento.dto.MovimentoDTO;
import com.meloafc.estacionamento.mapper.MovimentoMapper;
import com.meloafc.estacionamento.model.Movimento;
import com.meloafc.estacionamento.service.MovimentoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/movimentos")
public class MovimentoController {

    @Autowired
    MovimentoService movimentoService;

    private final MovimentoMapper movimentoMapper = new MovimentoMapper();

    @GetMapping()
    @ApiOperation(value = "Listar todos os movimentos")
    public List<MovimentoDTO> getAll() {
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
