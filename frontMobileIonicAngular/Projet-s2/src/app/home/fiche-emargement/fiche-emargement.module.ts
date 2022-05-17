import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

const routes: Routes = [
  {
    path: '',
    component:FicheEmargementPage
  }
]


import { FicheEmargementPage } from './fiche-emargement.page';
import {RouterModule, Routes} from "@angular/router";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes)
  ],
  declarations: [FicheEmargementPage]
})
export class FicheEmargementPageModule {}
