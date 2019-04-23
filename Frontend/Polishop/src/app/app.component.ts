import { Component, ViewEncapsulation } from '@angular/core';
import { CategoriaService } from './services/categoria.service';
import { Categoria } from './model/Categoria';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AppComponent {
  title = 'Polishop';
  categorias: Array<Categoria>;

  constructor(private categoriaService: CategoriaService){
    this.loadCategorias();
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
}
