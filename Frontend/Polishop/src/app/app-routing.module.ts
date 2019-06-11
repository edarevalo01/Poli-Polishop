import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PantallaProductoComponent } from './components/pantalla-producto/pantalla-producto.component';
import { PantallaInicioComponent } from './components/pantalla-inicio/pantalla-inicio.component';
import { PantallaVenderComponent } from './components/pantalla-vender/pantalla-vender.component';
import { PantallaVendedorComponent } from './components/pantalla-vendedor/pantalla-vendedor.component';

const routes: Routes = [
  {path: '', redirectTo: '/inicio', pathMatch: 'full'},
  {path: 'inicio', component: PantallaInicioComponent},
  {path: 'p-producto', component: PantallaProductoComponent},
  {path: 'p-producto/:idProducto', component: PantallaProductoComponent},
  {path: 'p-vender', component: PantallaVenderComponent},
  {path: 'p-vendedor', component: PantallaVendedorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
