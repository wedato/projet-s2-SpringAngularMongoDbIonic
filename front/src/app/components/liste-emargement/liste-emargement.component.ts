import { Component, OnInit } from '@angular/core';
import {User} from "../../models/user";
import {UserComponent} from "../user/user.component";
import {AuthenticationService} from "../../services/authentication.service";
import {FicheService} from "../../services/fiche.service";
import {FicheEmargement} from "../../models/fiche-emargement";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-liste-emargement',
  templateUrl: './liste-emargement.component.html',
  styleUrls: ['./liste-emargement.component.css']
})
export class ListeEmargementComponent implements OnInit {

  public usersInscrit : User[];

  public listeFiche : FicheEmargement[];
  private subscriptions: Subscription[] = [];
  user: User
  premiereSignature:boolean;
  public selectedFiche: FicheEmargement;
  public nameOfSelectedFiche: string;

  constructor(  public authenticationService: AuthenticationService , public ficheService: FicheService) { }

  ngOnInit(): void {
    this.user = this.authenticationService.getUserFromLocalCache();
    this.getListeFiche();

  }

  getEtudiantInscrit(nomCours:String){

    this.ficheService.getUsersInscritCours(nomCours)
      .subscribe({
        next:(data) => {
          this.usersInscrit = data;


        },
        error: (e) => console.error(e)
      });
  }
  getListeFiche(){
    this.subscriptions.push(
    this.ficheService.getListeFiche()
      .subscribe({
        next:(data) => {
          this.ficheService.addFichesToLocalCache(data)
          this.listeFiche = data;


        },
        error: (e) => console.error(e)
      }));
  }

  public signerFiche():void{

    this.premiereSignature=true;
    this.subscriptions.push(
      this.ficheService.signerFiche(this.selectedFiche.id,this.user.username).subscribe(
        {
          next:(response) => {
            this.getListeFiche()
          },
          error: (errResponse) => {
            console.error(errResponse)
          }
        }
      )
    )

  }

  public onSelectFiche(selectedFiche: FicheEmargement): void {
    this.selectedFiche = selectedFiche;
    console.log(selectedFiche.nomCours)
    this.nameOfSelectedFiche = selectedFiche.nomCours;
    this.getEtudiantInscrit(this.nameOfSelectedFiche);
    this.clickButton('openListeEleves')
    console.log(this.usersInscrit)
  }

  private clickButton(buttonId: string):void {
    document.getElementById(buttonId).click();
  }

}
