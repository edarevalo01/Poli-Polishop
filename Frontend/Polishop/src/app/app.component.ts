import { Component, ViewEncapsulation, ViewChild } from '@angular/core';
import { CategoriaService } from './services/categoria.service';
import { Categoria } from './model/Categoria';
import { FormControl, Validators } from '@angular/forms';
import { UsuarioService } from './services/usuario.service';
import { Comprador } from './model/Comprador';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AppComponent {
  correo = new FormControl('', [Validators.required, Validators.email]);
  hide = true;
  hintColor: string;
  errorLogin: string = ' ';

  title = 'Polishop';
  categorias: Array<Categoria>;
  loginText = 'INGRESAR';

  email: string;
  password: string;

  comprador: Comprador = null;

  searchProduct: string;

  logueado: boolean = false;
  vendedor: boolean = false;

  constructor(private categoriaService: CategoriaService, private usuarioService: UsuarioService, private router: Router){
    this.hintColor = '#FFFFFF';
    this.loadCategorias();
    if(sessionStorage.getItem('user') != null){
      this.loginText = sessionStorage.getItem('nameLogin').toUpperCase();
      this.getInfoCompradorById(+sessionStorage.getItem('user'));
    }
    if(sessionStorage.getItem('seller') != null){
      this.loginText = sessionStorage.getItem('nameLogin').toUpperCase();
      this.vendedor = true;
    }
  }

  loginComprador(){
    this.usuarioService.loginUserComprador(this.email).subscribe(
      loginUsuarioObs => {
        if(loginUsuarioObs != null){
          if(this.password == loginUsuarioObs.contrasena){
            console.log('Usuario Verificado..');
            this.getInfoCompradorLogin(loginUsuarioObs.correo);
            this.errorLogin = '';
            this.hintColor = '#FFFFFF';
          }
          else{
            console.log('Contrasena inválida.');
            this.errorLogin = 'Contraseña incorrecta. Intente de nuevo.';
            this.hintColor = '#F10F0F';
          }
        }
        else{
          this.errorLogin = 'Correo incorrecto. Intente de nuevo.';
          this.hintColor = '#F10F0F';
        }
      },
      error => {
        console.error('ERROR_LOGIN: ', error);
      },
      () => {
        console.log("Login Successful.");
      }
    );
  }

  logoutComprador(){
    sessionStorage.clear();
    location.reload();
  }

  getInfoCompradorLogin(email: string){
    this.usuarioService.getInfoComprador(email).subscribe(
      infoCompradorObs => {
        this.comprador = infoCompradorObs;
      },
      error => {
        console.error("ERROR GET INFO: ", error);
      },
      () => {
        console.log("Comprador cargado exitosamente.");
        console.log(this.comprador);
        sessionStorage.setItem('user', this.comprador.id+'');
        sessionStorage.setItem('nameLogin', this.comprador.nombres.split(' ')[0]);
        this.loginText = this.comprador.nombres.split(' ')[0].toUpperCase();
        this.logueado = true;
        location.reload();
      }
    );
  }

  getInfoCompradorById(id: number){
    this.usuarioService.getInfoCompradorById(id).subscribe(
      infoCompradorObs => {
        this.comprador = infoCompradorObs;
      },
      error => {
        console.error("ERROR GET INFO: ", error);
      },
      () => {
        console.log("Comprador cargado exitosamente.");
        console.log(this.comprador);
        this.loginText = this.comprador.nombres.split(' ')[0].toUpperCase();
        this.logueado = true;
      }
    );
  }

  loadCategorias(){
    this.categoriaService.getAllCategorias().subscribe(
      categoriasObs => {
        this.categorias = categoriasObs;
      },
      error => {
        console.log('Error al cargar categorías ', error);
      },
      () => {
        console.log('Categorías cargadas exitosamente.');
      }
    );
  }

  getErrorMessage() {
    return this.correo.hasError('required') ? 'Debes ingresar un correo.' :
            this.correo.hasError('email') ? 'Correo inválido.' : '';
  }

  btnSearch(){
    if(this.searchProduct.trim() == ''){
      return 
    }
    this.router.navigate(
      ['search'],
      {queryParams: {
        object: this.searchProduct,
        type: 'search'
      },
      skipLocationChange: false}
    );
  }

  onKeydown(event){
    if(event.key == 'Enter'){
      this.btnSearch();
    }
  }

  searchCategoria(categoria){
    this.router.navigate(
      ['search'],
      {queryParams: {
        object: categoria.nombre,
        type: 'categoria'
      },
      skipLocationChange: false}
    );
  }

  modificarInfo(){
    if(this.vendedor){
      console.log('toca pensar bien esta xd');
    }
    else{
      console.log('y este también')
    }
  }

  goToInicio(){
    this.router.navigateByUrl('/inicio');
    this.searchProduct = '';
  }

}
