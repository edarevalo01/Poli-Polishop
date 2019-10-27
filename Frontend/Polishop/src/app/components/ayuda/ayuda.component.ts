import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-ayuda",
  templateUrl: "./ayuda.component.html",
  styleUrls: ["./ayuda.component.css"]
})
export class AyudaComponent implements OnInit {
  public nombre: string;
  public correo: string;
  public mensaje: string;
  public mostrar: boolean = false;
  public showTerminos: boolean = false;

  constructor() {}

  ngOnInit() {}

  enviar() {
    this.correo = "";
    this.nombre = "";
    this.mensaje = "";
    this.mostrar = true;
  }
}
