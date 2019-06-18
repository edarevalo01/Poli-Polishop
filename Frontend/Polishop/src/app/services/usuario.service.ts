import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginUsuario } from '../model/LoginUsuario';
import { environment } from 'src/environments/environment.prod';
import { Comprador } from '../model/Comprador';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http: HttpClient) { }

  loginUserComprador(email: string): Observable<LoginUsuario>{
    const param = new HttpParams().set('correo', email);
    return this.http.get<LoginUsuario>(environment.urlLoginComprador, {params: param});
  }

  getInfoComprador(email: string): Observable<Comprador>{
    const param = new HttpParams().set('correo', email);
    return this.http.get<Comprador>(environment.urlGetInfoComprador, {params: param});
  }

  getInfoCompradorById(id: number): Observable<Comprador>{
    const param = new HttpParams().set('id', id+'');
    return this.http.get<Comprador>(environment.urlGetInfoCompradorById, {params: param});
  }

}
