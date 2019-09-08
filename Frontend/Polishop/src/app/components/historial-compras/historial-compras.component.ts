import { Component, OnInit } from '@angular/core';
import { Compra } from 'src/app/model/Compra';
import { ProductoService } from 'src/app/services/producto.service';
import { Router } from '@angular/router';
import { CompraHist } from 'src/app/model/CompraHist';
import { FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { ConfirmationService } from 'primeng/api';
import { Estado } from 'src/app/model/Estado';

@Component({
  selector: 'app-historial-compras',
  templateUrl: './historial-compras.component.html',
  styleUrls: ['./historial-compras.component.css'],
  providers: [ConfirmationService]
})
export class HistorialComprasComponent implements OnInit {
  comprasList: CompraHist[];
  compraSel: CompraHist = new CompraHist();
  display: boolean = false;

  estados: Estado[] = [
    {nombre: this.compraSel.estadoCompra, valor: this.compraSel.estadoCompra},
    {nombre: 'Comprando', valor: 'comprando'},
    {nombre: 'Pendiente', valor: 'pendiente'},
    {nombre: 'Enviado', valor: 'enviado'},
    {nombre: 'Entregado', valor: 'entregado'},
    {nombre: 'Cancelado', valor: 'cancelado'}
  ];

  constructor(private productoService: ProductoService, private confirmationService: ConfirmationService, private _formBuilder: FormBuilder, private snackBar: MatSnackBar, private router: Router) {
    if(!sessionStorage.getItem('seller')){
      this.router.navigateByUrl('inicio');
    }
    this.getComprasByVendedor();
  }

  getComprasByVendedor(){
    this.productoService.getHistorialProductos(+sessionStorage.getItem('seller')).subscribe(
      comprasObs => {
        this.comprasList = comprasObs;
      },
      error => {
        console.error('ERROR al traer el historial de compras: ', error);
      },
      () => {
        console.log(this.comprasList);
        console.log('Historial de compra cargada.');
      }
    );
  }

  showDialog(compra: CompraHist){
    this.compraSel = compra;
    this.display = true
  }

  ngOnInit() {
  }

}
