import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs";
import { LoginUsuario } from "../model/LoginUsuario";
import { environment } from "src/environments/environment.prod";
import { Comprador } from "../model/Comprador";
import { Vendedor } from "../model/Vendedor";

@Injectable({
	providedIn: "root"
})
export class UsuarioService {
	constructor(private http: HttpClient) {}

	loginUserComprador(email: string): Observable<LoginUsuario> {
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

	loginUserVendedor(email: string): Observable<LoginUsuario> {
		const param = new HttpParams().set("correo", email);
		return this.http.get<LoginUsuario>(environment.urlLoginVendedor, { params: param });
	}

	getInfoVendedor(email: string): Observable<Vendedor> {
		const param = new HttpParams().set("correo", email);
		return this.http.get<Vendedor>(environment.urlGetInfoVendedor, { params: param });
	}

	getInfoVendedorById(id: number): Observable<Vendedor> {
		const param = new HttpParams().set("id", id + "");
		return this.http.get<Vendedor>(environment.urlGetInfoVendedorById, { params: param });
	}

	saveRegistroComprador(vendedor: Vendedor, foto: File) {
		const body = new FormData();
		body.append("nombres", vendedor.nombres);
		body.append("apellidos", vendedor.apellidos);
		body.append("correo", vendedor.correo);
		body.append("contrasena", vendedor.contrasena);
		body.append("descripcion", vendedor.descripcion);
		body.append("pais", "null");
		body.append("ciudad", "null");
		body.append("celular", vendedor.celular);
		body.append("urlFoto", foto);
		body.append("puntuacionVendedor", "0");
		return this.http.post(environment.urlAddVendedor, body).subscribe();
	}

	getAllVendedores(): Observable<Vendedor[]> {
		return this.http.get<Vendedor[]>(environment.urlGetAllVendedor);
	}
}
