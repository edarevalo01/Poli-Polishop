import { Component, OnInit } from "@angular/core";
import { CompraService } from "src/app/services/compra.service";
import { ProductoCarrito } from "src/app/model/ProductoCarrito";
import { ConfirmationService, MessageService } from "primeng/api";
import { ProductoService } from "src/app/services/producto.service";
import { Router } from "@angular/router";
import { Compra } from "src/app/model/Compra";

@Component({
  selector: "app-pantalla-carrito",
  templateUrl: "./pantalla-carrito.component.html",
  styleUrls: ["./pantalla-carrito.component.css"],
  providers: [ConfirmationService, MessageService]
})
export class PantallaCarritoComponent implements OnInit {
  public productosCarrito: ProductoCarrito[] = [];
  public displayFormCompra: boolean = false;
  public totalPrecio: number = 0;
  public documentos: any[];
  public documentSelected: any = { name: "", value: "" };
  public compra: Compra = new Compra();

  constructor(
    private compraService: CompraService,
    private confirmationService: ConfirmationService,
    private productoService: ProductoService,
    private router: Router,
    private messageService: MessageService
  ) {
    if (!sessionStorage.getItem("user")) {
      router.navigateByUrl("inicio");
    }
    this.getProductosCarrito();
  }

  getProductosCarrito() {
    this.compraService.getProductosCarrito(+sessionStorage.getItem("user")).subscribe(
      productosObs => {
        this.productosCarrito = productosObs;
      },
      error => {},
      () => {
        this.productosCarrito.forEach(element => {
          this.totalPrecio += +element.valor * +element.cantidad;
        });
      }
    );
  }

  confirm(producto: ProductoCarrito) {
    this.confirmationService.confirm({
      message: "¿Está seguro que desea eliminar el producto?",
      header: "Eliminar producto del carrito de compras",
      icon: "pi pi-exclamation-triangle",
      accept: () => {
        this.compraService.eliminarProductoCarrito(producto.idCarrito, producto.idProducto);
        location.reload();
      },
      reject: () => {}
    });
  }

  sumProducto(cant: number, producto: ProductoCarrito) {
    var cantAct = producto.cantidad;
    if (cantAct + cant <= 0) return;
    else {
      this.totalPrecio = 0;
      this.productosCarrito.forEach(element => {
        if (producto.idProducto === element.idProducto) {
          element.cantidad += cant;
          this.productoService.saveCarritoConProducto(element.idProducto, +sessionStorage.getItem("user"), cant);
        }
        this.totalPrecio += +element.valor * +element.cantidad;
      });
    }
  }

  formCompra() {
    this.displayFormCompra = true;
  }

  realizarCompra() {
    this.compra.tipoDocumento = this.documentSelected.value;
    if (
      !this.compra.tipoDocumento ||
      !this.compra.numeroDocumento ||
      !this.compra.nombreDestinatario ||
      !this.compra.direccionEnvio ||
      !this.compra.telefonoUno
    ) {
      this.messageService.add({ severity: "warn", summary: "Ha ocurrido un error", detail: "Por favor verifique sus datos" });
    } else {
      this.displayFormCompra = false;
      this.confirmationService.confirm({
        header: "Realizar compra",
        message: "¿Está seguro que desea realizar la compra?",
        accept: () => {
          this.realizarCompraService();
        }
      });
    }
  }

  realizarCompraService() {
    this.compra.idComprador = +sessionStorage.getItem("user");
    this.productoService.realizarCompra(this.compra).subscribe(
      respuesta => {
        if (respuesta.status === "fail")
          this.messageService.add({
            severity: "warn",
            summary: "Ha ocurrido un error",
            detail: "Ha ocurrido un error al realizar la compra, intente nuevamente."
          });
        else {
          this.messageService.add({ severity: "success", summary: "¡Felicidades!", detail: "Compra realizada" });
          this.compra = new Compra();
          this.productosCarrito = [];
          this.totalPrecio = 0;
        }
      },
      error => {
        this.messageService.add({ severity: "warn", summary: "Ha ocurrido un error", detail: "Intente nuevamente" });
      }
    );
  }

  ngOnInit() {
    this.documentos = [
      { name: "Cédula de Ciudadanía", value: "cedulaCiudadania" },
      { name: "Tarjeta de Identidad", value: "tarjetaIdentidad" },
      { name: "Cédula de Extranjería", value: "cedulaExtranjeria" },
      { name: "Pasaporte", value: "pasaporte" }
    ];
  }
}
