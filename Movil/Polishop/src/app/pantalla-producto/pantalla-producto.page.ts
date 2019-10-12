import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { Producto } from "../model/producto";
import { GeneralService } from "../Services/general.service";
import { Comentario } from "../model/comentario";
import { Comprador } from "../model/comprador";
import { Storage } from "@ionic/storage";
import { ToastController, ModalController } from '@ionic/angular';
import { ModalPage } from '../modal/modal.page';

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

  constructor(
    private service: GeneralService, 
    private activeRoute: ActivatedRoute, 
    private storage: Storage,
    private toastController: ToastController,
    private modalController: ModalController
  ) {
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

  async login() {
    const modal = await this.modalController.create({
      component: ModalPage,
      componentProps: {}
    });

    modal.onDidDismiss().then(data => {
      if(data.data.user !== undefined){
        this.usuario = data.data.user;
        this.userLogged = true;
        this.service.setCompradorLogin(this.usuario);
      }
    });
    return await modal.present();
  }

  agregarCarrito() {
    this.service.saveCarritoConProducto(this.producto.id, this.usuario.id, 1).subscribe(
      agregado => {},
      error => {},
      () => {
        this.presentToast('Producto agregado satisfactoriamente.');
      }
    );
  }

  agregarComentario() {
    //Servicio agregar comentario
  }

  async presentToast(mensaje: string) {
    const toast = await this.toastController.create({
      message: mensaje,
      duration: 2000
    });
    toast.present();
  }

  ngOnInit() {}

  getParams() {
    this.activeRoute.queryParams.subscribe(retorno => {
      let retid = retorno["idProd"];
      let retname = retorno["nameProd"];
      this.cargarProducto(retid);
    });
  }

  slideOpts = {
    initialSlide: 0,
    speed: 1000,
    autoplay: true
  };
}
