import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';

import { IonicModule } from '@ionic/angular';

import { PantallaProductoPage } from './pantalla-producto.page';

const routes: Routes = [
  {
    path: '',
    component: PantallaProductoPage
  }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes)
  ],
  declarations: [PantallaProductoPage]
})
export class PantallaProductoPageModule {}
