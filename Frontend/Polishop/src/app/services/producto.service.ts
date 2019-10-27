import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { Producto } from "../model/Producto";
import { environment } from "src/environments/environment.prod";
import { Observable } from "rxjs";
import { Comentario } from "../model/Comentario";
import { Compra } from "../model/Compra";
import { CompraHist } from "../model/CompraHist";

@Injectable({
  providedIn: "root"
})
export class ProductoService {
  constructor(private http: HttpClient) {}

  getAllProductoById(id: number): Observable<Producto> {
    const param = new HttpParams().set("id", id + "");
    return this.http.get<Producto>(environment.urlGetProductoById, { params: param });
  }

  getAllProductos(): Observable<Producto[]> {
    return this.http.get<Producto[]>(environment.urlGetAllProductos);
  }

  getAllProductosByDependencia(dependencia: string): Observable<Producto[]> {
    const params = new HttpParams().set("dependencia", dependencia);
    return this.http.get<Producto[]>(environment.urlGetAllProductosByDependencia, { params: params });
  }

  getProductoComentarios(idProducto: number): Observable<Comentario[]> {
    const param = new HttpParams().set("idProducto", idProducto + "");
    return this.http.get<Comentario[]>(environment.urlGetProductoComentarios, { params: param });
  }

  addComentarioProducto(idComprador: number, idProducto: number, comentario: string, puntuacion: number) {
    const body = new HttpParams()
      .set("idComprador", idComprador + "")
      .set("idProducto", idProducto + "")
      .set("comentario", comentario)
      .set("puntuacion", puntuacion + "");
    return this.http.post(environment.urlAddComentarioProducto, body).subscribe();
  }

  saveCarritoConProducto(idProducto: number, idComprador: number, cantidad: number) {
    const body = new HttpParams()
      .set("idProducto", idProducto + "")
      .set("idComprador", idComprador + "")
      .set("cantidad", cantidad + "");
    return this.http.post(environment.urlSaveCarritoConCompra, body).subscribe();
  }

  getProductosByVendedor(idVendedor: number): Observable<Producto[]> {
    const param = new HttpParams().set("idVendedor", idVendedor + "");
    return this.http.get<Producto[]>(environment.urlGetProductosByVendedor, { params: param });
  }

  updateProducto(idProducto: number, nombre: string, precio: string, descripcion: string) {
    const body = new HttpParams()
      .set("idProducto", idProducto + "")
      .set("nombre", nombre)
      .set("precio", precio)
      .set("descripcion", descripcion);
    return this.http.post(environment.urlUpdateProducto, body);
  }

  deleteProducto(idProducto: number) {
    const body = new HttpParams().set("idProducto", idProducto + "");
    return this.http.post(environment.urldeleteProducto, body).subscribe();
  }

  addProducto(nombre: string, precio: string, descripcion: string, dependencia: string, idVendedor: number) {
    const body = new HttpParams()
      .set("nombre", nombre)
      .set("descripcion", descripcion)
      .set("precio", precio)
      .set("dependencia", dependencia)
      .set("idVendedor", idVendedor + "")
      .set("idPropietario", "1");
    return this.http.post(environment.urlAddProducto, body);
  }

  addImagenProducto(idProducto: number, imagenProducto: File, nombreImagen: string) {
    const body = new FormData();
    body.append("idProducto", idProducto + "");
    body.append("imagenProducto", imagenProducto);
    body.append("nombreImagen", nombreImagen);
    return this.http.post(environment.urlAddImagenProducto, body).subscribe();
  }

  busquedaProducto(nombre: string): Observable<Producto[]> {
    const param = new HttpParams().set("nombre", nombre);
    return this.http.get<Producto[]>(environment.urlBusquedaProducto, { params: param });
  }

  getHistorialProductos(idVendedor: number): Observable<CompraHist[]> {
    const param = new HttpParams().set("idVendedor", idVendedor + "");
    return this.http.get<CompraHist[]>(environment.urlGetHistorial, { params: param });
  }

  realizarCompra(compra: Compra): Observable<any> {
    const param = new HttpParams()
      .set("idComprador", compra.idComprador + "")
      .set("pais", "Colombia")
      .set("departamento", "Bogota")
      .set("ciudad", "Bogota")
      .set("tipoDocumento", compra.tipoDocumento)
      .set("numeroDocumento", compra.numeroDocumento)
      .set("nombreDestinatario", compra.nombreDestinatario)
      .set("direccionEnvio", compra.direccionEnvio)
      .set("observaciones", compra.observaciones)
      .set("telefonoUno", compra.telefonoUno)
      .set("telefonoDos", compra.telefonoDos);
    return this.http.get<any>(environment.urlRealizarCompra, { params: param });
  }
}
