import { Injectable } from '@angular/core';
import {FicheEmargement} from "./fiche-emargement.model";
import {AuthService} from "../../auth/auth.service";

@Injectable({
  providedIn: 'root'
})
export class FicheEmargementService {
   idGen:number = 2;

  private listeFicheEmargement: FicheEmargement[] = [
    {
      id:'1',
      nomCours:'WebService',
      imageUrl:
        'https://thumbs.dreamstime.com/b/vector-global-web-service-icon-isolated-black-flat-design-concept-163719580.jpg',
      listeEleves:['Jonathan','Louis','Clement','Mickael'],
      dateCours: new Date()
    },
    {
      id:'2',
      nomCours:'TestEtQualite',
      imageUrl:
        'https://img2.freepng.fr/20180404/lwe/kisspng-computer-icons-software-testing-quiz-5ac50f1e2e6aa2.8158265315228639021901.jpg',
      listeEleves:['Jonathan','Louis','Clement','Mickael'],
      dateCours: new Date()
    }
  ]

  constructor(private authService: AuthService) { }

  getAllFiches() {
    return [...this.listeFicheEmargement]
  }

  addFiche(nomCours:string, dateCours:Date){
    const newFiche = new FicheEmargement();
    this.idGen++;
    newFiche.id = this.idGen.toString();
    newFiche.nomCours = nomCours;
    newFiche.dateCours = dateCours;
    newFiche.imageUrl = 'https://picsum.photos/200'
    newFiche.listeEleves = ['']
    this.listeFicheEmargement.push(newFiche);
    return newFiche;
  }


  // ... = décomposition , il clone l'objet dans son integralité puis extrait toutes les propriété et les ajoute à un nouvel objet
  getFiche(ficheId:string){
   return { ...this.listeFicheEmargement.find(ficheEmargement => ficheEmargement.id === ficheId)};
  }

  deleteFiche(ficheId:string){
    this.listeFicheEmargement = this.listeFicheEmargement.filter(ficheEmargement => {
      return ficheEmargement.id !== ficheId;
    });
  }
}
