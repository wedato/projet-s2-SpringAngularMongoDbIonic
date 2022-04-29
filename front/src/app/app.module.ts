import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule} from "@angular/common/http";
import { AppComponent } from './app.component';
import {RouterModule, Routes} from "@angular/router";
import {AppRoutingModule} from "./app-routing.module";

import {FormsModule} from "@angular/forms";

import { AddEtudiantComponent } from './components/etudiant/add-etudiant/add-etudiant.component';
import { EtudiantDetailsComponent } from './components/etudiant/etudiant-details/etudiant-details.component';
import { EtudiantsListComponent } from './components/etudiant/etudiants-list/etudiants-list.component';

import { CoursListComponent } from './components/cours/cours-list/cours-list.component';
import { HeaderComponent } from './components/header/header/header.component';
import { GroupeTpComponent } from './groupe-tp/groupe-tp.component';



@NgModule({
  declarations: [
    AppComponent,
    AddEtudiantComponent,
    EtudiantDetailsComponent,
    EtudiantsListComponent,
    CoursListComponent,
    HeaderComponent,
    GroupeTpComponent,

  ],
    imports: [
        RouterModule,
        BrowserModule,
        HttpClientModule,
        FormsModule,
        AppRoutingModule
    ],
  providers: [],
  bootstrap: [AppComponent],
  exports : [RouterModule]
})
export class AppModule { }
