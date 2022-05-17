import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';



import {RouterModule, Routes} from "@angular/router";
import {FicheScanPage} from "./fiche-scan.page";

const routes : Routes = [
  {
    path:'',
    component: FicheScanPage

  }
]

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes)
  ],
 declarations: [FicheScanPage]
})
export class FicheScanPageModule {}
