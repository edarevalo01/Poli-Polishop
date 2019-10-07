import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Producto } from '../model/producto';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class GeneralService {

  constructor(private http: HttpClient) { }

  getAllProductos(): Observable<Producto[]>{
    return this.http.get<Producto[]>(environment.urlGetProductos);
  }

  getProductosByDependencia(dependencia: string): Observable<Producto[]> {
    const param = new HttpParams().set('dependencia', dependencia);
    return this.http.get<Producto[]>(environment.urlGetAllProductosByDependencia, {params: param});
  }

  getAllProductoById(id: number): Observable<Producto>{
    const param = new HttpParams().set('id', id+'');
    return this.http.get<Producto>(environment.urlGetProductoById, {params: param});
  }
}
