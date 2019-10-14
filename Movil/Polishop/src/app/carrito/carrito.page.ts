import { Component } from "@angular/core";
import { GeneralService } from "../Services/general.service";
import { Storage } from "@ionic/storage";
import { ToastController, ModalController, AlertController } from "@ionic/angular";
import { Comprador } from "../model/comprador";
import { ModalPage } from "../modal/modal.page";
import { ProductoCarrito } from "../model/producto-carrito";
import { Router } from "@angular/router";
import { CompraPage } from "../compra/compra.page";

@Component({
  selector: "app-carrito",
  templateUrl: "carrito.page.html",
  styleUrls: ["carrito.page.scss"]
})
export class CarritoPage {
  public userLogged: boolean = false;
  public usuario: Comprador = new Comprador();
  public productosCarrito: ProductoCarrito[] = [];
  public precioTotal: number = 0;

  constructor(
    private service: GeneralService,
    private storage: Storage,
    private toastController: ToastController,
    private modalController: ModalController,
    private router: Router,
    public alertController: AlertController
  ) {
    storage.get("user").then(val => {
      if (val !== null) {
        if (this.service.getCompradorLogin() === null) {
          this.getInfoCompradorById(val);
        } else {
          this.usuario = this.service.getCompradorLogin();
          this.userLogged = true;
          this.getProductosCarrito();
        }
      }
    });
  }

  getProductosCarrito() {
    this.precioTotal = 0;
    this.service.getProductosCarrito(this.usuario.id).subscribe(
      productosObs => {
        this.productosCarrito = productosObs;
      },
      error => {},
      () => {
        this.productosCarrito.forEach(producto => {
          this.precioTotal += parseInt(producto.valor) * producto.cantidad;
        });
      }
    );
  }

  sumProducto(cantidad: number, producto: ProductoCarrito) {
    var cantAct = producto.cantidad;
    if (cantAct + cantidad <= 0) return;
    else {
      this.productosCarrito.forEach(prodFor => {
        if (producto.idProducto === prodFor.idProducto) {
          prodFor.cantidad += cantidad;
          this.service.saveCarritoConProducto(prodFor.idProducto, this.usuario.id, cantidad).subscribe(
            respuesta => {},
            error => {},
            () => {
              this.presentToast("Producto agregado.");
              if (cantidad < 0) this.precioTotal -= parseInt(prodFor.valor);
              else if (cantidad > 0) this.precioTotal += parseInt(prodFor.valor);
            }
          );
        }
      });
    }
  }

  async confirmarEliminacion(producto: ProductoCarrito) {
    const alert = await this.alertController.create({
      header: "Eliminar producto",
      message: "Está seguro que desea <strong>eliminar el producto</strong> del carrito de compras?",
      buttons: [
        {
          text: "No",
          role: "cancel",
          cssClass: "secondary",
          handler: blah => {}
        },
        {
          text: "Si",
          handler: () => {
            this.service.eliminarProductoCarrito(producto.idCarrito, producto.idProducto).subscribe(
              respuesta => {},
              error => {},
              () => {
                //this.precioTotal -= parseInt(producto.valor) * producto.cantidad;
                this.presentToast("Producto eliminado del carrito.");
                this.getProductosCarrito();
              }
            );
          }
        }
      ],
      translucent: true
    });
    await alert.present();
  }

  getInfoCompradorById(id: number) {
    this.service.getInfoCompradorById(id).subscribe(
      infoCompradorObs => {
        this.usuario = infoCompradorObs;
      },
      error => {},
      () => {
        this.userLogged = true;
        this.getProductosCarrito();
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

  goProduct(producto: ProductoCarrito) {
    this.router.navigate(["/pantalla-producto/", producto.idProducto], {
      queryParams: {
        idProd: producto.idProducto,
        nameProd: producto.nombreProducto
      },
      skipLocationChange: false
    });
  }

  async realizarCompra() {
    const modal = await this.modalController.create({
      component: CompraPage,
      componentProps: {
        totalProductos: this.productosCarrito.length,
        totalPrecio: this.precioTotal,
        idComprador: this.usuario.id
      }
    });

    modal.onDidDismiss().then(data => {
      if (data.data.compra !== undefined) {
        if (data.data.compra) {
          //Se realiza la compra
        } else {
          //No se realiza compra
        }
      }
    });
    return await modal.present();
  }

  //FIXME: La solución para que cargue es probable que sea el observable, toca esperar
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
      }
    });
    return await modal.present();
  }
}
