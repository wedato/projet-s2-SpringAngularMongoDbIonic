import { Component, OnInit } from '@angular/core';
import {FicheEmargement} from "../fiche-emargement.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import {FicheEmargementService} from "../fiche-emargement.service";
import {NavController} from "@ionic/angular";

@Component({
  selector: 'app-fiche-edit',
  templateUrl: './fiche-edit.page.html',
  styleUrls: ['./fiche-edit.page.scss'],
})
export class FicheEditPage implements OnInit {

  fiche: FicheEmargement;
  form: FormGroup;
  constructor(private route: ActivatedRoute,
              private ficheEmargementService: FicheEmargementService,
              private navCtr: NavController) {
    this.route.paramMap.subscribe(paramMap => {
      if (!paramMap.has('ficheId')){
        this.navCtr.navigateBack('/home/tabs/liste-fiches');
        return;
      }
      this.fiche = this.ficheEmargementService.getFiche(paramMap.get('ficheId'));
      this.form = new FormGroup({
        nomCours: new FormControl(this.fiche.nomCours, {
          updateOn: 'blur',
          validators: [Validators.required]
        }),
        dateCours: new FormControl(null, {
          updateOn: 'blur',
          validators: [Validators.required]
        })
      })
    });

  }

  ngOnInit() {
  }

  onUpdateFiche() {
    if (!this.form.valid){
      return;
    }
    console.log(this.form)
  }
}
