import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('./tabs/tabs.module').then(m => m.TabsPageModule)
  },
  { path: 'pantalla-producto', loadChildren: './pantalla-producto/pantalla-producto.module#PantallaProductoPageModule' },
  { path: 'pantalla-producto/:idProducto', loadChildren: './pantalla-producto/pantalla-producto.module#PantallaProductoPageModule' },  { path: 'modal', loadChildren: './modal/modal.module#ModalPageModule' },
  { path: 'links', loadChildren: './links/links.module#LinksPageModule' },
  { path: 'compra', loadChildren: './compra/compra.module#CompraPageModule' },
  { path: 'buscar', loadChildren: './buscar/buscar.module#BuscarPageModule' }

  //{ path: 'tab4', loadChildren: './tab4/tab4.module#Tab4PageModule' }
];
@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
