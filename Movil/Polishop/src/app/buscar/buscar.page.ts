import { Component, OnInit } from "@angular/core";
import { GeneralService } from "../Services/general.service";
import { Producto } from "../model/producto";
import { ModalController } from "@ionic/angular";
import { Router } from "@angular/router";
import { ObservablePolishop, IObserverPolishop } from "../model/observable-polishop";

@Component({
  selector: "app-buscar",
  templateUrl: "./buscar.page.html",
  styleUrls: ["./buscar.page.scss"]
})
export class BuscarPage implements OnInit, IObserverPolishop {
  private observablePolishop: ObservablePolishop;
  private settedProductosPoli: boolean = false;
  private settedProductosComu: boolean = false;
  private productosObs: Producto[] = [];
  public productos: Producto[] = [];
  public searchTerm: string;

  constructor(private service: GeneralService, private modalCrtl: ModalController, private router: Router) {
    this.observablePolishop = ObservablePolishop.getInstance(service);
    this.observablePolishop.addObserver(this);
  }

  refrescarDatos() {
    if (this.observablePolishop.settedProductosPoli && !this.settedProductosPoli) {
      this.settedProductosPoli = true;
    }
    if (this.observablePolishop.settedProductosComu && !this.settedProductosComu) {
      this.settedProductosComu = true;
    }
    for (let i = 0; i < this.observablePolishop.productosPoli.length; i++) {
      this.productosObs.push(this.observablePolishop.productosPoli[i]);
      this.productosObs.push(this.observablePolishop.productosComu[i]);
    }
    this.productos = this.productosObs;
  }

  filterItemsFunct() {
    this.buscarProductoServ(this.searchTerm);
  }

  buscarProductoServ(criterio: string) {
    if (criterio.replace(/^\s+|\s+$|\s+(?=\s)/g, "") === "") {
      this.productos = this.productosObs;
    } else {
      this.service.buscarProducto(criterio.replace(/^\s+|\s+$|\s+(?=\s)/g, "")).subscribe(
        productosObs => {
          this.productos = productosObs;
        },
        error => {},
        () => {}
      );
    }
  }

  goProduct(producto: Producto) {
    this.router.navigate(["/pantalla-producto/", producto.id], {
      queryParams: {
        idProd: producto.id,
        nameProd: producto.nombre
      },
      skipLocationChange: false
    });
    this.cerrarModal();
  }

  cerrarModal() {
    this.modalCrtl.dismiss({
      dismissed: true
    });
  }

  ngOnInit() {}
}
