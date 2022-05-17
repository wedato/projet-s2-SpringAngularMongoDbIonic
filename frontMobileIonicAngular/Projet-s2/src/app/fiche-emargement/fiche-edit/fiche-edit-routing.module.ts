import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { FicheEditPage } from './fiche-edit.page';

const routes: Routes = [
  {
    path: '',
    component: FicheEditPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class FicheEditPageRoutingModule {}
