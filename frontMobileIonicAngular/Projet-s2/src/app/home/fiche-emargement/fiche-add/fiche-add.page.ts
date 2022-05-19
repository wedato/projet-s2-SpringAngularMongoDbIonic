import { Component, OnInit } from '@angular/core';
import {ModalController} from "@ionic/angular";


@Component({
  selector: 'app-fiche-add',
  templateUrl: './fiche-add.page.html',
  styleUrls: ['./fiche-add.page.scss'],
})
export class FicheAddPage implements OnInit {


  textToCode: string;
  myQrCode: string = null;

  constructor(
    private modalCtrl: ModalController
  ) { }

  ngOnInit() {
  }

  createQRCode() {
    this.myQrCode = this.textToCode;
    this.textToCode = "";
  }


}
