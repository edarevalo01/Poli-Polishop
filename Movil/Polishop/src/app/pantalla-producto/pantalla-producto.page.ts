import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { Producto } from "../model/producto";
import { GeneralService } from "../Services/general.service";
import { Comentario } from "../model/comentario";

@Component({
  selector: "app-pantalla-producto",
  templateUrl: "./pantalla-producto.page.html",
  styleUrls: ["./pantalla-producto.page.scss"]
})
export class PantallaProductoPage implements OnInit {
  public producto: Producto = new Producto();
  public imgs = ["p1.png", "p2.png", "p3.png", "p4.png", "p5.png"];
  public cargado: boolean = false;
  public comentarios: Comentario[];

  constructor(private service: GeneralService, private activeRoute: ActivatedRoute) {
    this.getParams();
  }

  getParams() {
    this.activeRoute.queryParams.subscribe(retorno => {
      let retid = retorno["idProd"];
      let retname = retorno["nameProd"];
      this.cargarProducto(retid);
    });
  }

  cargarProducto(productoId: string) {
    this.service.getAllProductoById(+productoId).subscribe(
      productoObs => {
        this.producto = productoObs;
      },
      error => {},
      () => {
        this.cargado = true;
        this.cargarComentarios();
      }
    );
  }

  cargarComentarios() {
    this.service.getProductoComentarios(this.producto.id).subscribe(
      comentariosObs => {
        this.comentarios = comentariosObs;
      },
      error => {},
      () => {
        //Comentarios cargados.
        console.log(this.comentarios);
      }
    );
  }

  ngOnInit() {}

  slideOpts = {
    initialSlide: 0,
    speed: 1000,
    autoplay: true
  };
}
