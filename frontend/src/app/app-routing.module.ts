import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListarHorariosComponent } from './pages/listar-horarios/listar-horarios.component';
import { EditarHorariosComponent } from './pages/editar-horarios/editar-horarios.component';
import { ListarMovimentosComponent } from './pages/listar-movimentos/listar-movimentos.component';
import { EditarMovimentosComponent } from './pages/editar-movimentos/editar-movimentos.component';
import { ListarRelatoriosComponent } from './pages/listar-relatorios/listar-relatorios.component';

const routes: Routes = [
  { path: '', component: ListarHorariosComponent },
  { path: 'listar-horarios', component: ListarHorariosComponent },
  { path: 'editar-horarios', component: EditarHorariosComponent },
  { path: 'listar-movimentos', component: ListarMovimentosComponent },
  { path: 'editar-movimentos', component: EditarMovimentosComponent },
  { path: 'listar-relatorios', component: ListarRelatoriosComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
