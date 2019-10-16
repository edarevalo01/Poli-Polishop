import { Component } from "@angular/core";
import { ModalPage } from "../modal/modal.page";
import { ModalController } from "@ionic/angular";
import { Storage } from "@ionic/storage";
import { GeneralService } from "../Services/general.service";
import { Comprador } from "../model/comprador";
import { LinksPage } from "../links/links.page";
import { ObservablePolishop, IObserverPolishop } from "../model/observable-polishop";

@Component({
  selector: "app-cuenta",
  templateUrl: "cuenta.page.html",
  styleUrls: ["cuenta.page.scss"]
})
export class CuentaPage implements IObserverPolishop {
  private observablePolishop: ObservablePolishop;
  private settedUsuario: boolean = false;
  public userLogged: boolean = false;
  public usuario: Comprador = new Comprador();

  constructor(private services: GeneralService, private modalController: ModalController, private storage: Storage) {
    this.observablePolishop = ObservablePolishop.getInstance(services);
    this.observablePolishop.addObserver(this);
  }

  refrescarDatos() {
    if (this.observablePolishop.settedUsuario && !this.settedUsuario) {
      this.usuario = this.observablePolishop.usuario;
      this.userLogged = true;
      this.settedUsuario = true;
    }
  }

  imagenDefecto() {
    this.usuario.urlFoto = "assets/profilePhotos/users/sin.png";
  }

  async iniciarSesion() {
    if (this.userLogged) {
      this.userLogged = false;
      this.settedUsuario = false;
      this.storage.clear();
      this.usuario = new Comprador();
      this.services.setCompradorLogin(null);
      this.services.setIdUsuario("");
      this.imagenDefecto();
      this.observablePolishop.deleteSesionUsuario();
    } else {
      const modal = await this.modalController.create({
        component: ModalPage,
        componentProps: {}
      });

      modal.onDidDismiss().then(data => {
        if (data.data.user !== undefined) {
          this.usuario = data.data.user;
          this.userLogged = true;
          this.services.setCompradorLogin(this.usuario);
        }
      });
      return await modal.present();
    }
  }

  async openLink(opcion: string) {
    const modal = await this.modalController.create({
      component: LinksPage,
      componentProps: {
        opcionPage: opcion
      }
    });
    return await modal.present();
  }
}
