import { Component, OnInit } from '@angular/core';
import {User} from "../../models/user";
import {UserComponent} from "../user/user.component";
import {AuthenticationService} from "../../services/authentication.service";
import {FicheService} from "../../services/fiche.service";

@Component({
  selector: 'app-liste-emargement',
  templateUrl: './liste-emargement.component.html',
  styleUrls: ['./liste-emargement.component.css']
})
export class ListeEmargementComponent implements OnInit {

  public usersInscrit : User[];
  user: User

  constructor(  public authenticationService: AuthenticationService , public ficheService: FicheService) { }

  ngOnInit(): void {
    this.user = this.authenticationService.getUserFromLocalCache();
  }

  getEtudiantInscrit(nomCours:String){
    this.ficheService.getUsersInscritCours(nomCours)
      .subscribe({
        next:(data) => {
          this.usersInscrit = data;
          console.log(data);
          console.log(this.user)
        },
        error: (e) => console.error(e)
      });
  }

}
