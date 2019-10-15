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
export class TabsPage implements IObserverPolishop {
  private observablePolishop: ObservablePolishop;
  private settedUsuario: boolean = false;

  constructor(private service: GeneralService, private modalController: ModalController, private storage: Storage) {
    this.observablePolishop = ObservablePolishop.getInstance(service);
    this.observablePolishop.addObserver(this);
  }

  refrescarDatos() {
    this.storage.get("user").then(val => {
      if (val !== null) {
        //Verifica si existe un id de usuario si no lo settea
        if (this.service.getIdUsuario() === "") {
          this.service.setIdUsuario(val);
          if (this.observablePolishop.settedUsuario && !this.settedUsuario) {
            this.settedUsuario = true;
          }
        } // Si si existe simplemente lo anuncia
        else {
          if (this.observablePolishop.settedUsuario && !this.settedUsuario) {
            this.settedUsuario = true;
          }
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
