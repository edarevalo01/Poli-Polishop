import { Component, OnInit, Input } from '@angular/core';
import { Comentario } from 'src/app/model/Comentario';

@Component({
  selector: 'app-comentario-producto',
  templateUrl: './comentario-producto.component.html',
  styleUrls: ['./comentario-producto.component.css']
})
export class ComentarioProductoComponent implements OnInit {

  constructor() {}

  @Input() mySelectedComentario: Comentario;

  ngOnInit() {
  }

}
