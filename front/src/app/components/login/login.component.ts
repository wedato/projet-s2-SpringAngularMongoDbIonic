import {Component, OnDestroy, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/authentication.service";
import {NotificationService} from "../../services/notification.service";
import {User} from "../../models/user";
import {Subscription} from "rxjs";
import {HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {NotificationType} from "../../enum/notification-type.enum";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit,OnDestroy {
  public showLoading: boolean;
  private subscriptions: Subscription[] = [];



  constructor(private router: Router, private authenticationService: AuthenticationService,
              private notificationService: NotificationService) { }

  //redirige si l'user est deja login vers la mainpage
  ngOnInit(): void {
    if (this.authenticationService.isUserLoggedIn()){
      this.router.navigateByUrl('/user/management');
    } else {
      this.router.navigateByUrl('/login')
    }
  }
  public onLogin(user: User): void{
    this.showLoading = true;
    this.subscriptions.push(

      this.authenticationService.login(user).subscribe({
        next:(response) => {
          const token = response.headers.get('Jwt-Token');
          this.authenticationService.saveToken(token);
          this.authenticationService.addUserToLocalCache(response.body);
          this.router.navigateByUrl('/user/management');
          this.showLoading = false;
        },
        error: (errorResponse) => {
          this.sendErrorNotification(NotificationType.ERROR, errorResponse.error.message)
          this.showLoading = false;
        }
       })
    );
  }

  private sendErrorNotification(notificationType: NotificationType, message: string): void {
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
