import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http'; 

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material';
import { MatCheckboxModule } from '@angular/material';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { PantallaProductoComponent } from './components/pantalla-producto/pantalla-producto.component';
import { PantallaInicioComponent } from './components/pantalla-inicio/pantalla-inicio.component';
import { MatDividerModule } from '@angular/material/divider';
import { ProductoPrincipalSliderComponent } from './components/producto-principal-slider/producto-principal-slider.component';
import { ProductoPrincipalPequenoComponent } from './components/producto-principal-pequeno/producto-principal-pequeno.component';

@NgModule({
  declarations: [
    AppComponent,
    PantallaProductoComponent,
    PantallaInicioComponent,
    ProductoPrincipalSliderComponent,
    ProductoPrincipalPequenoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule.forRoot(),
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule, 
    MatCheckboxModule,
    MatFormFieldModule,
    MatSelectModule,
    MatInputModule,
    MatMenuModule,
    MatIconModule,
    MatListModule,
    MatDividerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
