import {EtudiantsListComponent} from "./components/etudiant/etudiants-list/etudiants-list.component";
import {EtudiantDetailsComponent} from "./components/etudiant/etudiant-details/etudiant-details.component";
import {AddEtudiantComponent} from "./components/etudiant/add-etudiant/add-etudiant.component";
import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {CoursListComponent} from "./components/cours/cours-list/cours-list.component";


const routes: Routes = [
  {path: '' , redirectTo: 'etudiants', pathMatch:'full'},
  {path: 'etudiants' , component: EtudiantsListComponent},
  {path: 'etudiants/:id' , component: EtudiantDetailsComponent},
  {path: 'addEtudiant', component: AddEtudiantComponent},
  {path: 'cours', component: CoursListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
