import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { Producto } from "../model/producto";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment.prod";
import { Comentario } from "../model/comentario";
import { LoginUsuario } from "../model/login-usuario";
import { Comprador } from "../model/comprador";

@Injectable({
  providedIn: "root"
})
export class GeneralService {
  private usuario: Comprador = null;

  constructor(private http: HttpClient) {}

  getAllProductos(): Observable<Producto[]> {
    return this.http.get<Producto[]>(environment.urlGetProductos);
  }

  getProductosByDependencia(dependencia: string): Observable<Producto[]> {
    const param = new HttpParams().set("dependencia", dependencia);
    return this.http.get<Producto[]>(environment.urlGetAllProductosByDependencia, { params: param });
  }

  getAllProductoById(id: number): Observable<Producto> {
    const param = new HttpParams().set("id", id + "");
    return this.http.get<Producto>(environment.urlGetProductoById, { params: param });
  }

  getProductoComentarios(idProducto: number): Observable<Comentario[]> {
    const param = new HttpParams().set("idProducto", idProducto + "");
    return this.http.get<Comentario[]>(environment.urlGetProductoComentarios, { params: param });
  }

  loginUsuario(email: string): Observable<LoginUsuario> {
    const param = new HttpParams().set("correo", email);
    return this.http.get<LoginUsuario>(environment.urlLoginComprador, { params: param });
  }

  getInfoComprador(email: string): Observable<Comprador> {
    const param = new HttpParams().set("correo", email);
    return this.http.get<Comprador>(environment.urlGetInfoComprador, { params: param });
  }

  getInfoCompradorById(id: number): Observable<Comprador> {
    const param = new HttpParams().set("id", id + "");
    return this.http.get<Comprador>(environment.urlGetInfoCompradorById, { params: param });
  }

  saveCarritoConProducto(idProducto: number, idComprador: number, cantidad: number) {
    const param = new HttpParams()
      .set("idProducto", idProducto + "")
      .set("idComprador", idComprador + "")
      .set("cantidad", cantidad + "");
    return this.http.get(environment.urlSaveCarritoConCompra, { params: param });
  }

  addComentarioProducto(idComprador: number, idProducto: number, comentario: string, puntuacion: number) {
    const param = new HttpParams()
      .set("idComprador", idComprador + "")
      .set("idProducto", idProducto + "")
      .set("comentario", comentario)
      .set("puntuacion", puntuacion + "");
    return this.http.get(environment.urlAddComentarioProducto, { params: param });
  }

  getProductosCarrito(idComprador: number): Observable<ProductoCarrito[]> {
    const param = new HttpParams().set("idComprador", idComprador + "");
    return this.http.get<ProductoCarrito[]>(environment.urlGetProductosCarrito, { params: param });
  }

  eliminarProductoCarrito(idCarrito: number, idProducto: number) {
    const param = new HttpParams().set("idCarrito", idCarrito + "").set("idProducto", idProducto + "");
    return this.http.get(environment.urlEliminarProductoCarrito, { params: param });
  }

  //------------------------------------------------------------------------------------------------------------------------------------

  setCompradorLogin(comprador: Comprador) {
    this.usuario = comprador;
  }

  getCompradorLogin(): Comprador {
    return this.usuario;
  }
}
