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

  /**
   * En este método se implementa el servicio de correo que
   * envía la petición a los administradores de la aplicación.
   */
  enviar() {
    this.correo = "";
    this.nombre = "";
    this.mensaje = "";
    this.mostrar = true;
    setTimeout(() => {
      this.mostrar = false;
    }, 5000);
  }
}
