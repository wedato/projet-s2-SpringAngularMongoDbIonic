import { Component, OnInit } from '@angular/core';
import {ModalController} from "@ionic/angular";
import {FormControl, FormGroup, Validators} from "@angular/forms";


@Component({
  selector: 'app-fiche-add',
  templateUrl: './fiche-add.page.html',
  styleUrls: ['./fiche-add.page.scss'],
})
export class FicheAddPage implements OnInit {


  form: FormGroup
  textToCode: string;
  myQrCode: string = null;

  constructor(
    private modalCtrl: ModalController
  ) { }

  ngOnInit() {
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
    console.log(this.form);
  }
}
