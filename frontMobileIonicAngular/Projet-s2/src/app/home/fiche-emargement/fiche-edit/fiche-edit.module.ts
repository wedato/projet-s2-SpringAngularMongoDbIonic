import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { IonicModule } from '@ionic/angular';




import {RouterModule, Routes} from "@angular/router";
import {FicheEditPage} from "./fiche-edit.page";


const routes: Routes = [
  {
    path: '',
    component: FicheEditPage
  }
];
@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes),
    ReactiveFormsModule
  ],
  declarations: [FicheEditPage]
})
export class FicheEditPageModule {}
