import { Component, OnInit } from '@angular/core';
import { Movimento } from 'src/app/models/movimento';
import { MovimentoService } from 'src/app/services/movimento.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-listar-movimentos',
  templateUrl: './listar-movimentos.component.html',
  styleUrls: ['./listar-movimentos.component.scss']
})
export class ListarMovimentosComponent implements OnInit {

  public movimentos: Movimento[] = [];

  constructor(
    private movimentoService: MovimentoService,
    private router: Router
  ) { }

  ngOnInit() {
    this.movimentoService.getLista().subscribe(
      json => {
        this.movimentos = json;
      },
      erro => {
        alert('Ocorreu um erro ao tentar carregar a tabela de movimentos!');
      }
    );
  }

  editar(movimento: Movimento) {
    this.router.navigate(['/editar-movimentos'], { queryParams: { movimento: movimento.id } });
  }

}
