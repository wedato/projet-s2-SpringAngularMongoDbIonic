import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
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
import {AuthenticationService} from "./services/authentication.service";
import {UserService} from "./services/user.service";
import {AuthInterceptor} from "../interceptors/auth.interceptor";



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
  providers: [AuthenticationService, UserService,
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}], // on creer plusieurs instance dans l'injector , pour pouvoir repandre plusieurs mini instance dans les diff classes
  bootstrap: [AppComponent],
  exports : [RouterModule]
})
export class AppModule { }
