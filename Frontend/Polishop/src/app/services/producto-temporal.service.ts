import { Injectable } from '@angular/core';
import { Producto } from '../model/Producto';

@Injectable({
  providedIn: 'root'
})
export class ProductoTemporalService {

  private _actualProduct: Producto;
  
  public get actualProduct(): Producto {
    return this._actualProduct;
  }
  public set actualProduct(value: Producto) {
    this._actualProduct = value;
  }
  
}
