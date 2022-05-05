import {User} from "./user";

export class FicheEmargement {
  public id: string;
  public nomCours: string;
  // public joinDate: Date;
  public listeEtudiantInscrit: User[];



  constructor() {
    this.nomCours = '';
    this.listeEtudiantInscrit = [];

  }

}
