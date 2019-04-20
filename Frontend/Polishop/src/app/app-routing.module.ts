import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PantallaProductoComponent } from './components/pantalla-producto/pantalla-producto.component';
import { PantallaInicioComponent } from './components/pantalla-inicio/pantalla-inicio.component';

const routes: Routes = [
  {path: '', redirectTo: '/inicio', pathMatch: 'full'},
  {path: 'inicio', component: PantallaInicioComponent},
  {path: 'p-producto', component: PantallaProductoComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
