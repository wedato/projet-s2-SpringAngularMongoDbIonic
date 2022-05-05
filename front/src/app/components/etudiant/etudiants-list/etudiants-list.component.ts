import { Component, OnInit } from '@angular/core';
import {Etudiant} from "../../../models/etudiant.model";
import {EtudiantService} from "../../../services/etudiant.service";
import {User} from "../../../models/user";
import {FicheEmargement} from "../../../models/fiche-emargement";

@Component({
  selector: 'app-etudiants-list',
  templateUrl: './etudiants-list.component.html',
  styleUrls: ['./etudiants-list.component.css']
})
export class EtudiantsListComponent implements OnInit {


  etudiants?: Etudiant[];
  currentEtudiant: Etudiant = {};
  currentIndex = -1;
  numEtudiant='';
  constructor(private etudiantService: EtudiantService) { }
  ngOnInit(): void {
    this.recupererEtudiants();
  }
  recupererEtudiants():void {
    this.etudiantService.getAll()
      .subscribe({
        next: (data) => {
          this.etudiants = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  refreshList(): void {
    this.recupererEtudiants();
    this.currentEtudiant = {};
    this.currentIndex = -1;
  }
  setActiveEtudiant(etudiant: Etudiant, index: number):void {
    this.currentEtudiant = etudiant;
    this.currentIndex = index;
  }
  removeAllEtudiants(): void {
    this.etudiantService.deleteAll()
      .subscribe({
        next: (res) => {
          console.log(res);
          this.refreshList();
        },
        error: (e) => console.error(e)
      });
  }



  searchEtudiant(): void {
    this.currentEtudiant = {};
    this.currentIndex = -1;
    this.etudiantService.findByNumEtu(this.numEtudiant)
      .subscribe({
        next: (data) => {
          this.currentEtudiant = data;
          console.log(data)
        },
        error: (e) => console.error(e)
      });
  }



}
