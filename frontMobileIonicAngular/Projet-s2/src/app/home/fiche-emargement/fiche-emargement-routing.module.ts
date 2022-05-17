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
    loadChildren: () => import('./fiche-detail/fiche-detail.module').then(m => m.FicheDetailPageModule)
  },
  {
    path: 'fiche-add',
    loadChildren: () => import('./fiche-add/fiche-add.module').then(m => m.FicheAddPageModule)
  },
  {
    path: 'fiche-edit',
    loadChildren: () => import('./fiche-edit/fiche-edit.module').then(m => m.FicheEditPageModule)
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class FicheEmargementPageRoutingModule {}
