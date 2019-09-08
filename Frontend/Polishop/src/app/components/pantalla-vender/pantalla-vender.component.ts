import { Component, OnInit } from "@angular/core";
import { FormControl, Validators } from "@angular/forms";
import { UsuarioService } from "src/app/services/usuario.service";
import { Vendedor } from "src/app/model/Vendedor";
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: "app-pantalla-vender",
  templateUrl: "./pantalla-vender.component.html",
  styleUrls: ["./pantalla-vender.component.css"]
})
export class PantallaVenderComponent implements OnInit {
  panelOpenState = true;
  correo = new FormControl("", [Validators.required, Validators.email]);
  legalName: string =
    "Si te registras como vendedor individual, ingresa tu nombre completo. Si te registras como empresa, introduce el nombre registrado de la empresa y tu nombre completo (ej., Ventas-John Smith). ";
  hintColor: string;
  errorLogin: string = " ";

  email: string;
  password: string;

  vendedor: Vendedor = null;
  logueado: boolean = false;

  vendedorRegistro: Vendedor;
  imageVendedor: any;
  selectedFiles: FileList;

  uploadedFiles: any[] = [];
  hide1: boolean;
  
  constructor(private usuarioService: UsuarioService, private router: Router, private snackBar: MatSnackBar) {
    this.vendedorRegistro = new Vendedor();
    if (sessionStorage.getItem("seller") != null) {
      this.getInfoVendedorLoginById(+sessionStorage.getItem("seller"));
    }
    console.log(this.vendedorRegistro.apellidos);
  }

  ngOnInit() {}

  loginVendedor() {
    this.usuarioService.loginUserVendedor(this.email).subscribe(
      loginVendedorObs => {
        if (loginVendedorObs != null) {
          if (this.password === loginVendedorObs.contrasena) {
            console.log("Vendedor verificado.");
            this.getInfoVendedorLogin(loginVendedorObs.correo);
            this.errorLogin = "";
            this.hintColor = "#FFFFFF";
          } else {
            console.log("Contraseña inválida");
            this.errorLogin = "Contraseña incorrecta, intente de nuevo.";
            this.hintColor = "#F10F0F";
          }
        } else {
          this.errorLogin = "Correo incorrecto, intente de nuevo.";
          this.hintColor = "#F10F0F";
        }
      },
      error => {
        console.log("ERROR ", error);
      },
      () => {
        console.log("Login successful");
      }
    );
  }

  logoutVendedor() {
    sessionStorage.clear();
    location.reload();
  }

  getInfoVendedorLogin(correo: string) {
    this.usuarioService.getInfoVendedor(correo).subscribe(
      infoVendedorObs => {
        this.vendedor = infoVendedorObs;
      },
      error => {
        console.log("ERROR GET INFO", error);
      },
      () => {
        console.log("Vendedor cargado exitosamente.");
        console.log(this.vendedor);
        sessionStorage.setItem("seller", this.vendedor.id + "");
        sessionStorage.setItem( "nameLogin", this.vendedor.nombres.split(" ")[0] );
        this.logueado = true;
        location.reload();
      }
    );
  }

  getInfoVendedorLoginById(id: number) {
    this.usuarioService.getInfoVendedorById(id).subscribe(
      infoVendedorObs => {
        this.vendedor = infoVendedorObs;
      },
      error => {
        console.log("ERROR GET INFO", error);
      },
      () => {
        console.log("Vendedor cargado exitosamente.");
        console.log(this.vendedor);
        this.logueado = true;
        this.router.navigate(
          ['p-vendedor'],
          {queryParams: {
            object: this.vendedor.id,
            type: 'selling'
          },
          skipLocationChange: true}
        );
      }
    );
  }

  getErrorMessage() {
    return this.correo.hasError("required")? "Debes ingresar un correo." : 
              this.correo.hasError("email")? "Correo inválido." : "";
  }

  selectFile(event) {
    for(let file of event.files) {
      this.selectedFiles = file;
      this.uploadedFiles.push(file);
    }
  }

  registroVendedor(){
    this.snackBar.open('¡Gracias por registrarte! Tu solicitud de vendedor ha sido creada con éxito, en poco tiempo te enviaremos un correo con respuesta a tu solicitud. :)', 'Cerrar');
    this.imageVendedor = this.selectedFiles;
    this.usuarioService.saveRegistroComprador(this.vendedorRegistro, this.imageVendedor);
    this.vendedorRegistro = new Vendedor();
    this.uploadedFiles = [];
  }

}
