import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { IonicModule } from '@ionic/angular';


import { FicheAddPage } from './fiche-add.page';
import {RouterModule, Routes} from "@angular/router";
import {QRCodeModule} from "angularx-qrcode";


const routes: Routes = [
  {
    path: '',
    component:FicheAddPage
  }
]

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    IonicModule,
    RouterModule.forChild(routes),
    QRCodeModule
  ],
  declarations: [FicheAddPage]
})
export class FicheAddPageModule {}
