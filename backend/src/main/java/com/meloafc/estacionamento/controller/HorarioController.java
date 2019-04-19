package com.meloafc.estacionamento.controller;

import com.meloafc.estacionamento.dto.HorarioDTO;
import com.meloafc.estacionamento.mapper.HorarioMapper;
import com.meloafc.estacionamento.model.Horario;
import com.meloafc.estacionamento.service.HorarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/horarios")
public class HorarioController {

    @Autowired
    HorarioService horarioService;

    private final HorarioMapper horarioMapper = new HorarioMapper();

    @GetMapping()
    @ApiOperation(value = "Listar todos os horarios")
    public List<HorarioDTO> getAll() {
        return horarioMapper.convertToListDTO(horarioService.getAll());
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Consultar por id")
    public HorarioDTO findById(@ApiParam(value = "id", required = true) @PathVariable Long id) {
        return horarioMapper.convertToDTO(horarioService.get(id));
    }

    @PostMapping()
    @ApiOperation(value = "Salvar horario")
    public HorarioDTO create(@Valid @RequestBody HorarioDTO horarioDTO) {
        Horario horario = this.horarioMapper.convertToEntity(horarioDTO);
        return this.horarioMapper.convertToDTO(horarioService.add(horario));
    }

    @PutMapping()
    @ApiOperation(value = "Atualizar horario")
    public HorarioDTO update(@Valid @RequestBody HorarioDTO horarioDTO) {
        Horario horario = this.horarioMapper.convertToEntity(horarioDTO);
        return this.horarioMapper.convertToDTO(horarioService.update(horario));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Deletar horario")
    public void delete(@ApiParam(value = "id", required = true) @PathVariable Long id) {
        horarioService.removeById(id);
    }

}
