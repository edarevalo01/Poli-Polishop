import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { RouteReuseStrategy } from "@angular/router";
import { HttpClientModule } from "@angular/common/http";
import { IonicStorageModule } from "@ionic/storage";
import { FormsModule } from "@angular/forms";

import { IonicModule, IonicRouteStrategy } from "@ionic/angular";
import { SplashScreen } from "@ionic-native/splash-screen/ngx";
import { StatusBar } from "@ionic-native/status-bar/ngx";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { ModalPage } from "./modal/modal.page";
import { LinksPage } from "./links/links.page";
import { CompraPage } from "./compra/compra.page";
import { BuscarPage } from "./buscar/buscar.page";
import { SafePipe } from "./pipes/safe.pipe";

@NgModule({
  declarations: [AppComponent, ModalPage, LinksPage, BuscarPage, CompraPage, SafePipe],
  entryComponents: [ModalPage, LinksPage, CompraPage, BuscarPage],
  imports: [BrowserModule, HttpClientModule, IonicModule.forRoot(), AppRoutingModule, FormsModule, IonicStorageModule.forRoot()],
  providers: [StatusBar, SplashScreen, { provide: RouteReuseStrategy, useClass: IonicRouteStrategy }],
  bootstrap: [AppComponent]
})
export class AppModule {}
