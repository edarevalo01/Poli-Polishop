import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { Producto } from "../model/producto";
import { GeneralService } from "../Services/general.service";
import { Comentario } from "../model/comentario";
import { Comprador } from "../model/comprador";
import { Storage } from "@ionic/storage";
import { ToastController, ModalController, AlertController } from "@ionic/angular";
import { ModalPage } from "../modal/modal.page";
import { BuscarPage } from "../buscar/buscar.page";
import { ObservablePolishop, IObserverPolishop } from "../model/observable-polishop";

@Component({
  selector: "app-pantalla-producto",
  templateUrl: "./pantalla-producto.page.html",
  styleUrls: ["./pantalla-producto.page.scss"]
})
export class PantallaProductoPage implements OnInit, IObserverPolishop {
  private observablePolishop: ObservablePolishop;
  private settedUsuario: boolean = false;
  public producto: Producto = new Producto();
  public imgs = ["p1.png", "p2.png", "p3.png", "p4.png", "p5.png"];
  public cargado: boolean = false;
  public comentarios: Comentario[];
  public userLogged: boolean = false;
  public usuario: Comprador = new Comprador();
  public nuevoComentario: string = "";
  public nuevaPuntuacion: number = 5;

  constructor(
    private service: GeneralService,
    private activeRoute: ActivatedRoute,
    private storage: Storage,
    private toastController: ToastController,
    private modalController: ModalController,
    private alertController: AlertController
  ) {
    this.observablePolishop = ObservablePolishop.getInstance(service);
    this.observablePolishop.addObserver(this);
    this.getParams();
  }

  refrescarDatos() {
    if (this.observablePolishop.settedUsuario && !this.settedUsuario) {
      this.usuario = this.observablePolishop.usuario;
      this.settedUsuario = true;
      this.userLogged = true;
    }
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

  async login() {
    const modal = await this.modalController.create({
      component: ModalPage,
      componentProps: {}
    });

    modal.onDidDismiss().then(data => {
      if (data.data.user !== undefined) {
        this.usuario = data.data.user;
        this.userLogged = true;
        this.service.setCompradorLogin(this.usuario);
        this.service.setIdUsuario(this.usuario.id + "");
      }
    });
    return await modal.present();
  }

  agregarCarrito() {
    this.service.saveCarritoConProducto(this.producto.id, this.usuario.id, 1).subscribe(
      agregado => {},
      error => {},
      () => {
        // FIXME: Hay que verificar que el producto este agregado al carrito o no
        this.presentToast("Producto agregado satisfactoriamente.");
        this.observablePolishop.productosCarrito.push({
          idProducto: this.producto.id,
          nombreProducto: this.producto.nombre,
          nombreVendedor: this.producto.nombre,
          valor: this.producto.precio,
          cantidad: 1,
          urlCarpetaImagenes: this.producto.urlCarpetaImagenes,
          idCarrito: 0,
          idCompra: 0
        });
      }
    );
  }

  agregarComentario() {
    this.service.addComentarioProducto(this.usuario.id, this.producto.id, this.nuevoComentario, this.nuevaPuntuacion).subscribe(
      respuestaObs => {},
      error => {},
      () => {
        this.presentToast("Comentario enviado.");
        this.comentarios.push({
          id: 0,
          nombreComprador: this.usuario.nombres + " " + this.usuario.apellidos,
          nombreProducto: this.producto.nombre,
          comentario: this.nuevoComentario,
          fecha: "0 segundos",
          imagenComprador: this.usuario.urlFoto,
          puntuacionProducto: this.nuevaPuntuacion
        });
        this.nuevaPuntuacion = 5;
        this.nuevoComentario = "";
      }
    );
  }

  async presentToast(mensaje: string) {
    const toast = await this.toastController.create({
      message: mensaje,
      duration: 2000
    });
    toast.present();
  }

  async showInfoVendedor() {
    const alert = await this.alertController.create({
      header: "Detalles vendedor",
      subHeader: this.producto.nombreVendedor,
      message: this.producto.descripcionVendedor + "</br></br>Calificación: " + this.producto.calificacionVendedor + " estrellas.",
      buttons: ["OK"],
      translucent: true
    });

    await alert.present();
  }

  getParams() {
    this.activeRoute.queryParams.subscribe(retorno => {
      let retid = retorno["idProd"];
      let retname = retorno["nameProd"];
      this.cargarProducto(retid);
    });
  }

  async openSearch() {
    const modal = await this.modalController.create({
      component: BuscarPage,
      componentProps: {}
    });
    return await modal.present();
  }

  ngOnInit() {}

  slideOpts = {
    initialSlide: 0,
    speed: 1000,
    autoplay: true
  };
}
