import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'home',
    loadChildren: () => import('./home/home.module').then( m => m.HomePageModule)
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'fiche-emargement',
    children: [
        {
          path:'',
          loadChildren: () => import('./fiche-emargement/fiche-emargement.module').then( m => m.FicheEmargementPageModule),

        },
        {
          path: ':ficheId',
          loadChildren: () => import('./fiche-emargement/fiche-detail/fiche-detail.module').then( m => m.FicheDetailPageModule)
        }
    ]
  },

];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
