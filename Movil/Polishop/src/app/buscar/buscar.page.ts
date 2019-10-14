import { Component, OnInit } from "@angular/core";
import { GeneralService } from "../Services/general.service";
import { Producto } from "../model/producto";
import { ModalController } from "@ionic/angular";
import { Router } from "@angular/router";

@Component({
  selector: "app-buscar",
  templateUrl: "./buscar.page.html",
  styleUrls: ["./buscar.page.scss"]
})
export class BuscarPage implements OnInit {
  public productos: Producto[] = [];
  public searchTerm: string;

  constructor(private service: GeneralService, private modalCrtl: ModalController, private router: Router) {
    this.buscarProductoServ("");
  }

  filterItemsFunct() {
    this.buscarProductoServ(this.searchTerm);
  }

  buscarProductoServ(criterio: string) {
    this.service.buscarProducto(criterio).subscribe(
      productosObs => {
        this.productos = productosObs;
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
    this.cerrarModal();
  }

  cerrarModal() {
    this.modalCrtl.dismiss({
      dismissed: true
    });
  }

  ngOnInit() {}
}
