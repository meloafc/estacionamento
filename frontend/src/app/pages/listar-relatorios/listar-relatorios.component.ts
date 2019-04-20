import { Component, OnInit } from '@angular/core';
import { Movimento } from 'src/app/models/movimento';
import { Router } from '@angular/router';
import { MovimentoService } from 'src/app/services/movimento.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-listar-relatorios',
  templateUrl: './listar-relatorios.component.html',
  styleUrls: ['./listar-relatorios.component.scss']
})
export class ListarRelatoriosComponent implements OnInit {

  public movimentos: Movimento[] = [];
  public valorPeriodo: number = 0;

  constructor(
    private movimentoService: MovimentoService,
    private router: Router,
    private datePipe: DatePipe
  ) { }

  ngOnInit() {
  }

  onValueChange(value: Date): void {
    let inicio = this.datePipe.transform(value[0], 'dd/MM/yyyy');
    let fim = this.datePipe.transform(value[1], 'dd/MM/yyyy');
    this.pesquisarPeriodo(inicio, fim);
  }

  pesquisarPeriodo(dataInicial: string, dataFinal: string) {
    this.movimentos = [];
    this.movimentoService.getPeriodo(dataInicial, dataFinal).subscribe(
      json => {
        this.movimentos = json;
        this.valorPeriodo = 0;
        for (let i of json) {
          this.valorPeriodo += Number(i.valor)
        }
      },
      erro => {
        alert('Ocorreu um erro ao tentar carregar a tabela de relatorios!');
      }
    );
  }

}
