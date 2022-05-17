import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { FicheEditPageRoutingModule } from './fiche-edit-routing.module';

import { FicheEditPage } from './fiche-edit.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    FicheEditPageRoutingModule
  ],
  declarations: [FicheEditPage]
})
export class FicheEditPageModule {}
