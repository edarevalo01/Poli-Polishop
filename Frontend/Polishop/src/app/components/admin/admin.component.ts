import { Component, OnInit } from "@angular/core";
import { Vendedor } from "src/app/model/Vendedor";
import { Producto } from "src/app/model/Producto";
import { UsuarioService } from "src/app/services/usuario.service";
import { ProductoService } from "src/app/services/producto.service";

@Component({
	selector: "app-admin",
	templateUrl: "./admin.component.html",
	styleUrls: ["./admin.component.css"]
})
export class AdminComponent implements OnInit {
	public vendedores: Vendedor[] = [];
	public productos: Producto[] = [];
	public showDetallesVendedor: boolean = false;
	public showDetallesProducto: boolean = false;
	public vendedorSel: Vendedor = new Vendedor();
	public productoSel: Producto = new Producto();

	constructor(private service: UsuarioService, private prodService: ProductoService) {
		this.getVendedores();
		this.getProductos();
	}

	getVendedores() {
		this.service.getAllVendedores().subscribe(
			(vendedoresObs) => {
				this.vendedores = vendedoresObs;
			},
			(error) => {
				console.error(error);
			},
			() => {
				console.log(this.vendedores);
			}
		);
	}

	getProductos() {
		this.prodService.getAllProductos().subscribe(
			(prodsObs) => {
				this.productos = prodsObs;
			},
			(error) => {
				console.error(error);
			},
			() => {
				console.log(this.productos);
			}
		);
	}

	verDetallesVendedor(vendedor) {
		this.vendedorSel = vendedor;
		setTimeout(() => {
			this.showDetallesVendedor = true;
		}, 200);
	}

	verDetallesProducto(producto) {
		this.productoSel = producto;
		setTimeout(() => {
			this.showDetallesProducto = true;
		}, 200);
	}

	ngOnInit() {}
}
