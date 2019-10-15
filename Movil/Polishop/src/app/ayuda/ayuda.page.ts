import { Component, OnInit } from "@angular/core";
import { GeneralService } from "../Services/general.service";
import { Comprador } from "../model/comprador";
import { Storage } from "@ionic/storage";
import { ToastController } from "@ionic/angular";
import { ObservablePolishop, IObserverPolishop } from "../model/observable-polishop";

@Component({
  selector: "app-ayuda",
  templateUrl: "./ayuda.page.html",
  styleUrls: ["./ayuda.page.scss"]
})
export class AyudaPage implements OnInit, IObserverPolishop {
  public userLogged: boolean = false;
  //public usuario: Comprador = new Comprador();
  private observablePolishop: ObservablePolishop;
  private settedUsuario: boolean = false;

  public nombreUsuario: string = "";
  public ayuda: boolean = true;
  public tyc: boolean = false;

  public nombre: string = "";
  public correo: string = "";
  public mensaje: string = "";

  constructor(private service: GeneralService, private toastController: ToastController, private storage: Storage) {
    this.observablePolishop = ObservablePolishop.getInstance(service);
    this.observablePolishop.addObserver(this);
  }

  refrescarDatos() {
    console.log("entra");
    this.storage.get("user").then(val => {
      if (val !== null) {
        //Verifica si existe un id de usuario si no lo settea
        if (this.service.getIdUsuario() === "") {
          this.service.setIdUsuario(val);
          console.log("aca tambien????");
          if (this.observablePolishop.settedUsuario && !this.settedUsuario) {
            console.log("aca tambien");
            this.settedUsuario = true;
            this.nombreUsuario = this.observablePolishop.usuario.nombres.split(" ")[0];
          }
        } // Si si existe simplemente lo anuncia
        else {
          console.log("Y aca?");
          console.log(this.observablePolishop.settedUsuario && !this.settedUsuario);
          console.log(this.settedUsuario);
          if (this.observablePolishop.settedUsuario && !this.settedUsuario) {
            console.log("Y aca??????");
            this.settedUsuario = true;
            this.nombreUsuario = this.observablePolishop.usuario.nombres.split(" ")[0];
          }
        }
      }
    });
  }

  enviarAyuda() {
    this.nombre = "";
    this.correo = "";
    this.mensaje = "";
    this.presentToast("En poco tiempo estaremos en contacto contigo.");
  }

  async presentToast(mensaje: string) {
    const toast = await this.toastController.create({
      message: mensaje,
      duration: 2000
    });
    toast.present();
  }

  ngOnInit() {}
}
