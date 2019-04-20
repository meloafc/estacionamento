import { Component, OnInit } from '@angular/core';
import { Horario } from 'src/app/models/horario';
import { ActivatedRoute, Router } from '@angular/router';
import { HorarioService } from 'src/app/services/horario.service';

@Component({
  selector: 'app-editar-horarios',
  templateUrl: './editar-horarios.component.html',
  styleUrls: ['./editar-horarios.component.scss']
})
export class EditarHorariosComponent implements OnInit {

  public isAtualizar = false;
  public horario: Horario = new Horario();

  constructor(
    private activatedRoute: ActivatedRoute,
    private horarioService: HorarioService,
    private router: Router
  ) { }

  ngOnInit() {
    this.novoObjeto();
    this.activatedRoute.queryParams.subscribe(params => {
      if (params['horario']) {
        this.isAtualizar = true;
        this.carregar(params['horario']);
      }
    });
  }

  novoObjeto() {
    this.horario = new Horario();
    this.horario.diaDaSemana = 0;
    this.horario.valor = 0;
    this.horario.horaInicial = '00:00';
    this.horario.horaFinal = '00:00';
  }

  carregar(id) {
    this.horarioService.get(id).subscribe(
      json => {
        this.horario = json;
      },
      erro => {
        alert('Ocorreu um erro ao tentar carregar o preço.');
      }
    );
  }

  salvar() {
    if (this.isAtualizar) {
      this.alterar();
      return;
    }
    
    this.horarioService.cadastrar(this.horario).subscribe(
      json => {
        this.navegarParaListagem();
      },
      erro => {
        alert(erro.error.message);
      }
    );
  }

  alterar() {
    this.horarioService.atualizar(this.horario).subscribe(
      json => {
        this.navegarParaListagem();
      },
      erro => {
        alert(erro.error.message);
      }
    );
  }

  excluir() {
    this.horarioService.remover(this.horario).subscribe(
      json => {
        this.navegarParaListagem();
      },
      erro => {
        alert('Ocorreu um erro ao tentar excluir o preço.');
      }
    );
  }

  navegarParaListagem() {
    this.router.navigate(['/listar-horarios']);
  }

}
