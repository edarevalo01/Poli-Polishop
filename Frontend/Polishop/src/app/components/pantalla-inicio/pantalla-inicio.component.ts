import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Producto } from 'src/app/model/Producto';
import { ProductoService } from 'src/app/services/producto.service';

@Component({
  selector: 'app-pantalla-inicio',
  templateUrl: './pantalla-inicio.component.html',
  styleUrls: ['./pantalla-inicio.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class PantallaInicioComponent implements OnInit {
  title = 'Polishop';
  productos: Array<Producto>;
  matrizPrincipales: Producto[][];

  constructor(private productoService: ProductoService){
    this.matrizPrincipales = [];
    productoService.getAllProductos().subscribe(
      misProductosObs => {
        this.productos = misProductosObs;
      },
      error => {
        console.log("Error: ", error);
      },
      () => {
        console.log("Productos cargados satisfactoriamente.");
        this.cargarProdPeq();
        console.log(this.matrizPrincipales);
      }
    );
  }

  cargarProdPeq(){
    var aux = 0;
    loop: for(var i = 0; i < 3; i++){
      this.matrizPrincipales[i] = [];
      for(var j = 0; j < 3; j++){
        if(aux == 9) break loop;
        this.matrizPrincipales[i][j] = this.productos[aux];
        aux++;
      }
    }
  }

  ngOnInit() {
  }

}
