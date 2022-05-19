import {User} from "./user";

export class GroupeTp {
  public id: string;
  public nomGroupe: string;
  // public joinDate: Date;
  public listeEtudiantInscrit: User[];



  constructor() {
    this.nomGroupe = '';
    this.listeEtudiantInscrit = [];

  }

}