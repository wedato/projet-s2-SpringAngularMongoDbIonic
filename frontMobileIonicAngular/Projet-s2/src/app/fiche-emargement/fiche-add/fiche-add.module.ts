import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { FicheAddPageRoutingModule } from './fiche-add-routing.module';

import { FicheAddPage } from './fiche-add.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    FicheAddPageRoutingModule
  ],
  declarations: [FicheAddPage]
})
export class FicheAddPageModule {}
