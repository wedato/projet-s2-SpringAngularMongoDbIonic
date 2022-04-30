import {Component, OnDestroy, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/authentication.service";
import {NotificationService} from "../../services/notification.service";
import {User} from "../../models/user";
import {Subscription} from "rxjs";
import {NotificationType} from "../../enum/notification-type.enum";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit, OnDestroy {
  public showLoading: boolean;
  private subscriptions: Subscription[] = [];


  constructor(private router: Router, private authenticationService: AuthenticationService,
              private notificationService: NotificationService) { }

  //redirige si l'user est deja login vers la mainpage
  ngOnInit(): void {
    if (this.authenticationService.isUserLoggedIn()) {
      this.router.navigateByUrl('/user/management');
    }
  }
  public onRegister(user: User): void{
    console.log(user);
    this.showLoading = true;
    this.subscriptions.push(
      this.authenticationService.register(user).subscribe({
        next:(user) => {
          this.showLoading = false;
          this.sendNotification(NotificationType.SUCCESS, `Votre compte à bien été créer ${user.firstName}.
          Veuillez confirmez votre email et récuperer votre mot de passe fournis par mail`)
        },
        error: (errorResponse) => {
          console.log(errorResponse)
          this.sendNotification(NotificationType.ERROR, errorResponse.error.message)
          this.showLoading = false;
        }
      })
    );
  }

  private sendNotification(notificationType: NotificationType, message: string): void {
    if (message) {
      this.notificationService.notify(notificationType,message);
    } else {
      this.notificationService.notify(notificationType, 'Une erreur est survenu, veuillez ressayez.')
    }
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(sub=>sub.unsubscribe());
  }
}
