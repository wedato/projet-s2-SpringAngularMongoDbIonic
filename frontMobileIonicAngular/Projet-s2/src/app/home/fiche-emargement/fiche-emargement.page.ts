import {Component, OnDestroy, OnInit} from '@angular/core';
import {FicheEmargement} from "./fiche-emargement.model";
import {FicheEmargementService} from "./fiche-emargement.service";

@Component({
  selector: 'app-fiche-emargement',
  templateUrl: './fiche-emargement.page.html',
  styleUrls: ['./fiche-emargement.page.scss'],
})
export class FicheEmargementPage implements OnInit, OnDestroy{

  listeFicheEmargements: FicheEmargement[];

  // le private dans le constructeur permet un acces dans toute la classe
  constructor(private ficheEmargementService: FicheEmargementService) { }

  ngOnInit() {

  }

  ionViewWillEnter(){
    this.listeFicheEmargements = this.ficheEmargementService.getAllFiches();
  }

  ngOnDestroy(): void {

  }

}
