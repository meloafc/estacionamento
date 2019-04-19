package com.meloafc.estacionamento.controller;

import com.meloafc.estacionamento.model.Horario;
import com.meloafc.estacionamento.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/horario")
public class HorarioController {

    @Autowired
    HorarioRepository horarioRepository;

    @GetMapping()
    public List<Horario> listar() {
        return horarioRepository.findAll();
    }

    @PostMapping()
    public Horario criar(@Valid @RequestBody Horario horario) {
        return horarioRepository.save(horario);
    }

}
