import { Component } from "@angular/core";
import { ModalPage } from "../modal/modal.page";
import { ModalController } from "@ionic/angular";

@Component({
  selector: "app-cuenta",
  templateUrl: "cuenta.page.html",
  styleUrls: ["cuenta.page.scss"]
})
export class CuentaPage {
  constructor(public modalController: ModalController) {}

  async iniciarSesion() {
    const modal = await this.modalController.create({
      component: ModalPage,
      componentProps: {
        nombre: "Alejandro",
        cel: "123"
      }
    });
    return await modal.present();
  }
}
