import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePage } from './home.page';

const routes: Routes = [
  {
    path: '',
    component: HomePage,
    children: [
      {
        path: 'liste-fiche',
        children:[
          {
           path:"",
           loadChildren: () => import('./fiche-emargement/fiche-emargement.module').then(m => m.FicheEmargementPageModule)
          },
          {
            path:':ficheId',
            loadChildren:() => import('./fiche-emargement/fiche-detail/fiche-detail.module').then(m => m.FicheDetailPageModule)
          }
        ]
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomePageRoutingModule {}
