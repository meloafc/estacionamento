import { Component, OnInit } from '@angular/core';
import { Movimento } from 'src/app/models/movimento';
import { MovimentoService } from 'src/app/services/movimento.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-editar-movimentos',
  templateUrl: './editar-movimentos.component.html',
  styleUrls: ['./editar-movimentos.component.scss']
})
export class EditarMovimentosComponent implements OnInit {

  public isAtualizar = false;
  public movimento: Movimento = new Movimento();

  constructor(
    private activatedRoute: ActivatedRoute,
    private movimentoService: MovimentoService,
    private router: Router
  ) { }

  ngOnInit() {
    this.novoObjeto();
    this.activatedRoute.queryParams.subscribe(params => {
      if (params['movimento']) {
        this.isAtualizar = true;
        this.carregar(params['movimento']);
      }
    });
  }

  novoObjeto() {
    this.movimento = new Movimento();
  }

  carregar(id) {
    this.movimentoService.get(id).subscribe(
      json => {
        this.movimento = json;
      },
      erro => {
        alert('Ocorreu um erro ao tentar carregar a movimentação.');
      }
    );
  }

  salvar() {
    if (this.isAtualizar) {
      this.alterar();
      return;
    }

    this.movimentoService.cadastrar(this.movimento).subscribe(
      json => {
        this.navegarParaListagem();
      },
      erro => {
        alert(erro.error.message);
      }
    );
  }

  alterar() {
    this.movimentoService.atualizar(this.movimento).subscribe(
      json => {
        this.navegarParaListagem();
      },
      erro => {
        alert(erro.error.message);
      }
    );
  }

  excluir() {
    this.movimentoService.remover(this.movimento).subscribe(
      json => {
        this.navegarParaListagem();
      },
      erro => {
        alert('Ocorreu um erro ao tentar excluir a movimentação.');
      }
    );
  }

  navegarParaListagem() {
    this.router.navigate(['/listar-movimentos']);
  }

}
