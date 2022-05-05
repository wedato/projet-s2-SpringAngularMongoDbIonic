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
import { HeaderComponent } from './components/header/header.component';
import { GroupeTpComponent } from './components/groupe-tp/groupe-tp.component';
import {AuthenticationService} from "./services/authentication.service";
import {UserService} from "./services/user.service";
import {AuthInterceptor} from "../interceptors/auth.interceptor";
import {AuthenticationGuard} from "./guard/authentication.guard";
import {NotificationModule} from "./notification.module";
import {NotificationService} from "./services/notification.service";
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { UserComponent } from './components/user/user.component';
import { ListeEmargementComponent } from './components/liste-emargement/liste-emargement.component';
import {FicheService} from "./services/fiche.service";



@NgModule({
  declarations: [
    AppComponent,
    AddEtudiantComponent,
    EtudiantDetailsComponent,
    EtudiantsListComponent,
    CoursListComponent,
    HeaderComponent,
    GroupeTpComponent,
    LoginComponent,
    RegisterComponent,
    UserComponent,
    ListeEmargementComponent,

  ],
    imports: [
        RouterModule,
        BrowserModule,
        HttpClientModule,
        FormsModule,
        AppRoutingModule,
        NotificationModule,

    ],
  providers: [NotificationService, AuthenticationGuard, AuthenticationService, UserService,UserComponent,FicheService,
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}], // on creer plusieurs instance dans l'injector , pour pouvoir repandre plusieurs mini instance dans les diff classes
  bootstrap: [AppComponent],
  exports : [RouterModule]
})
export class AppModule { }
