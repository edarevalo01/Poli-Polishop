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
      error => {
        console.error("Error al cargar productos principales poli");
      },
      () => {
        console.log("Productos Poli Done");
      }
    );
  }

  getProductosComunidad() {
    this.service.getProductosByDependencia("Comunidad").subscribe(
      comunidadObs => {
        this.productosComu = comunidadObs;
      },
      error => {
        console.error("Error al cargar productos principales de la comunidad");
      },
      () => {
        console.log("Productos Comunidad Done");
      }
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

  async delay(ms: number) {
    await new Promise(resolve => setTimeout(() => resolve(), ms)).then(() => location.reload());
  }

  slideOpts = {
    initialSlide: 0,
    speed: 1000,
    autoplay: true
  };
}
