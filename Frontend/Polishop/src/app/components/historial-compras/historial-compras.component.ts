import { Component, OnInit } from "@angular/core";
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
  public comprasList: CompraHist[] = [];
  public compraSel: CompraHist = new CompraHist();
  public display: boolean = false;
  public estados: Estado[] = [
    { nombre: "Todos", valor: null },
    { nombre: this.compraSel.estadoCompra, valor: this.compraSel.estadoCompra },
    { nombre: "Comprando", valor: "comprando" },
    { nombre: "Pendiente", valor: "pendiente" },
    { nombre: "Enviado", valor: "enviado" },
    { nombre: "Entregado", valor: "entregado" },
    { nombre: "Cancelado", valor: "cancelado" }
  ];
  public cols: any[] = [
    { field: "nombre", header: "Nombre" },
    { field: "estado", header: "Estado" },
    { field: "informacion", header: "Información" }
  ];
  public dropEstados: Estado[] = [
    { nombre: "Todos", valor: null },
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
    this.productoService
      .getHistorialProductos(+sessionStorage.getItem("seller"))
      .subscribe(
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

  exportExcel() {
    import("xlsx").then(xlsx => {
      const worksheet = xlsx.utils.json_to_sheet(this.getHistorialExcel());
      const workbook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
      const excelBuffer: any = xlsx.write(workbook, { bookType: 'xlsx', type: 'array' });
      this.saveAsExcelFile(excelBuffer, "resumen");
    });
  }

  saveAsExcelFile(buffer: any, fileName: string): void {
    import("file-saver").then(FileSaver => {
      let EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
      let EXCEL_EXTENSION = '.xlsx';
      const data: Blob = new Blob([buffer], {
          type: EXCEL_TYPE
      });
      FileSaver.saveAs(data, fileName + '_historialCompras_' + new Date().getTime() + EXCEL_EXTENSION);
    });
  }

  getHistorialExcel() {
    let historial = [];
    this.comprasList.map( compra => {
      historial.push({
        Nombre_Producto: compra.nombreProducto,
        Estado_Compra: compra.estadoCompra,
        Nombre_Comprador: compra.nombreComprador,
        Documento_Comprador: compra.documentoComprador,
        Nombre_Destinatario: compra.nombreDestinatario,
        Ciudad: compra.ciudadComprador,
        Direccion: compra.direccionComprador,
        Telefono_uno: compra.telefono1,
        Telefono_dos: compra.telefono2
      });
    })
    return historial;
  }

  ngOnInit() {}
}
