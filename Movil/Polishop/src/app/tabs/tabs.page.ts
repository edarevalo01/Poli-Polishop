import { Component } from "@angular/core";
import { BuscarPage } from "../buscar/buscar.page";
import { ModalController } from "@ionic/angular";

@Component({
  selector: "app-tabs",
  templateUrl: "tabs.page.html",
  styleUrls: ["tabs.page.scss"]
})
export class TabsPage {
  constructor(private modalController: ModalController) {}

  async openSearch() {
    const modal = await this.modalController.create({
      component: BuscarPage,
      componentProps: {}
    });
    return await modal.present();
  }
}
