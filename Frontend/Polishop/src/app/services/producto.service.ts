import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Producto } from '../model/Producto';
import { environment } from 'src/environments/environment.prod';
import { Observable } from 'rxjs';
import { Comentario } from '../model/Comentario';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  constructor(private http: HttpClient) { }

  getAllProductoById(id: number): Observable<Producto>{
    const param = new HttpParams().set('id', id+'');
    return this.http.get<Producto>(environment.urlGetProductoById, {params: param});
  }

  getAllProductos(): Observable<Producto[]>{
    return this.http.get<Producto[]>(environment.urlGetAllProductos);
  }

  getAllProductosByDependencia(dependencia: string): Observable<Producto[]>{
    const params = new HttpParams().set('dependencia', dependencia);
    return this.http.get<Producto[]>(environment.urlGetAllProductosByDependencia, {params: params});
  }

  getProductoComentarios(idProducto: number): Observable<Comentario[]>{
    const param = new HttpParams().set('idProducto', idProducto+'');
    return this.http.get<Comentario[]>(environment.urlGetProductoComentarios, {params: param});
  }

  addComentarioProducto(idComprador: number, idProducto: number, comentario: string, puntuacion: number){
    const body = new HttpParams().set('idComprador', idComprador+'')
                                  .set('idProducto', idProducto+'')
                                  .set('comentario', comentario)
                                  .set('puntuacion', puntuacion+'');
    return this.http.post(environment.urlAddComentarioProducto, body).subscribe();
  }
}
