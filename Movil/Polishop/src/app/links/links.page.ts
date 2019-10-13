import { Component, OnInit, Input } from "@angular/core";
import { NavParams, ModalController } from "@ionic/angular";

@Component({
  selector: "app-links",
  templateUrl: "./links.page.html",
  styleUrls: ["./links.page.scss"]
})
export class LinksPage implements OnInit {
  @Input() opcionPage: string;
  public url: string = "";

  constructor(navParams: NavParams, private modalCtrl: ModalController) {
    //TODO: Probar si los links sirven cuando estoy bajo el dominio del poli
    this.opcionPage = navParams.get("opcionPage");
    if (this.opcionPage === "poli") {
      this.url = "https://poli.edu.co";
    } else if (this.opcionPage === "smart") {
      this.url = "https://autenticacion.poligran.edu.co/identity/login?signin=8e8ed3410357359449d60274653be57d";
    }
  }

  cerrarModal() {
    this.modalCtrl.dismiss({
      dismissed: true
    });
  }

  ngOnInit() {}
}
