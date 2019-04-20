import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Categoria } from 'src/app/model/Categoria';
import { CategoriaService } from 'src/app/services/categoria.service';

@Component({
  selector: 'app-pantalla-inicio',
  templateUrl: './pantalla-inicio.component.html',
  styleUrls: ['./pantalla-inicio.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class PantallaInicioComponent implements OnInit {
  title = 'Polishop';
  categorias: Array<Categoria>;
  imagenesCarrusel: string[];

  constructor(private categoriaService: CategoriaService){
    this.loadCategorias();
    this.cargarCarrusel();
  }

  cargarCarrusel(){
    this.imagenesCarrusel = [
      'assets/system/uno.jpg',
      'assets/system/dos.jpg',
      'assets/system/tres.jpg'
    ];
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

  ngOnInit() {
  }

}
