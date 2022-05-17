import { Component, OnInit } from '@angular/core';
import {FicheEmargement} from "./fiche-emargement.model";
import {FicheEmargementService} from "./fiche-emargement.service";

@Component({
  selector: 'app-fiche-emargement',
  templateUrl: './fiche-emargement.page.html',
  styleUrls: ['./fiche-emargement.page.scss'],
})
export class FicheEmargementPage implements OnInit {

  listeFicheEmargements: FicheEmargement[];

  // le private dans le constructeur permet un acces dans toute la classe
  constructor(private ficheEmargementService: FicheEmargementService) { }

  ngOnInit() {
    this.listeFicheEmargements = this.ficheEmargementService.getAllFiches();
    console.log(this.listeFicheEmargements)
  }

}
