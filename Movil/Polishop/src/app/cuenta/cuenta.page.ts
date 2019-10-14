import { Component } from "@angular/core";
import { ModalPage } from "../modal/modal.page";
import { ModalController } from "@ionic/angular";
import { Storage } from "@ionic/storage";
import { GeneralService } from "../Services/general.service";
import { Comprador } from "../model/comprador";
import { LinksPage } from "../links/links.page";

@Component({
  selector: "app-cuenta",
  templateUrl: "cuenta.page.html",
  styleUrls: ["cuenta.page.scss"]
})
export class CuentaPage {
  public userLogged: boolean = false;
  public usuario: Comprador = new Comprador();

  constructor(private services: GeneralService, private modalController: ModalController, private storage: Storage) {
    storage.get("user").then(val => {
      if (val !== null) {
        if (this.services.getCompradorLogin() === null) {
          this.getInfoCompradorById(val);
        } else {
          this.usuario = this.services.getCompradorLogin();
          this.userLogged = true;
        }
      }
    });
  }

  getInfoCompradorById(id: number) {
    this.services.getInfoCompradorById(id).subscribe(
      infoCompradorObs => {
        this.usuario = infoCompradorObs;
      },
      error => {},
      () => {
        this.userLogged = true;
      }
    );
  }

  imagenDefecto() {
    this.usuario.urlFoto = "assets/profilePhotos/users/sin.png";
  }

  async iniciarSesion() {
    if (this.userLogged) {
      this.userLogged = false;
      this.storage.clear();
      this.usuario = new Comprador();
      this.services.setCompradorLogin(null);
      this.imagenDefecto();
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
