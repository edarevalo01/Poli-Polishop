import { Component, OnInit, Input } from "@angular/core";
import { Comentario } from "src/app/model/Comentario";

@Component({
  selector: "app-comentario-producto",
  templateUrl: "./comentario-producto.component.html",
  styleUrls: ["./comentario-producto.component.css"]
})
export class ComentarioProductoComponent implements OnInit {
  constructor() {}

  /** Comentario enviado desde la carga de comentarios de la pantalla producto */
  @Input() mySelectedComentario: Comentario;

  ngOnInit() {}
}
