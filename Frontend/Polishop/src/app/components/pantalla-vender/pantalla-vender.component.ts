import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-pantalla-vender',
  templateUrl: './pantalla-vender.component.html',
  styleUrls: ['./pantalla-vender.component.css']
})
export class PantallaVenderComponent implements OnInit {
  panelOpenState = true;
  correo = new FormControl('', [Validators.required, Validators.email]);
  legalName: string = "Si te registras como vendedor individual, ingresa tu nombre completo. Si te registras como empresa, introduce el nombre registrado de la empresa y tu nombre completo (ej., Ventas-John Smith). ";
  
  constructor() { }

  ngOnInit() {
  }

  getErrorMessage() {
    return this.correo.hasError('required') ? 'Debes ingresar un correo.' :
            this.correo.hasError('email') ? 'Correo inválido.' : '';
  }

}
