import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { HttpClientModule } from "@angular/common/http";
import { OverlayModule } from "@angular/cdk/overlay";

import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { MatButtonModule } from "@angular/material";
import { MatCheckboxModule } from "@angular/material";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatDialogModule } from "@angular/material/dialog";
import { FormsModule } from "@angular/forms";
import { ReactiveFormsModule } from "@angular/forms";
import { MatSelectModule } from "@angular/material/select";
import { MatInputModule } from "@angular/material/input";
import { MatMenuModule } from "@angular/material/menu";
import { MatIconModule } from "@angular/material/icon";
import { MatListModule } from "@angular/material/list";
import { MatCardModule } from "@angular/material/card";
import { MatSnackBarModule } from "@angular/material/snack-bar";
import { MatExpansionModule } from "@angular/material/expansion";
import { MatTooltipModule } from "@angular/material/tooltip";
import { MatStepperModule } from "@angular/material/stepper";

import { ToastModule } from "primeng/toast";
import { MessagesModule } from "primeng/messages";
import { MessageModule } from "primeng/message";
import { TableModule } from "primeng/table";
import { ButtonModule } from "primeng/button";
import { DialogModule } from "primeng/dialog";
import { InputTextareaModule } from "primeng/inputtextarea";
import { KeyFilterModule } from "primeng/keyfilter";
import { InputMaskModule } from "primeng/inputmask";
import { ConfirmDialogModule } from "primeng/confirmdialog";
import { FileUploadModule } from "primeng/fileupload";
import { ListboxModule } from "primeng/listbox";
import { DropdownModule } from "primeng/dropdown";
import { InputTextModule } from "primeng/inputtext";

import { PantallaProductoComponent } from "./components/pantalla-producto/pantalla-producto.component";
import { PantallaInicioComponent } from "./components/pantalla-inicio/pantalla-inicio.component";
import { MatDividerModule } from "@angular/material/divider";
import { ProductoPrincipalSliderComponent } from "./components/producto-principal-slider/producto-principal-slider.component";
import { ProductoPrincipalPequenoComponent } from "./components/producto-principal-pequeno/producto-principal-pequeno.component";
import { ComentarioProductoComponent } from "./components/comentario-producto/comentario-producto.component";
import { PantallaVenderComponent } from "./components/pantalla-vender/pantalla-vender.component";
import { PantallaVendedorComponent } from "./components/pantalla-vendedor/pantalla-vendedor.component";
import { BusquedaComponent } from "./components/busqueda/busqueda.component";
import { AyudaComponent } from "./components/ayuda/ayuda.component";
import { PantallaCarritoComponent } from "./components/pantalla-carrito/pantalla-carrito.component";
import { HistorialComprasComponent } from "./components/historial-compras/historial-compras.component";

@NgModule({
  declarations: [
    AppComponent,
    PantallaProductoComponent,
    PantallaInicioComponent,
    ProductoPrincipalSliderComponent,
    ProductoPrincipalPequenoComponent,
    ComentarioProductoComponent,
    PantallaVenderComponent,
    PantallaVendedorComponent,
    BusquedaComponent,
    AyudaComponent,
    PantallaCarritoComponent,
    HistorialComprasComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
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
    MatDividerModule,
    MatCardModule,
    FormsModule,
    ReactiveFormsModule,
    MatSnackBarModule,
    MatExpansionModule,
    MatTooltipModule,
    MatStepperModule,
    ToastModule,
    MessagesModule,
    MessageModule,
    TableModule,
    ButtonModule,
    DialogModule,
    InputTextareaModule,
    KeyFilterModule,
    InputMaskModule,
    ConfirmDialogModule,
    FileUploadModule,
    ListboxModule,
    MatDialogModule,
    OverlayModule,
    InputTextModule,
    DropdownModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
