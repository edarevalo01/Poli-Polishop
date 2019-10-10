import { Component, OnInit, Input } from "@angular/core";
import { NavParams, ModalController } from "@ionic/angular";

@Component({
  selector: "app-modal",
  templateUrl: "./modal.page.html",
  styleUrls: ["./modal.page.scss"]
})
export class ModalPage implements OnInit {
  constructor(private navParams: NavParams, private modalCtrl: ModalController) {}

  cerrarModal() {
    this.modalCtrl.dismiss({
      dismissed: true
    });
  }

  ngOnInit() {}
}
