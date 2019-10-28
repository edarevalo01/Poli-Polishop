import { Component, OnInit } from "@angular/core";
import { Compra } from "src/app/model/Compra";
import { ProductoService } from "src/app/services/producto.service";
import { Router } from "@angular/router";
import { CompraHist } from "src/app/model/CompraHist";
import { FormBuilder } from "@angular/forms";
import { MatSnackBar } from "@angular/material";
import { ConfirmationService } from "primeng/api";
import { Estado } from "src/app/model/Estado";

@Component({
  selector: "app-historial-compras",
  templateUrl: "./historial-compras.component.html",
  styleUrls: ["./historial-compras.component.css"],
  providers: [ConfirmationService]
})
export class HistorialComprasComponent implements OnInit {
  public comprasList: CompraHist[];
  public compraSel: CompraHist = new CompraHist();
  public display: boolean = false;
  public estados: Estado[] = [
    { nombre: this.compraSel.estadoCompra, valor: this.compraSel.estadoCompra },
    { nombre: "Comprando", valor: "comprando" },
    { nombre: "Pendiente", valor: "pendiente" },
    { nombre: "Enviado", valor: "enviado" },
    { nombre: "Entregado", valor: "entregado" },
    { nombre: "Cancelado", valor: "cancelado" }
  ];
  public labelBtnAvanzar: string = "Cambiar a enviado";
  public showchange: boolean = true;

  constructor(
    private productoService: ProductoService,
    private confirmationService: ConfirmationService,
    private _formBuilder: FormBuilder,
    private snackBar: MatSnackBar,
    private router: Router
  ) {
    if (!sessionStorage.getItem("seller")) {
      this.router.navigateByUrl("inicio");
    }
    this.getComprasByVendedor();
  }

  getComprasByVendedor() {
    this.productoService.getHistorialProductos(+sessionStorage.getItem("seller")).subscribe(
      comprasObs => {
        this.comprasList = comprasObs;
      },
      error => {},
      () => {}
    );
  }

  showDialog(compra: CompraHist) {
    this.compraSel = compra;
    this.display = true;
    this.changeLabelBtn();
  }

  changeLabelBtn() {
    switch (this.compraSel.estadoCompra.trim()) {
      case "pendiente":
        this.labelBtnAvanzar = "Cambiar a enviado";
        this.showchange = true;
        break;
      case "enviado":
        this.labelBtnAvanzar = "Cambiar a entregado";
        this.showchange = true;
        break;
      case "entregado":
        this.labelBtnAvanzar = "";
        this.showchange = false;
        break;
    }
  }

  avanzar() {
    switch (this.labelBtnAvanzar.trim()) {
      case "Cambiar a enviado":
        this.compraSel.estadoCompra = "enviado";
        this.showchange = true;
        this.labelBtnAvanzar = "Cambiar a entregado";
        break;
      case "Cambiar a entregado":
        this.compraSel.estadoCompra = "entregado";
        this.showchange = false;
        break;
    }
  }

  ngOnInit() {}
}
