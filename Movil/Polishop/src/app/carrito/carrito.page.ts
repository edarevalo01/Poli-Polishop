import { Component } from "@angular/core";
import { GeneralService } from "../Services/general.service";
import { Storage } from "@ionic/storage";
import { ToastController, ModalController, AlertController } from "@ionic/angular";
import { Comprador } from "../model/comprador";
import { ModalPage } from "../modal/modal.page";
import { ProductoCarrito } from "../model/producto-carrito";
import { Router } from "@angular/router";
import { CompraPage } from "../compra/compra.page";
import { ObservablePolishop, IObserverPolishop } from "../model/observable-polishop";
import { Producto } from "../model/producto";

@Component({
  selector: "app-carrito",
  templateUrl: "carrito.page.html",
  styleUrls: ["carrito.page.scss"]
})
export class CarritoPage implements IObserverPolishop {
  private observablePolishop: ObservablePolishop;
  private settedUsuario: boolean = false;
  private settedCarrito: boolean = false;
  public usuario: Comprador = new Comprador();

  constructor(
    private service: GeneralService,
    private storage: Storage,
    private toastController: ToastController,
    private modalController: ModalController,
    private router: Router,
    public alertController: AlertController
  ) {
    this.observablePolishop = ObservablePolishop.getInstance(service);
    this.observablePolishop.addObserver(this);
  }

  refrescarDatos() {
    if (this.observablePolishop.settedUsuario && !this.settedUsuario) {
      this.usuario = this.observablePolishop.usuario;
      this.settedUsuario = true;
    } else if (!this.observablePolishop.settedUsuario && this.settedUsuario) {
      this.settedUsuario = false;
    }

    if (this.observablePolishop.settedProductosCarrito && !this.settedCarrito) {
      this.settedCarrito = true;
    } else if (!this.observablePolishop.settedProductosCarrito && this.settedCarrito) {
      this.settedCarrito = false;
    }
  }

  sumProducto(cantidad: number, producto: ProductoCarrito) {
    var cantAct = producto.cantidad;
    if (cantAct + cantidad <= 0) return;
    else {
      this.observablePolishop.productosCarrito.forEach(prodFor => {
        if (producto.idProducto === prodFor.idProducto) {
          prodFor.cantidad += cantidad;
          this.service.saveCarritoConProducto(prodFor.idProducto, this.usuario.id, cantidad).subscribe(
            respuesta => {},
            error => {},
            () => {
              this.presentToast("Producto agregado.");
              if (cantidad < 0) this.observablePolishop.precioTotal -= parseInt(prodFor.valor);
              else if (cantidad > 0) this.observablePolishop.precioTotal += parseInt(prodFor.valor);
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
                var copyProductos = this.observablePolishop.productosCarrito;
                this.observablePolishop.productosCarrito = [];
                for (let i = 0; i < copyProductos.length; i++) {
                  if (producto.idProducto === copyProductos[i].idProducto && producto.idCarrito === copyProductos[i].idCarrito) {
                  } else {
                    this.observablePolishop.productosCarrito.push(copyProductos[i]);
                  }
                }
                this.observablePolishop.precioTotal -= parseInt(producto.valor) * producto.cantidad;
              }
            );
          }
        }
      ],
      translucent: true
    });
    await alert.present();
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
        totalProductos: this.observablePolishop.productosCarrito.length,
        totalPrecio: this.observablePolishop.precioTotal,
        idComprador: this.usuario.id
      }
    });

    modal.onDidDismiss().then(data => {
      if (data.data.compra !== undefined) {
        if (data.data.compra) {
          this.observablePolishop.productosCarrito = [];
          this.observablePolishop.productosCarrito = [];
          this.observablePolishop.precioTotal = 0;
        }
      }
    });
    return await modal.present();
  }

  doRefresh(event) {
    this.observablePolishop.refrescarCarrito();
    setTimeout(() => {
      event.target.complete();
    }, 2000);
  }
}
