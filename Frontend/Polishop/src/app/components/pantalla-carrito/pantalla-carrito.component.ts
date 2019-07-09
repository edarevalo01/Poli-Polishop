import { Component, OnInit } from '@angular/core';
import { CompraService } from 'src/app/services/compra.service';
import { ProductoCarrito } from 'src/app/model/ProductoCarrito';
import { ConfirmationService } from 'primeng/api';
import { ProductoService } from 'src/app/services/producto.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pantalla-carrito',
  templateUrl: './pantalla-carrito.component.html',
  styleUrls: ['./pantalla-carrito.component.css'],
  providers: [ConfirmationService]
})
export class PantallaCarritoComponent implements OnInit {

  productosCarrito: ProductoCarrito[];

  totalPrecio: number = 0;

  constructor(private compraService: CompraService, private confirmationService: ConfirmationService, private productoService: ProductoService, private router: Router) {
    if(!sessionStorage.getItem('user')){
      router.navigateByUrl('inicio');
    }
    this.getProductosCarrito();
  }

  getProductosCarrito(){
    this.compraService.getProductosCarrito(+sessionStorage.getItem('user')).subscribe(
      productosObs => {
        this.productosCarrito = productosObs;
      },
      error => {
        console.error('ERROR al cargar PRODUCTOS - CARRITO: ', error);
      },
      () => {
        console.log(this.productosCarrito);
        this.productosCarrito.forEach(element => {
          this.totalPrecio += (+element.valor) * (+element.cantidad);
        });
      }
    )
  }

  confirm(producto: ProductoCarrito) {
    this.confirmationService.confirm({
      message: '¿Está seguro que desea eliminar el producto?',
      header: 'Eliminar producto del carrito de compras',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.compraService.eliminarProductoCarrito(producto.idCarrito, producto.idProducto);
        location.reload();
      },
      reject: () => {
        console.log('none');
      }
    });
  }

  sumProducto(cant: number, producto: ProductoCarrito){
    var cantAct = producto.cantidad;
    if(cantAct + cant <= 0)return;
    else{
      this.totalPrecio = 0;
      this.productosCarrito.forEach(element => {
        if(producto.idProducto === element.idProducto){
          element.cantidad += cant;
          this.productoService.saveCarritoConProducto(element.idProducto, +sessionStorage.getItem('user'), cant);
        }
        this.totalPrecio += (+element.valor) * (+element.cantidad);
      });
    }
  }

  ngOnInit() {
  }

}
