import { Component, OnInit } from '@angular/core';
import {ModalController} from "@ionic/angular";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {FicheEmargementService} from "../fiche-emargement.service";


@Component({
  selector: 'app-fiche-add',
  templateUrl: './fiche-add.page.html',
  styleUrls: ['./fiche-add.page.scss'],
})
export class FicheAddPage implements OnInit {


  form: FormGroup
  textToCode: string;
  myQrCode: string = null;
  ficheIsCreated: boolean;

  constructor(
    private modalCtrl: ModalController,
    private ficheService: FicheEmargementService
  ) { }

  ngOnInit() {
    this.ficheIsCreated = false;
    this.form = new FormGroup({
      nomCours: new FormControl(null, {
        updateOn: 'blur',
        validators: [Validators.required]
      }),
      dateCours: new FormControl(null, {
        updateOn: 'blur',
        validators: [Validators.required]
      })
    })
  }

  createQRCode() {
    this.myQrCode = this.textToCode;
    this.textToCode = "";
  }


  onAddFiche() {
    if (!this.form.valid){
      return;
    }

    const newFiche = (this.ficheService.addFiche(this.form.value.nomCours, this.form.value.dateCours))
    this.ficheIsCreated = true;
    this.myQrCode = newFiche.id;
  }
}
