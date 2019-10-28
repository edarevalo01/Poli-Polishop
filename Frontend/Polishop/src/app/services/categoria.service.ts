import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Categoria } from "../model/Categoria";
import { environment } from "src/environments/environment.prod";

@Injectable({
  providedIn: "root"
})
export class CategoriaService {
  constructor(private http: HttpClient) {}

  getAllCategorias(): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(environment.urlGetAllCategorias);
  }
}
