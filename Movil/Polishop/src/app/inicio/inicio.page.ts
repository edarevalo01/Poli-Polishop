import { Component } from "@angular/core";
import { GeneralService } from "../Services/general.service";
import { Producto } from "../model/producto";
import { Router } from "@angular/router";
import { Storage } from "@ionic/storage";

@Component({
  selector: "app-inicio",
  templateUrl: "inicio.page.html",
  styleUrls: ["inicio.page.scss"]
})
export class InicioPage {
  public productosPoli: Producto[] = [];
  public productosComu: Producto[] = [];

  constructor(private service: GeneralService, private router: Router, private storage: Storage) {
    this.getProductosPoli();
    this.getProductosComunidad();
  }

  getProductosPoli() {
    this.service.getProductosByDependencia("Poli").subscribe(
      poliObs => {
        this.productosPoli = poliObs;
      },
      error => {},
      () => {}
    );
  }

  getProductosComunidad() {
    this.service.getProductosByDependencia("Comunidad").subscribe(
      comunidadObs => {
        this.productosComu = comunidadObs;
      },
      error => {},
      () => {}
    );
  }

  goProduct(producto: Producto) {
    this.router.navigate(["/pantalla-producto/", producto.id], {
      queryParams: {
        idProd: producto.id,
        nameProd: producto.nombre
      },
      skipLocationChange: false
    });
  }

  slideOpts = {
    initialSlide: 0,
    speed: 1000,
    autoplay: true
  };
}
