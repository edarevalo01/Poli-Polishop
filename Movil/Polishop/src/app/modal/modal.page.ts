import { Component, OnInit, Input } from "@angular/core";
import { NavParams, ModalController } from "@ionic/angular";
import { Storage } from "@ionic/storage";
import { ToastController } from "@ionic/angular";
import { GeneralService } from "../Services/general.service";
import { LoginUsuario } from "../model/login-usuario";
import { Comprador } from "../model/comprador";

@Component({
  selector: "app-modal",
  templateUrl: "./modal.page.html",
  styleUrls: ["./modal.page.scss"]
})
export class ModalPage implements OnInit {
  public correoLogin: string;
  public contrasenaLogin: string;
  public loginUsuario: LoginUsuario;
  public usuarioComprador: Comprador;
  public passHide: boolean = true;

  constructor(
    private service: GeneralService,
    private modalCtrl: ModalController,
    private toastController: ToastController,
    private storage: Storage
  ) {}

  login() {
    this.service.loginUsuario(this.correoLogin).subscribe(
      loginObs => {
        if (loginObs != null) {
          if (this.contrasenaLogin == loginObs.contrasena) {
            this.getInfoComprador(loginObs.correo);
          } else {
            console.log("Contrasena inválida.");
            this.presentToast("Contraseña incorrecta. Intente de nuevo.");
          }
        } else {
          this.presentToast("Correo incorrecto. Intente de nuevo.");
        }
      },
      error => {},
      () => {}
    );
  }

  getInfoComprador(correo: string) {
    this.service.getInfoComprador(correo).subscribe(
      infoObs => {
        this.usuarioComprador = infoObs;
      },
      error => {},
      () => {
        this.presentToast("Inicio de sesión exitoso.");
        this.storage.set("user", this.usuarioComprador.id + "");
        this.storage.set("nameLogin", this.usuarioComprador.nombres.split(" ")[0]);
        this.service.setCompradorLogin(this.usuarioComprador);
        this.cerrarModal();
      }
    );
  }

  cerrarModal() {
    this.modalCtrl.dismiss({
      dismissed: true,
      user: this.usuarioComprador
    });
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
