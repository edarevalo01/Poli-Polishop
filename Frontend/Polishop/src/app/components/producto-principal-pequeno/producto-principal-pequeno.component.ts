import { Component, OnInit, Input } from '@angular/core';
import { Producto } from 'src/app/model/Producto';

@Component({
  selector: 'app-producto-principal-pequeno',
  templateUrl: './producto-principal-pequeno.component.html',
  styleUrls: ['./producto-principal-pequeno.component.css']
})
export class ProductoPrincipalPequenoComponent implements OnInit {

  constructor() { }

  @Input() mySelectedProducto: Producto;

  ngOnInit() {
  }

}
