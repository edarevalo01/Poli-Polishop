import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PantallaProductoComponent } from './components/pantalla-producto/pantalla-producto.component';
import { PantallaInicioComponent } from './components/pantalla-inicio/pantalla-inicio.component';
import { PantallaVenderComponent } from './components/pantalla-vender/pantalla-vender.component';
import { PantallaVendedorComponent } from './components/pantalla-vendedor/pantalla-vendedor.component';
import { BusquedaComponent } from './components/busqueda/busqueda.component';
import { AyudaComponent } from './components/ayuda/ayuda.component';
import { PantallaCarritoComponent } from './components/pantalla-carrito/pantalla-carrito.component';
import { HistorialComprasComponent } from './components/historial-compras/historial-compras.component';

const routes: Routes = [
  {path: '', redirectTo: '/inicio', pathMatch: 'full'},
  {path: 'inicio', component: PantallaInicioComponent},
  {path: 'p-producto', component: PantallaProductoComponent},
  {path: 'p-producto/:idProducto', component: PantallaProductoComponent},
  {path: 'p-vender', component: PantallaVenderComponent},
  {path: 'p-vendedor', component: PantallaVendedorComponent},
  {path: 'search', component: BusquedaComponent},
  {path: 'help-polishop', component: AyudaComponent},
  {path: 'shopping-cart', component: PantallaCarritoComponent},
  {path: 'historial-compras-vendedor', component: HistorialComprasComponent},
];

@NgModule({
  //imports: [RouterModule.forRoot(routes, { useHash: true })], //Produccion
  imports: [RouterModule.forRoot(routes)], //Local
  exports: [RouterModule]
})
export class AppRoutingModule { }
