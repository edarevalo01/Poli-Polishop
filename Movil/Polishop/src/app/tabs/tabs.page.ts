import { Component } from "@angular/core";
import { BuscarPage } from "../buscar/buscar.page";
import { ModalController } from "@ionic/angular";
import { Storage } from "@ionic/storage";
import { GeneralService } from "../Services/general.service";
import { ObservablePolishop, IObserverPolishop } from "../model/observable-polishop";

@Component({
  selector: "app-tabs",
  templateUrl: "tabs.page.html",
  styleUrls: ["tabs.page.scss"]
})
export class TabsPage {
  private observablePolishop: ObservablePolishop;

  constructor(private service: GeneralService, private modalController: ModalController, private storage: Storage) {
    this.observablePolishop = ObservablePolishop.getInstance(service);
    this.storage.get("user").then(val => {
      if (val !== null) {
        if (this.service.getIdUsuario() === "") {
          this.service.setIdUsuario(val);
          this.observablePolishop.getUsuarioFirstTime();
        } else {
          this.observablePolishop.getUsuarioFirstTime();
        }
      }
    });
  }

  async openSearch() {
    const modal = await this.modalController.create({
      component: BuscarPage,
      componentProps: {}
    });
    return await modal.present();
  }
}
