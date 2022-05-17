import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FicheEmargementService} from "../fiche-emargement.service";
import {FicheEmargement} from "../fiche-emargement.model";
import {AlertController} from "@ionic/angular";

@Component({
  selector: 'app-fiche-detail',
  templateUrl: './fiche-detail.page.html',
  styleUrls: ['./fiche-detail.page.scss'],
})
export class FicheDetailPage implements OnInit {
  loadedFiche: FicheEmargement

  constructor(
    private activatedRoute: ActivatedRoute,
    private ficheEmargementService: FicheEmargementService,
    private router: Router,
    private alertCtrl: AlertController
    ) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(paramMap => {
      if (!paramMap.has('ficheId')) {
        return;
      }
      const ficheId = paramMap.get('ficheId');
      this.loadedFiche = this.ficheEmargementService.getFiche(ficheId);
    })
  }

  onDeleteFiche() {
    this.alertCtrl.create({
      header:"Confirmation",
      message:"Voulez vous vraimment supprimer la fiche",
      buttons: [
        {
        text:'Annuler',
        role:'annuler'
      },
        {
          text:'Supprimer',
          handler: () => {
            this.ficheEmargementService.deleteFiche(this.loadedFiche.id);
            this.router.navigate(['home/liste-fiche'])
          }
        }
      ]
    })
      .then(alertEl => {
        alertEl.present();
      });

  }


}
