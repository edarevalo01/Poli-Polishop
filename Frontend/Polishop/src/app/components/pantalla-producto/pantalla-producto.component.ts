import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductoService } from 'src/app/services/producto.service';
import { Producto } from 'src/app/model/Producto';
import { Comentario } from 'src/app/model/Comentario';

@Component({
  selector: 'app-pantalla-producto',
  templateUrl: './pantalla-producto.component.html',
  styleUrls: ['./pantalla-producto.component.css']
})
export class PantallaProductoComponent implements OnInit {

  imgCalificacionProducto: string;
  imgCalificacionVendedor: string;
  productoTmp: Producto;
  comentarios: Array<Comentario>;

  calificacionComentario: number = 0;
  comentario: string = '';

  imgs: string [];

  constructor(private productoService: ProductoService, private activeRoute: ActivatedRoute) { 
    scrollTo(0,0)
    activeRoute.queryParams.subscribe(
      retorno => {
        let retid = retorno['idProd'];
        let retname = retorno['nameProd'];
        this.cargarProducto(retid);
      }
    );
  }

  cargarProducto(retid){
    this.productoService.getAllProductoById(retid).subscribe(
      miProductoObs => {
        this.productoTmp = miProductoObs;
      },
      error => {
        console.log("ERROR: ", error);
      },
      () => {
        console.log("Producto cargado satisfactoriamente.");
        this.cargarComentarios();
        this.setImgCalificacionProducto(this.productoTmp.calificacion);
        this.setImgCalificacionVendedor(this.productoTmp.calificacionVendedor);
        var filess = (this.productoTmp.nombre+'guid'+this.productoTmp.nombreVendedor).replace(/[^A-Z0-9]/ig, '').toLowerCase();
        console.log(filess);
        this.imgs = ['p1.png', 'p2.png', 'p3.png', 'p4.png', 'p5.png'];
      }
    );
  }

  cargarComentarios(){
    this.productoService.getProductoComentarios(this.productoTmp.id).subscribe(
      comentariosObs => {
        this.comentarios = comentariosObs;
      },
      error => {
        console.error("Error al cargar comentarios", error);
      },
      () => {
        console.log("Comentarios cargados satisfactoriamente.");
      }
    );
  }

  //Es más breve, que reciba dos parametros, el numero y la imagen que va a reemplazar y así queda un sólo método
  setImgCalificacionProducto(calificacionProducto: number){
    switch(calificacionProducto){
      case 0: this.imgCalificacionProducto = 'assets/system/calificacionCero.png'; break;
      case 1: this.imgCalificacionProducto = 'assets/system/calificacionUno.png'; break;
      case 2: this.imgCalificacionProducto = 'assets/system/calificacionDos.png'; break;
      case 3: this.imgCalificacionProducto = 'assets/system/calificacionTres.png'; break;
      case 4: this.imgCalificacionProducto = 'assets/system/calificacionCuatro.png'; break;
      case 5: this.imgCalificacionProducto = 'assets/system/calificacionCinco.png'; break;
    }
  }

  setImgCalificacionVendedor(calificacionVendedor: number){
    switch(calificacionVendedor){
      case 0: this.imgCalificacionVendedor = 'assets/system/calificacionCero.png'; break;
      case 1: this.imgCalificacionVendedor = 'assets/system/calificacionUno.png'; break;
      case 2: this.imgCalificacionVendedor = 'assets/system/calificacionDos.png'; break;
      case 3: this.imgCalificacionVendedor = 'assets/system/calificacionTres.png'; break;
      case 4: this.imgCalificacionVendedor = 'assets/system/calificacionCuatro.png'; break;
      case 5: this.imgCalificacionVendedor = 'assets/system/calificacionCinco.png'; break;
    }
  }

  buttonColor1: string = '#fff';
  buttonColor2: string = '#fff';
  buttonColor3: string = '#fff';
  buttonColor4: string = '#fff';
  buttonColor5: string = '#fff';

  clickPuntuacion(number){
    this.calificacionComentario = number;
    if(number == 1){
      this.buttonColor1 = '#F1C40F';
      this.buttonColor2 = '#fff';
      this.buttonColor3 = '#fff';
      this.buttonColor4 = '#fff';
      this.buttonColor5 = '#fff';
    }
    else if(number == 2){
      this.buttonColor1 = '#F1C40F';
      this.buttonColor2 = '#F1C40F';
      this.buttonColor3 = '#fff';
      this.buttonColor4 = '#fff';
      this.buttonColor5 = '#fff';
    }
    else if(number == 3){
      this.buttonColor1 = '#F1C40F';
      this.buttonColor2 = '#F1C40F';
      this.buttonColor3 = '#F1C40F';
      this.buttonColor4 = '#fff';
      this.buttonColor5 = '#fff';
    }
    else if(number == 4){
      this.buttonColor1 = '#F1C40F';
      this.buttonColor2 = '#F1C40F';
      this.buttonColor3 = '#F1C40F';
      this.buttonColor4 = '#F1C40F';
      this.buttonColor5 = '#fff';
    }
    else if(number == 5){
      this.buttonColor1 = '#F1C40F';
      this.buttonColor2 = '#F1C40F';
      this.buttonColor3 = '#F1C40F';
      this.buttonColor4 = '#F1C40F';
      this.buttonColor5 = '#F1C40F';
    }
  }

  addComentario(){ //El id del comprador esta quemado.
    if(this.comentario == '') return;
    this.productoService.addComentarioProducto(+sessionStorage.getItem('user'), this.productoTmp.id, this.comentario, this.calificacionComentario);
    location.reload();
  }

  addCarrito(){
    this.productoService.saveCarritoConProducto(this.productoTmp.id, +sessionStorage.getItem('user'));
  }

  logueado(){
    if(sessionStorage.getItem('user') != null){
      return true;
    }
    return false;
  }

  compraHabilitada(){
    if(sessionStorage.getItem('user') != null){
      return false;
    }
    return true;
  }

  ngOnInit() {
  }

}
