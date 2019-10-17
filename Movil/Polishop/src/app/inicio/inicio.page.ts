import { Component } from "@angular/core";
import { GeneralService } from "../Services/general.service";
import { Producto } from "../model/producto";
import { Router } from "@angular/router";
import { Storage } from "@ionic/storage";
import { ObservablePolishop, IObserverPolishop } from '../model/observable-polishop';

@Component({
  selector: "app-inicio",
  templateUrl: "inicio.page.html",
  styleUrls: ["inicio.page.scss"]
})
export class InicioPage implements IObserverPolishop{
  private observablePolishop: ObservablePolishop;
  private settedProductosPoli: boolean = false;
  private settedProductosComu: boolean = false;

  constructor(private service: GeneralService, private router: Router, private storage: Storage) {
    this.observablePolishop = ObservablePolishop.getInstance(service);
    this.observablePolishop.addObserver(this);
  }

  refrescarDatos() {
    if(this.observablePolishop.productosPoli && !this.settedProductosPoli){
      this.settedProductosPoli = true;
    }
    if(this.observablePolishop.productosComu && !this.settedProductosComu){
      this.settedProductosComu = true;
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
  }

  slideOpts = {
    initialSlide: 0,
    speed: 1000,
    autoplay: true
  };
}
