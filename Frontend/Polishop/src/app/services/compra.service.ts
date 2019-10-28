import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { ProductoCarrito } from "../model/ProductoCarrito";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment.prod";

@Injectable({
  providedIn: "root"
})
export class CompraService {
  constructor(private http: HttpClient) {}

  getProductosCarrito(idComprador: number): Observable<ProductoCarrito[]> {
    const param = new HttpParams().set("idComprador", idComprador + "");
    return this.http.get<ProductoCarrito[]>(environment.urlGetProductosCarrito, { params: param });
  }

  eliminarProductoCarrito(idCarrito: number, idProducto: number) {
    const body = new HttpParams().set("idCarrito", idCarrito + "").set("idProducto", idProducto + "");
    return this.http.post(environment.urlEliminarProductoCarrito, body).subscribe();
  }
}
