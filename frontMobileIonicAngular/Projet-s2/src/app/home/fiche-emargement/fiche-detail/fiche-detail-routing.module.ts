import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { FicheDetailPage } from './fiche-detail.page';

const routes: Routes = [
  {
    path: '',
    component: FicheDetailPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class FicheDetailPageRoutingModule {}
