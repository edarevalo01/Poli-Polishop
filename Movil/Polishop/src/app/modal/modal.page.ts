import { Component, OnInit, Input } from "@angular/core";
import { NavParams, ModalController } from "@ionic/angular";

@Component({
  selector: "app-modal",
  templateUrl: "./modal.page.html",
  styleUrls: ["./modal.page.scss"]
})
export class ModalPage implements OnInit {
  @Input() nombre: string;
  @Input() cel: string;

  constructor(private navParams: NavParams, private modalCtrl: ModalController) {
    console.log(navParams.get("nombre"));
  }

  dismiss() {
    this.modalCtrl.dismiss();
  }

  ngOnInit() {}
}
