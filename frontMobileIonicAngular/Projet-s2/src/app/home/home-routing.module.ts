import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePage } from './home.page';

const routes: Routes = [
  {
    path: 'tabs',
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
          },
          {
            path:'edit/:ficheId',
            loadChildren:() => import('./fiche-emargement/fiche-edit/fiche-edit.module').then(m => m.FicheEditPageModule)
          }

        ]
      },
      {
        path:'add-fiche',
        loadChildren:() => import('./fiche-emargement/fiche-add/fiche-add.module').then(m => m.FicheAddPageModule)
      },
      {
        path:'scan-fiche',
        loadChildren: () => import('./fiche-emargement/fiche-scan/fiche-scan.module').then(m => m.FicheScanPageModule)
      },
      {
        path:'',
        redirectTo:'/home/tabs/liste-fiche',
        pathMatch:'full'
      }

    ]

  },
  {
    path:'',
    redirectTo:'/home/tabs/liste-fiche',
    pathMatch:'full'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomePageRoutingModule {}

