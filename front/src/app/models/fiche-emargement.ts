import {User} from "./user";

export class FicheEmargement {
  public id: string;
  public nomCours: string;
  // public joinDate: Date;
  public listeEtudiantSigne: User[];


  constructor() {
    this.nomCours = '';
    this.listeEtudiantSigne = [];

  }

}
