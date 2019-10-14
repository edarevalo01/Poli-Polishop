import { Component, OnInit, Input } from "@angular/core";
import { ModalController, NavParams, ToastController, AlertController } from "@ionic/angular";
import { Compra } from "../model/compra";
import { GeneralService } from "../Services/general.service";

@Component({
  selector: "app-compra",
  templateUrl: "./compra.page.html",
  styleUrls: ["./compra.page.scss"]
})
export class CompraPage implements OnInit {
  @Input() totalProductos: number;
  @Input() totalPrecio: number;
  @Input() idComprador: number;
  public compraRealizada: boolean = false;
  public compra: Compra;
  public ola: string = "";

  constructor(
    private service: GeneralService,
    private navParams: NavParams,
    private modalCtrl: ModalController,
    private toastController: ToastController,
    public alertController: AlertController
  ) {
    this.compra = new Compra();
  }

  async realizarCompra() {
    const alert = await this.alertController.create({
      header: "Realizar compra",
      message: "¿Está seguro que desea realizar la compra?",
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
            this.realizarCompraService();
          }
        }
      ],
      translucent: true
    });
    await alert.present();
  }

  realizarCompraService() {
    this.compra.idComprador = this.idComprador;
    this.service.realizarCompra(this.compra).subscribe(
      respuesta => {
        if (respuesta.status === "fail") this.presentToast("Ha ocurrido un error al realizar la compra.");
        else {
          this.presentToast("¡Felicidades! Compra realizada.");
          this.compra = new Compra();
          this.cerrarModal();
        }
      },
      error => {
        this.presentToast("Ha ocurrido un error al realizar la compra.");
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

  cerrarModal() {
    this.modalCtrl.dismiss({
      dismissed: true,
      compra: this.compraRealizada
    });
  }

  ngOnInit() {}
}
