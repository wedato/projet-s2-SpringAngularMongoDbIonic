import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { FicheDetailPageRoutingModule } from './fiche-detail-routing.module';

import { FicheDetailPage } from './fiche-detail.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    FicheDetailPageRoutingModule
  ],
  declarations: [FicheDetailPage]
})
export class FicheDetailPageModule {}
