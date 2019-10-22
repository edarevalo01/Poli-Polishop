import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { Routes, RouterModule } from "@angular/router";

import { IonicModule } from "@ionic/angular";
import { SafePipe } from "../pipes/safe.pipe";
import { LinksPage } from "./links.page";

const routes: Routes = [
  {
    path: "",
    component: LinksPage
  }
];

@NgModule({
  imports: [CommonModule, FormsModule, IonicModule, RouterModule.forChild(routes)],
  declarations: [LinksPage, SafePipe]
})
export class LinksPageModule {}
