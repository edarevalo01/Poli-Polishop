import { Component, ViewEncapsulation, ViewChild } from "@angular/core";
import { CategoriaService } from "./services/categoria.service";
import { Categoria } from "./model/Categoria";
import { FormControl, Validators } from "@angular/forms";
import { UsuarioService } from "./services/usuario.service";
import { Comprador } from "./model/Comprador";
import { Router, NavigationEnd } from "@angular/router";
import { filter } from "rxjs/operators";
declare var gtag;

@Component({
	selector: "app-root",
	templateUrl: "./app.component.html",
	styleUrls: ["./app.component.css"],
	encapsulation: ViewEncapsulation.None
})
export class AppComponent {
	public correo = new FormControl("", [Validators.required, Validators.email]);
	public hide = true;
	public hintColor: string;
	public errorLogin: string = " ";

	public title = "Polishop";
	public categorias: Array<Categoria>;
	public loginText = "INGRESAR";

	public email: string;
	public password: string;

	public comprador: Comprador = null;
	public searchProduct: string;

	public logueado: boolean = false;
	public vendedor: boolean = false;

	public admin: boolean = false;

	constructor(private categoriaService: CategoriaService, private usuarioService: UsuarioService, private router: Router) {
		const navEndEvent = this.router.events.pipe(filter((event) => event instanceof NavigationEnd));
		navEndEvent.subscribe((event: NavigationEnd) => {
			gtag("config", "UA-154462554-1", {
				page_path: event.urlAfterRedirects
			});
		});

		this.hintColor = "#FFFFFF";
		this.loadCategorias();
		if (sessionStorage.getItem("user") != null) {
			this.loginText = sessionStorage.getItem("nameLogin").toUpperCase();
			this.getInfoCompradorById(+sessionStorage.getItem("user"));
		}
		if (sessionStorage.getItem("seller") != null) {
			this.loginText = sessionStorage.getItem("nameLogin").toUpperCase();
			this.vendedor = true;
		}
		if (sessionStorage.getItem("NF9AC") != null) {
			this.admin = true;
			this.router.navigateByUrl("admin-page");
			this.loginText = "Administrador";
			this.logueado = true;
		}
	}

	loginComprador() {
		if (this.email == "admin@poligran.edu.co") {
			sessionStorage.setItem("NF9AC", "ZDmCIdnAli0a4qvHW8d2");
			location.reload();
		} else {
			this.usuarioService.loginUserComprador(this.email).subscribe(
				(loginUsuarioObs) => {
					if (loginUsuarioObs != null) {
						if (this.password == loginUsuarioObs.contrasena) {
							this.getInfoCompradorLogin(loginUsuarioObs.correo);
							this.errorLogin = "";
							this.hintColor = "#FFFFFF";
						} else {
							this.errorLogin = "Contraseña incorrecta. Intente de nuevo.";
							this.hintColor = "#F10F0F";
						}
					} else {
						this.errorLogin = "Correo incorrecto. Intente de nuevo.";
						this.hintColor = "#F10F0F";
					}
				},
				(error) => {},
				() => {}
			);
		}
	}

	logoutComprador() {
		sessionStorage.clear();
		location.reload();
	}

	getInfoCompradorLogin(email: string) {
		this.usuarioService.getInfoComprador(email).subscribe(
			(infoCompradorObs) => {
				this.comprador = infoCompradorObs;
			},
			(error) => {},
			() => {
				sessionStorage.setItem("user", this.comprador.id + "");
				sessionStorage.setItem("nameLogin", this.comprador.nombres.split(" ")[0]);
				this.loginText = this.comprador.nombres.split(" ")[0].toUpperCase();
				this.logueado = true;
				location.reload();
			}
		);
	}

	getInfoCompradorById(id: number) {
		this.usuarioService.getInfoCompradorById(id).subscribe(
			(infoCompradorObs) => {
				this.comprador = infoCompradorObs;
			},
			(error) => {},
			() => {
				this.loginText = this.comprador.nombres.split(" ")[0].toUpperCase();
				this.logueado = true;
			}
		);
	}

	loadCategorias() {
		this.categoriaService.getAllCategorias().subscribe(
			(categoriasObs) => {
				this.categorias = categoriasObs;
			},
			(error) => {},
			() => {}
		);
	}

	getErrorMessage() {
		return this.correo.hasError("required")
			? "Debes ingresar un correo."
			: this.correo.hasError("email")
			? "Correo inválido."
			: "";
	}

	btnSearch() {
		if (this.searchProduct.trim() == "") {
			return;
		}
		this.router.navigate(["search"], {
			queryParams: {
				object: this.searchProduct,
				type: "search"
			},
			skipLocationChange: false
		});
	}

	onKeydown(event) {
		if (event.key == "Enter") {
			this.btnSearch();
		}
	}

	searchCategoria(categoria) {
		this.router.navigate(["search"], {
			queryParams: {
				object: categoria.nombre,
				type: "categoria"
			},
			skipLocationChange: false
		});
	}

	modificarInfo() {
		if (this.vendedor) {
		} else {
		}
	}

	goToInicio() {
		if (!this.admin) {
			this.router.navigateByUrl("/inicio");
			this.searchProduct = "";
		}
	}

	carritoHabilitado() {
		if (sessionStorage.getItem("user") != null) {
			return true;
		}
		return false;
	}

	goShoppingCart() {
		this.router.navigateByUrl("shopping-cart");
	}
}
