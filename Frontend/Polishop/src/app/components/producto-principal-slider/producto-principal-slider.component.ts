import { Component, OnInit, Input } from '@angular/core';
import { Producto } from 'src/app/model/Producto';

@Component({
  selector: 'app-producto-principal-slider',
  templateUrl: './producto-principal-slider.component.html',
  styleUrls: ['./producto-principal-slider.component.css']
})
export class ProductoPrincipalSliderComponent implements OnInit {

  constructor() { }

  @Input() mySelectedProducto: Producto;
  
  ngOnInit() {
  }

}
