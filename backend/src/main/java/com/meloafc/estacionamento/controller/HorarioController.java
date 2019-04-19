package com.meloafc.estacionamento.controller;

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

    @GetMapping()
    @ApiOperation(value = "Listar todos os horarios")
    public List<Horario> getAll() {
        return horarioService.getAll();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Consultar por id")
    public Horario findById(@ApiParam(value = "id", required = true) @PathVariable Long id) {
        return horarioService.get(id);
    }

    @PostMapping()
    @ApiOperation(value = "Salvar horario")
    public Horario create(@Valid @RequestBody Horario horario) {
        return horarioService.add(horario);
    }

    @PutMapping()
    @ApiOperation(value = "Atualizar horario")
    public Horario update(@Valid @RequestBody Horario horario) {
        return horarioService.update(horario);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Deletar horario")
    public void delete(@ApiParam(value = "id", required = true) @PathVariable Long id) {
        horarioService.removeById(id);
    }

}
