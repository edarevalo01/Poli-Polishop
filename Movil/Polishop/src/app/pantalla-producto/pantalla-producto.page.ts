import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { Producto } from "../model/producto";
import { GeneralService } from "../Services/general.service";
import { Comentario } from "../model/comentario";
import { Comprador } from "../model/comprador";
import { Storage } from "@ionic/storage";

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
  public userLogged: boolean = false;
  public usuario: Comprador = new Comprador();

  constructor(private service: GeneralService, private activeRoute: ActivatedRoute, private storage: Storage) {
    storage.get("user").then(val => {
      if (val !== null) {
        if (this.service.getCompradorLogin() === null) {
          this.getInfoCompradorById(val);
        } else {
          this.usuario = this.service.getCompradorLogin();
          this.userLogged = true;
        }
      }
    });
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
      () => {}
    );
  }

  getInfoCompradorById(id: number) {
    this.service.getInfoCompradorById(id).subscribe(
      infoCompradorObs => {
        this.usuario = infoCompradorObs;
      },
      error => {},
      () => {
        this.userLogged = true;
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
