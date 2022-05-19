import { Component, OnInit } from '@angular/core';
import { GroupeTp } from 'src/app/models/groupe.model';
import { AuthenticationService } from 'src/app/services/authentication.service';
import {Subscription} from "rxjs";
import { GroupeTpService } from 'src/app/services/groupeTp.service';
import { User } from 'src/app/models/user';
import {NotificationService} from "../../services/notification.service";
import {NotificationType} from "../../enum/notification-type.enum";
import {NgForm} from "@angular/forms";
import * as e from 'express';

@Component({
  selector: 'groupe-tp',
  templateUrl: './groupe-tp.component.html',
  styleUrls: ['./groupe-tp.component.css']
})
export class GroupeTpComponent implements OnInit {

  public eleveGroupe : User[]
  
  public listGroupeTp : GroupeTp[]
  groupe: GroupeTp
  user : User
  private sub : Subscription[] = []
  public editGroupe = new GroupeTp()
  public currentGroupeTpName : String
  public refreshing: boolean;

  constructor(public authenticationService: AuthenticationService, public groupeTpService: GroupeTpService, private notificationService: NotificationService ) { }

  ngOnInit(): void {
    this.listGroupeTp;
    this.user = this.authenticationService.getUserFromLocalCache()
    this.getGroupeTp(true);
  }

  private sendNotification(notificationType: NotificationType, message: string): void {
    if (message) {
      this.notificationService.notify(notificationType,message);
    } else {
      this.notificationService.notify(notificationType, 'Une erreur est survenu, veuillez ressayez.')
    }
  }

  private clickButton(buttonId: string):void {
    document.getElementById(buttonId).click();
  }

  public onEditGroupe(editGroupe : GroupeTp): void{
    this.editGroupe = editGroupe;
    this.currentGroupeTpName = editGroupe.nomGroupe;
    this.clickButton('openGroupeEdit')

  }

  public getGroupeTp(showNotification: boolean): void{
    this.refreshing = true;
    this.sub.push(
      this.groupeTpService.getListGroupeTp().subscribe({
        next :(response) => {
          this.groupeTpService.addGroupeTpLocalCache(response);
          this.listGroupeTp = response;
          this.refreshing = false;
    },
        error: (errorResponse) => {
          this.sendNotification(NotificationType.ERROR, 'Une erreur est survenu, veuillez ressayez.')
          this.refreshing = false;
      }
        }
      )
    );
  }

  public onDeleteGroupeTp(nomGroupe: string) {
    this.sub.push(
      this.groupeTpService.deleteGroupe(nomGroupe).subscribe({
          next: (response) => {
            this.sendNotification(NotificationType.SUCCESS, `L'utilisateur a bien ete supprimé`);
            //refresh list
            this.getGroupeTp(true)
          },
          error: (errorResponse) => {
            this.sendNotification(NotificationType.ERROR, `Une erreur est survenu, veuillez ressayez`)
          }
        }
      )
    )
}

onAddNewGroupeTp(groupeForm: NgForm):void {
  const formData = this.groupeTpService.createGroupeFormData(groupeForm.value);
  this.sub.push(
    this.groupeTpService.addGroupeTp(formData).subscribe({
      next:(response) => {
        this.clickButton('new-groupe-close');
        this.getGroupeTp(true);
        groupeForm.reset();
        this.sendNotification(NotificationType.SUCCESS, `${response.nomGroupe} a bien été ajouté`)
      },
      error:(errorResponse) => {
        this.sendNotification(NotificationType.ERROR, `Une erreur est survenu, veuillez ressayez`)
      }
    })
  )
}

saveNewGroupe():void {
  this.clickButton('new-groupe-save')
}

}
