import {Component, OnInit} from '@angular/core';
import {BehaviorSubject, Subscription} from "rxjs";
import {User} from "../../models/user";
import {UserService} from "../../services/user.service";
import {NotificationService} from "../../services/notification.service";
import {NotificationType} from "../../enum/notification-type.enum";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  // par defaut le titre est utilisateurs, le titre change quand on clique
  private titleSubject = new BehaviorSubject<string>('Utilisateurs');

  public titleAction$ = this.titleSubject.asObservable();
  public users: User[];
  //le spiner
  public refreshing: boolean;
  private subscriptions: Subscription[] = [];
  public selectedUser: User;

  constructor(private userService: UserService, private notificationService: NotificationService) { }

  // change le titre chaque fois qu'on clique sur un nouveu tab
  public changeTitle(title: string): void {
    this.titleSubject.next(title)
  }
  ngOnInit(): void {
    this.getUsers(true)
  }

  public getUsers(showNotification: boolean): void{
    this.refreshing = true;
    this.subscriptions.push(
      this.userService.getUsers().subscribe({
        next :(response) => {
          this.userService.addUsersToLocalCache(response);
          this.users = response;
          this.refreshing = false;
          if (showNotification) {
            this.sendNotification(NotificationType.SUCCESS, `${response.length} utilisateur(s) load avec succès.`);
          }
    },
        error: (errorResponse) => {
          this.sendNotification(NotificationType.ERROR, 'Une erreur est survenu, veuillez ressayez.')
          this.refreshing = false;
      }
        }
      )
    );
  }

  private sendNotification(notificationType: NotificationType, message: string): void {
    if (message) {
      this.notificationService.notify(notificationType,message);
    } else {
      this.notificationService.notify(notificationType, 'Une erreur est survenu, veuillez ressayez.')
    }
  }

  public onSelectUser(selectedUser: User): void {
    this.selectedUser = selectedUser;
    document.getElementById('openUserInfo').click()
  }


}
