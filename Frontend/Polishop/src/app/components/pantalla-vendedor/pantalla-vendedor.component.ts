import { Component, OnInit } from '@angular/core';
import { ProductoService } from 'src/app/services/producto.service';
import { Producto } from 'src/app/model/Producto';
import { ConfirmationService } from 'primeng/api';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-pantalla-vendedor',
  templateUrl: './pantalla-vendedor.component.html',
  styleUrls: ['./pantalla-vendedor.component.css'],
  providers: [ConfirmationService]
})
export class PantallaVendedorComponent implements OnInit {
  productosVendedor: Producto[];
  productoSel: Producto = new Producto;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;

  display: boolean = false;
  nuevoNombre: string;
  nuevoPrecio: string;
  nuevaDescripcion: string;

  addNombre: string = '';
  addPrecio: string = '';
  addDescripcion: string = '';
  idNuevoProducto: string = '';
  uploadedFiles: any[] = [];
  
  constructor(private productoService: ProductoService, private confirmationService: ConfirmationService, private _formBuilder: FormBuilder, private snackBar: MatSnackBar) { 
    this.getProductosByVendedor();
  }

  getProductosByVendedor(){
    if(sessionStorage.getItem('seller')){
      this.productoService.getProductosByVendedor(+sessionStorage.getItem('seller')).subscribe(
        productosObs => {
          this.productosVendedor = productosObs;
        },
        error => {
          console.log('ERROR AL CARGAR PRODUCTOS: ', error);
        },
        () => {
          console.log('Productos Cargados.');
          console.log(this.productosVendedor)
        }
      );
    }
  }

  showDialog(producto: Producto) {
    this.display = true;
    this.nuevoNombre = producto.nombre;
    this.nuevoPrecio = producto.precio;
    this.nuevaDescripcion = producto.descripcion
    this.productoSel = producto;
  }

  eliminar(producto: Producto){
    this.confirmationService.confirm({
      message: '¿Está seguro que desea eliminar el producto?',
      header: 'Eliminar Producto',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.productoService.deleteProducto(producto.id);
        location.reload();
      },
      reject: () => {
        console.log('none');
      }
    });
  }

  actualizar(producto: Producto, accion){
    this.display = false;
    if(accion === 'cancelar') return;
    this.productoSel.nombre = this.nuevoNombre;
    this.productoSel.precio = this.nuevoPrecio;
    this.productoSel.descripcion = this.nuevaDescripcion;
    this.productoService.updateProducto(producto.id, this.nuevoNombre, this.nuevoPrecio, this.nuevaDescripcion).subscribe(
      retObs => {
        console.log(retObs);
      }, 
      error => {
        console.log('error :( ', error);
      }
    );
  }

  crearProducto(){
    if(this.idNuevoProducto === ''){
      this.productoService.addProducto(this.addNombre, this.addPrecio, this.addDescripcion, 'Poli', +sessionStorage.getItem('seller')).subscribe(
        retObs => {
          this.idNuevoProducto = retObs['success'];
        }, 
        error => {
          console.log('error :( ', error);
        },
        () => {
          console.log(this.idNuevoProducto);
        }
      );
    }
    else{
      this.productoService.updateProducto(+this.idNuevoProducto, this.addNombre, this.addPrecio, this.addDescripcion).subscribe(
        retObs => {
          console.log(retObs);
        }, 
        error => {
          console.log('error :( ', error);
        }
      );
    }
  }

  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });
  }

  onUpload(event) {
    if(this.idNuevoProducto === ''){
      this.snackBar.open('¡Primero debes completar el primer paso! (Información del producto)', 'Cerrar'); 
      return;
    }
    var cont = 1;
    var nombreImagenProducto = '';
    for(let file of event.files) {
      if(cont > 5) break;
      switch (cont) {
        case 1: nombreImagenProducto = 'p1'; break;
        case 2: nombreImagenProducto = 'p2'; break;
        case 3: nombreImagenProducto = 'p3'; break;
        case 4: nombreImagenProducto = 'p4'; break;
        case 5: nombreImagenProducto = 'p5'; break;
      }
      this.uploadedFiles.push(file);
      console.log(file)
      this.productoService.addImagenProducto(+this.idNuevoProducto, file, nombreImagenProducto);
      cont++;
    }
    this.snackBar.open('¡Producto guardado!', 'Cerrar');
  }

  reiniciarCrear(){
    location.reload();
  }

}
