import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { FicheEmargementPageRoutingModule } from './fiche-emargement-routing.module';

import { FicheEmargementPage } from './fiche-emargement.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    FicheEmargementPageRoutingModule
  ],
  declarations: [FicheEmargementPage]
})
export class FicheEmargementPageModule {}
