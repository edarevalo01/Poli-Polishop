import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TabsPage } from './tabs.page';

const routes: Routes = [
  {
    path: 'tabs',
    component: TabsPage,
    children: [
      {
        path: 'inicio',
        children: [
          {
            path: '',
            loadChildren: () =>
              import('../inicio/inicio.module').then(m => m.InicioPageModule)
          }
        ]
      },
      {
        path: 'cuenta',
        children: [
          {
            path: '',
            loadChildren: () =>
              import('../cuenta/cuenta.module').then(m => m.CuentaPageModule)
          }
        ]
      },
      {
        path: 'carrito',
        children: [
          {
            path: '',
            loadChildren: () =>
              import('../carrito/carrito.module').then(m => m.CarritoPageModule)
          }
        ]
      },
      {
        path: 'ayuda',
        children: [
          {
            path: '',
            loadChildren: () =>
              import('../ayuda/ayuda.module').then(m => m.AyudaPageModule)
          }
        ]
      },
      {
        path: '',
        redirectTo: '/tabs/inicio',
        pathMatch: 'full'
      }
    ]
  },
  {
    path: '',
    redirectTo: '/tabs/inicio',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TabsPageRoutingModule {}
