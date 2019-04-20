import { Component, OnInit } from '@angular/core';
import { Horario } from 'src/app/models/horario';
import { Router } from '@angular/router';
import { HorarioService } from 'src/app/services/horario.service';
import { DiaDaSemana } from 'src/app/models/dia-da-semana';

@Component({
  selector: 'app-listar-horarios',
  templateUrl: './listar-horarios.component.html',
  styleUrls: ['./listar-horarios.component.scss']
})
export class ListarHorariosComponent implements OnInit {

  DiaDaSemana = DiaDaSemana;

  public horarios: Horario[] = [];

  constructor(
    private horarioService: HorarioService,
    private router: Router
  ) { }

  ngOnInit() {
    this.horarioService.getLista().subscribe(
      json => {
        this.horarios = json;
      },
      erro => {
        alert('Ocorreu um erro ao tentar carregar a tabela de preços!');
      }
    );
  }

  editar(horario: Horario) {
    this.router.navigate(['/editar-horarios'], { queryParams: { horario: horario.id } });
  }

}
