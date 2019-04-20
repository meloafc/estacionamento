import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Horario } from '../models/horario';

@Injectable()
export class HorarioService {

  public URL = 'http://localhost:8080/horarios'; 

  constructor(private http: HttpClient) {

  }

  get(id): Observable<Horario> {
    return this.http.get<Horario>(this.URL + '/' + id);
  }

  getLista(): Observable<Horario[]> {
    return this.http.get<Horario[]>(this.URL);
  }

  cadastrar(horario: Horario): Observable<Horario> {
    return this.http.post<Horario>(this.URL, horario);
  }

  atualizar(horario: Horario): Observable<Horario> {
    return this.http.put<Horario>(this.URL, horario);
  }

  remover(horario): Observable<any> {
    return this.http.delete(this.URL + '/' + horario.id);
  }
  
}
