import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-ayuda',
  templateUrl: './ayuda.component.html',
  styleUrls: ['./ayuda.component.css']
})
export class AyudaComponent implements OnInit {

  nombre: string;
  correo: string;
  mensaje: string;
  mostrar: boolean = false;

  constructor() { }

  ngOnInit() {
  }

  enviar(){
    this.correo = '';
    this.nombre = '';
    this.mensaje = '';
    this.mostrar = true;
  }

}
