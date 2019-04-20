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
}
