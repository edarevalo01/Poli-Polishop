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

  public estadistica: boolean = false;
  public ventas: any = [
    { mes: "Enero", venta: 0 },
    { mes: "Febrero", venta: 0 },
    { mes: "Marzo", venta: 0 },
    { mes: "Abril", venta: 0 },
    { mes: "Mayo", venta: 0 },
    { mes: "Junio", venta: 0 },
    { mes: "Julio", venta: 0 },
    { mes: "Agosto", venta: 0 },
    { mes: "Septiembre", venta: 0 },
    { mes: "Octubre", venta: 0 },
    { mes: "Noviembre", venta: 0 },
    { mes: "Diciembre", venta: 0 }
  ];
  public data: any;
  public options: any;

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
        () => {
          this.metodoEstadistica();
        }
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
      const workbook = { Sheets: { data: worksheet }, SheetNames: ["data"] };
      const excelBuffer: any = xlsx.write(workbook, {
        bookType: "xlsx",
        type: "array"
      });
      this.saveAsExcelFile(excelBuffer, "resumen");
    });
  }

  saveAsExcelFile(buffer: any, fileName: string): void {
    import("file-saver").then(FileSaver => {
      let EXCEL_TYPE =
        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8";
      let EXCEL_EXTENSION = ".xlsx";
      const data: Blob = new Blob([buffer], {
        type: EXCEL_TYPE
      });
      FileSaver.saveAs(
        data,
        fileName + "_historialCompras_" + new Date().getTime() + EXCEL_EXTENSION
      );
    });
  }

  getHistorialExcel() {
    let historial = [];
    this.comprasList.map(compra => {
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
    });
    return historial;
  }

  openEstadistica() {
    this.estadistica = true;
  }

  metodoEstadistica() {
    this.comprasList.map(prod => {
      var mes = new Date(prod.fechaCreacion).getMonth();
      this.ventas[mes].venta += 1;
    });
    // console.log(this.dist.map(a => a.mes));
    // console.log(this.dist.map(a => a.venta));
    this.data = {
      labels: this.ventas.map(a => a.mes),
      datasets: [
        {
          label: "2019",
          data: this.ventas.map(a => a.venta),
          fill: false,
          borderColor: "#4bc0c0",
          lineTension: 0
        }
      ]
    };

    this.options = {
      legend: { position: "bottom" },
      scales: {
        yAxes: [
          {
            scaleLabel: {
              display: true,
              labelString: "Cantidad de productos"
            },
            ticks: { stepSize: 1 }
          }
        ]
      },
      animation: { easing: "easeInQuad" },
      layout: {
        padding: {
          left: 20,
          right: 50,
          top: 20,
          bottom: 0
        }
      }
    };
  }

  ngOnInit() {}
}
