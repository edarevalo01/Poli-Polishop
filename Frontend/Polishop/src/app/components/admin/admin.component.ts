import { Component, OnInit } from "@angular/core";
import { Vendedor } from "src/app/model/Vendedor";
import { Producto } from "src/app/model/Producto";
import { UsuarioService } from "src/app/services/usuario.service";
import { ProductoService } from "src/app/services/producto.service";
import { ConfirmationService, Message, MessageService } from "primeng/api";
import { Router } from "@angular/router";

@Component({
	selector: "app-admin",
	templateUrl: "./admin.component.html",
	styleUrls: ["./admin.component.css"],
	providers: [ConfirmationService, MessageService]
})
export class AdminComponent implements OnInit {
	public vendedores: Vendedor[] = [];
	public productos: Producto[] = [];
	public showDetallesVendedor: boolean = false;
	public showDetallesProducto: boolean = false;
	public vendedorSel: Vendedor = new Vendedor();
	public productoSel: Producto = new Producto();
	public msgs: Message[] = [];

	constructor(
		private service: UsuarioService,
		private prodService: ProductoService,
		private confirmationService: ConfirmationService,
		private messageService: MessageService,
		private router: Router
	) {
		if (sessionStorage.getItem("NF9AC") == null) {
			this.router.navigateByUrl("/inicio");
		}
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
			() => {}
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
			() => {}
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

	deleteVendedor(vendedor) {
		this.confirmationService.confirm({
			message: "¿Está seguro que desea eliminar a este vendedor?",
			header: "Eliminar Vendedor",
			icon: "pi pi-exclamation-triangle",
			acceptLabel: "Si",
			rejectLabel: "No",
			accept: () => {
				this.service.deleteVendedor(vendedor.id).subscribe(
					(del) => {},
					(error) => {},
					() => {
						this.messageService.add({ severity: "success", summary: "Vendedor eliminado" });
						setTimeout(() => {
							location.reload();
						}, 1000);
					}
				);
			},
			reject: () => {}
		});
	}

	deleteProducto(producto) {
		this.confirmationService.confirm({
			message: "¿Está seguro que desea eliminar este producto?",
			header: "Eliminar Producto",
			icon: "pi pi-exclamation-triangle",
			acceptLabel: "Si",
			rejectLabel: "No",
			accept: () => {
				this.prodService.deleteProducto(producto.id).subscribe(
					(resp) => {},
					(error) => {},
					() => {
						this.messageService.add({ severity: "success", summary: "Producto eliminado" });
						setTimeout(() => {
							location.reload();
						}, 1000);
					}
				);
			},
			reject: () => {}
		});
	}

	ngOnInit() {}
}
