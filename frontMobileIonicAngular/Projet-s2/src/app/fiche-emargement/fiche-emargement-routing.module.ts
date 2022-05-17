import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { FicheEmargementPage } from './fiche-emargement.page';

const routes: Routes = [
  {
    path: '',
    component: FicheEmargementPage
  },
  {
    path: 'fiche-detail',
    loadChildren: () => import('./fiche-detail/fiche-detail.module').then( m => m.FicheDetailPageModule)
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class FicheEmargementPageRoutingModule {}
