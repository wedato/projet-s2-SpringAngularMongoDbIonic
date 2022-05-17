import { Injectable } from '@angular/core';
import {FicheEmargement} from "./fiche-emargement.model";

@Injectable({
  providedIn: 'root'
})
export class FicheEmargementService {

  private listeFicheEmargement: FicheEmargement[] = [
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

  getAllFiches() {
    return [...this.listeFicheEmargement]
  }
  getFiche(ficheId:string){
   return {
     ...this.listeFicheEmargement.find(ficheEmargement => {
       return ficheEmargement.id === ficheId;
     })
   };
  }

  deleteFiche(ficheId:string){
    this.listeFicheEmargement = this.listeFicheEmargement.filter(ficheEmargement => {
      return ficheEmargement.id !== ficheId;
    });
  }
}
