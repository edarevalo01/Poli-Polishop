import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { ProductoService } from "src/app/services/producto.service";
import { Producto } from "src/app/model/Producto";
import { CategoriaService } from "src/app/services/categoria.service";
import { Categoria } from "src/app/model/Categoria";

@Component({
  selector: "app-busqueda",
  templateUrl: "./busqueda.component.html",
  styleUrls: ["./busqueda.component.css"]
})
export class BusquedaComponent implements OnInit {
  public textRoute: string = "texto temporal :v";
  public productos: Producto[] = [];
  public categorias: Array<Categoria>;

  constructor(
    private activeRoute: ActivatedRoute,
    private router: Router,
    private productoService: ProductoService,
    private categoriaService: CategoriaService
  ) {
    activeRoute.queryParams.subscribe(retorno => {
      this.textRoute = retorno["object"];
      this.busquedaProducto();
    });
    this.loadCategorias();
  }

  busquedaProducto() {
    this.productoService.busquedaProducto(this.textRoute).subscribe(
      productosObs => {
        this.productos = productosObs;
      },
      error => {},
      () => {}
    );
  }

  loadCategorias() {
    this.categoriaService.getAllCategorias().subscribe(
      categoriasObs => {
        this.categorias = categoriasObs;
      },
      error => {},
      () => {}
    );
  }

  /**
   * Redirigir a la pantalla de busqueda con la categoría asignada
   * @param categoria
   */
  searchCategoria(categoria: Categoria) {
    this.router.navigate(["search"], {
      queryParams: {
        object: categoria.nombre,
        type: "categoria"
      },
      skipLocationChange: false
    });
  }

  /**
   * Redirigir a la pantalla producto con el producto seleccionado
   * @param producto
   */
  btnVerProducto(producto: Producto) {
    this.router.navigate(["/p-producto/", producto.id], {
      queryParams: {
        idProd: producto.id,
        nameProd: producto.nombre
      },
      skipLocationChange: false
    });
    this.delay(5);
  }

  ngOnInit() {}

  async delay(ms: number) {
    await new Promise(resolve => setTimeout(() => resolve(), ms)).then(() => location.reload());
  }
}
