import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { BsDropdownModule, CollapseModule, ModalModule } from 'ngx-bootstrap';
import { ListarHorariosComponent } from './pages/listar-horarios/listar-horarios.component';
import { EditarHorariosComponent } from './pages/editar-horarios/editar-horarios.component';
import { ListarMovimentosComponent } from './pages/listar-movimentos/listar-movimentos.component';
import { EditarMovimentosComponent } from './pages/editar-movimentos/editar-movimentos.component';
import { ListarRelatoriosComponent } from './pages/listar-relatorios/listar-relatorios.component';
import { HorarioService } from './services/horario.service';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MovimentoService } from './services/movimento.service';
import { SairMovimentosComponent } from './pages/sair-movimentos/sair-movimentos.component';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { DatePipe } from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    ListarHorariosComponent,
    EditarHorariosComponent,
    ListarMovimentosComponent,
    EditarMovimentosComponent,
    ListarRelatoriosComponent,
    SairMovimentosComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BsDropdownModule.forRoot(),
    CollapseModule.forRoot(),
    ModalModule.forRoot(),
    BsDatepickerModule.forRoot()
  ],
  providers: [
    HorarioService,
    MovimentoService,
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
