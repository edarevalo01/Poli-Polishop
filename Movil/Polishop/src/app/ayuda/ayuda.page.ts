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
  private observablePolishop: ObservablePolishop;
  private settedUsuario: boolean = false;
  public usuario: Comprador = new Comprador();

  public nombreUsuario: string = "";
  public ayuda: boolean = true;
  public tyc: boolean = false;
  public showEaster: boolean = false;
  public countEaster: number = 0;

  public nombre: string = "";
  public correo: string = "";
  public mensaje: string = "";

  constructor(private service: GeneralService, private toastController: ToastController, private storage: Storage) {
    this.observablePolishop = ObservablePolishop.getInstance(service);
    this.observablePolishop.addObserver(this);
  }

  refrescarDatos() {
    if (this.observablePolishop.settedUsuario && !this.settedUsuario) {
      this.usuario = this.observablePolishop.usuario;
      this.settedUsuario = true;
      this.nombreUsuario = " " + this.usuario.nombres.split(" ")[0];
    } else if (!this.observablePolishop.settedUsuario && this.settedUsuario) {
      this.settedUsuario = false;
      this.nombreUsuario = "";
    }
  }

  enviarAyuda() {
    this.nombre = "";
    this.correo = "";
    this.mensaje = "";
    this.presentToast("En poco tiempo estaremos en contacto contigo.");
  }

  easterEgg() {
    if (this.countEaster < 10) {
      this.countEaster++;
    } else {
      this.countEaster = 0;
      this.showEaster = true;
      setTimeout(() => {
        this.showEaster = false;
      }, 12000);
    }
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
