import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Movimento } from '../models/movimento';

@Injectable()
export class MovimentoService {

  public URL = 'http://localhost:8080/movimentos'; 

  constructor(private http: HttpClient) {

  }

  get(id): Observable<Movimento> {
    return this.http.get<Movimento>(this.URL + '/' + id);
  }

  getLista(): Observable<Movimento[]> {
    return this.http.get<Movimento[]>(this.URL);
  }

  cadastrar(movimento: Movimento): Observable<Movimento> {
    return this.http.post<Movimento>(this.URL, movimento);
  }

  atualizar(movimento: Movimento): Observable<Movimento> {
    return this.http.put<Movimento>(this.URL, movimento);
  }

  sair(placa: String): Observable<Movimento> {
    return this.http.put<Movimento>(this.URL + '/sair/' + placa, placa);
  }

  remover(movimento): Observable<any> {
    return this.http.delete(this.URL + '/' + movimento.id);
  }
}
