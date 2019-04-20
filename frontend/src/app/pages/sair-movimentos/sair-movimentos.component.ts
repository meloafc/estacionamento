import { Component, OnInit } from '@angular/core';
import { Movimento } from 'src/app/models/movimento';
import { ActivatedRoute, Router } from '@angular/router';
import { MovimentoService } from 'src/app/services/movimento.service';

@Component({
  selector: 'app-sair-movimentos',
  templateUrl: './sair-movimentos.component.html',
  styleUrls: ['./sair-movimentos.component.scss']
})
export class SairMovimentosComponent implements OnInit {

  public isSaiu = false;
  public placa = '';
  public movimento: Movimento = new Movimento();

  constructor(
    private activatedRoute: ActivatedRoute,
    private movimentoService: MovimentoService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  sair() {
    this.movimentoService.sair(this.placa).subscribe(
      json => {
        this.isSaiu = true;
        this.movimento = json;
      },
      erro => {
        alert(erro.error.message);
      }
    );
  }

}
