import { Comprador } from "./comprador";
import { ProductoCarrito } from "./producto-carrito";
import { Producto } from "./producto";
import { GeneralService } from "../Services/general.service";
import { Storage } from "@ionic/storage";

export class ObservablePolishop {
  static instance: ObservablePolishop;
  private observers: IObserverPolishop[];

  // Flags
  public settedUsuario: boolean;
  public settedProductosCarrito: boolean;
  public settedProductosPoli: boolean;
  public settedProductosComu: boolean;
  public settedAllProductos: boolean;

  // Data
  public usuario: Comprador;
  public productosCarrito: ProductoCarrito[] = [];
  public productosPoli: Producto[] = [];
  public productosComu: Producto[] = [];
  public allProductos: Producto[] = [];

  /**
   * Constructor
   * @param service
   */
  private constructor(public service: GeneralService) {
    this.observers = [];
    this.getProductosPoli();
    this.getProductosComu();
    this.getAllProductos();
  }

  public refrescarPeticiones() {
    this.settedUsuario = false;
    this.settedProductosCarrito = false;
    this.settedProductosPoli = false;
    this.settedProductosComu = false;
    this.settedAllProductos = false;

    this.usuario = new Comprador();
    this.productosCarrito = [];
    this.productosPoli = [];
    this.productosComu = [];
    this.allProductos = [];
    this.getProductosPoli();
    this.getProductosComu();
    this.getAllProductos();
  }

  /**
   * Obtener instancia unica (Singleton).
   * @param service
   */
  public static getInstance(service: GeneralService): ObservablePolishop {
    if (ObservablePolishop.instance == undefined) {
      ObservablePolishop.instance = new ObservablePolishop(service);
    }
    return ObservablePolishop.instance;
  }

  /**
   * Agregar Observer.
   * @param observer
   */
  public addObserver(observer: IObserverPolishop) {
    this.observers.push(observer);
    this.informarObservers();
  }

  /**
   * Informar Observers.
   */
  private informarObservers() {
    this.observers.forEach(function(observer) {
      observer.refrescarDatos();
    });
  }

  private getUsuario() {
    var idUsuario = this.service.getIdUsuario();
    if (!this.settedUsuario) {
      this.service.getInfoCompradorById(+idUsuario).subscribe(
        response => {
          this.settedUsuario = true;
          this.usuario = response;
        },
        error => {},
        () => {
          this.informarObservers();
        }
      );
    } else {
      this.informarObservers();
    }
  }

  private getProductosCarrito() {
    var idUsuario = this.service.getIdUsuario();
    if (!this.settedProductosCarrito) {
      this.service.getProductosCarrito(+idUsuario).subscribe(
        response => {
          this.settedProductosCarrito = true;
          this.productosCarrito = response;
        },
        error => {},
        () => {
          this.informarObservers();
        }
      );
    } else {
      this.informarObservers();
    }
  }

  private getProductosPoli() {
    if (!this.settedProductosPoli) {
      this.service.getProductosByDependencia("Poli").subscribe(
        response => {
          this.settedProductosPoli = true;
          this.productosPoli = response;
        },
        error => {},
        () => {
          this.informarObservers();
        }
      );
    } else {
      this.informarObservers();
    }
  }

  private getProductosComu() {
    if (!this.settedProductosComu) {
      this.service.getProductosByDependencia("Comunidad").subscribe(
        response => {
          this.settedProductosComu = true;
          this.productosComu = response;
        },
        error => {},
        () => {
          this.informarObservers();
        }
      );
    } else {
      this.informarObservers();
    }
  }

  private getAllProductos() {
    if (!this.settedAllProductos) {
      this.service.getAllProductos().subscribe(
        response => {
          this.settedAllProductos = true;
          this.allProductos = response;
        },
        error => {},
        () => {
          this.informarObservers();
        }
      );
    } else {
      this.informarObservers();
    }
  }
}

export interface IObserverPolishop {
  refrescarDatos();
}
