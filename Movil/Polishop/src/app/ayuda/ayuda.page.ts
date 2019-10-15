import { Component, OnInit } from "@angular/core";
import { GeneralService } from "../Services/general.service";
import { Comprador } from "../model/comprador";
import { Storage } from "@ionic/storage";
import { ToastController } from "@ionic/angular";

@Component({
  selector: "app-ayuda",
  templateUrl: "./ayuda.page.html",
  styleUrls: ["./ayuda.page.scss"]
})
export class AyudaPage implements OnInit {
  public userLogged: boolean = false;
  public usuario: Comprador = new Comprador();
  public nombreUsuario: string = "";
  public ayuda: boolean = true;
  public tyc: boolean = false;

  public nombre: string = "";
  public correo: string = "";
  public mensaje: string = "";

  constructor(private service: GeneralService, private toastController: ToastController, private storage: Storage) {
    storage.get("user").then(val => {
      if (val !== null) {
        if (this.service.getCompradorLogin() === null) {
          this.getInfoCompradorById(val);
        } else {
          this.usuario = this.service.getCompradorLogin();
          this.userLogged = true;
          this.nombreUsuario = this.usuario.nombres.split(" ")[0];
        }
      }
    });
  }

  getInfoCompradorById(id: number) {
    this.service.getInfoCompradorById(id).subscribe(
      infoCompradorObs => {
        this.usuario = infoCompradorObs;
      },
      error => {},
      () => {
        this.userLogged = true;
        this.nombreUsuario = this.usuario.nombres.split(" ")[0];
      }
    );
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
