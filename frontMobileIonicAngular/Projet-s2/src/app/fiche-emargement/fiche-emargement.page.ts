import { Component, OnInit } from '@angular/core';
import {FicheEmargement} from "./fiche-emargement.model";

@Component({
  selector: 'app-fiche-emargement',
  templateUrl: './fiche-emargement.page.html',
  styleUrls: ['./fiche-emargement.page.scss'],
})
export class FicheEmargementPage implements OnInit {

  ficheEmargements: FicheEmargement[] = [
    {
      id:'1',
      nomCours:'WebService',
      imageUrl:
      'https://thumbs.dreamstime.com/b/vector-global-web-service-icon-isolated-black-flat-design-concept-163719580.jpg',
      listeEleves:['Jonathan','Louis','Clement','Mickael']
    },
    {
      id:'2',
      nomCours:'TestEtQualite',
      imageUrl:
        'https://img2.freepng.fr/20180404/lwe/kisspng-computer-icons-software-testing-quiz-5ac50f1e2e6aa2.8158265315228639021901.jpg',
      listeEleves:['Jonathan','Louis','Clement','Mickael']
    }
  ]

  constructor() { }

  ngOnInit() {
  }

}
