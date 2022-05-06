import {Component, OnDestroy, OnInit} from '@angular/core';
import {BehaviorSubject, Subscription} from "rxjs";
import {User} from "../../models/user";
import {UserService} from "../../services/user.service";
import {NotificationService} from "../../services/notification.service";
import {NotificationType} from "../../enum/notification-type.enum";
import {NgForm} from "@angular/forms";
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";
import {HttpEvent, HttpEventType} from "@angular/common/http";
import {FileUploadStatus} from "../../models/FileUploadStatus";
import {Role} from "../../enum/Role";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit , OnDestroy{
  // par defaut le titre est utilisateurs, le titre change quand on clique
  private titleSubject = new BehaviorSubject<string>('Utilisateurs');

  public titleAction$ = this.titleSubject.asObservable();
  public users: User[];
  //le spiner
  public refreshing: boolean;
  private subscriptions: Subscription[] = [];
  public selectedUser: User;
  public fileName: string;
  public profileImage: File;
  public editUser = new User();
  public currentUsername: string;
  public user: User;
  public fileStatus = new FileUploadStatus();
  public profileImageInput: any;


  constructor( private router: Router, public authenticationService: AuthenticationService , private userService: UserService, private notificationService: NotificationService) { }

  // change le titre chaque fois qu'on clique sur un nouveu tab
  public changeTitle(title: string): void {

    this.titleSubject.next(title)
  }
  ngOnInit(): void {
   this.user = this.authenticationService.getUserFromLocalCache();
    this.getUsers(true);
  }

  public getUsers(showNotification: boolean): void{
    this.refreshing = true;
    this.subscriptions.push(
      this.userService.getUsers().subscribe({
        next :(response) => {
          this.userService.addUsersToLocalCache(response);
          this.users = response;
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

  private sendNotification(notificationType: NotificationType, message: string): void {
    if (message) {
      this.notificationService.notify(notificationType,message);
    } else {
      this.notificationService.notify(notificationType, 'Une erreur est survenu, veuillez ressayez.')
    }
  }

  public onSelectUser(selectedUser: User): void {
    this.selectedUser = selectedUser;
    this.clickButton('openUserInfo')
  }



  public onProfileImageChange(file: FileList): void{
    this.fileName = file.item(0).name;
    this.profileImage = file.item(0);

  }

  saveNewUser():void {
    this.clickButton('new-user-save')
  }


  onAddNewUser(userForm: NgForm):void {

    const formData = this.userService.createUserFormData(null,userForm.value, this.profileImage);
    this.subscriptions.push(
      this.userService.addUser(formData).subscribe({
        next:(response) => {
          this.clickButton('new-user-close');
          this.getUsers(false);
          this.fileName=null;
          this.profileImage=null;
          userForm.reset();
          this.sendNotification(NotificationType.SUCCESS, `${response.firstName} ${response.lastName} a bien été ajouté`)
        },
        error:(errorResponse) => {
          this.sendNotification(NotificationType.ERROR, `Une erreur est survenu, veuillez ressayez`)
        }
      })
    )

  }
  private clickButton(buttonId: string):void {
    document.getElementById(buttonId).click();
  }

  // ça peut être n'importe quoi le string qu'on recherche et veut match , nom id email

  public searchUsers(searchTerm: string): void {
    console.log(searchTerm)
    const results: User[] = [];
    for (const user of this.userService.getUsersFromLocalCache()) {
      if (user.firstName.toLowerCase().indexOf(searchTerm.toLowerCase()) !== -1 ||
        user.lastName.toLowerCase().indexOf(searchTerm.toLowerCase()) !== -1 ||
        user.username.toLowerCase().indexOf(searchTerm.toLowerCase()) !== -1)  {
        results.push(user);
      }
    }
    this.users = results
    if (results.length === 0 || !searchTerm){
      this.users = this.userService.getUsersFromLocalCache();
    }
  }

  public onEditUser(editUser: User): void {
    this.editUser = editUser;
    this.currentUsername= editUser.username;
    this.clickButton('openUserEdit');
  }

  public onUpdateUser() : void {

    const formData = this.userService.createUserFormData(this.currentUsername,this.editUser, this.profileImage);
    console.log(formData)
    this.subscriptions.push(
      this.userService.updateUser(formData).subscribe({
        next:(response) => {
          this.clickButton('closeEditUserModalButton');
          this.getUsers(false);
          this.fileName=null;
          this.profileImage=null;
          this.sendNotification(NotificationType.SUCCESS, `${response.firstName} ${response.lastName} a bien ete mis à jour`)
        },
        error:(errorResponse) => {
          this.sendNotification(NotificationType.ERROR, `Une erreur est survenu, veuillez ressayez`)
        }
      })
    )
  }

  public onUpdateCurrentUser(user: User): void {
    this.refreshing = true;
    this.currentUsername = this.authenticationService.getUserFromLocalCache().username;
    const formData = this.userService.createUserFormData(this.currentUsername,user, this.profileImage);
    console.log(formData)
    this.subscriptions.push(
      this.userService.updateUser(formData).subscribe({
        next:(response) => {
          this.authenticationService.addUserToLocalCache(response);
          this.getUsers(false);
          this.fileName=null;
          this.profileImage=null;
          this.sendNotification(NotificationType.SUCCESS, `${response.firstName} ${response.lastName} a bien ete mis à jour`)
        },
        error:(errorResponse) => {
          this.sendNotification(NotificationType.ERROR, `Une erreur est survenu, veuillez ressayez`)
          this.refreshing=false;
          this.profileImage=null;
        }
      })
    )
  }



  public onDeleteUser(username: string) {
      this.subscriptions.push(
        this.userService.deleteUser(username).subscribe({
            next: (response) => {
              this.sendNotification(NotificationType.SUCCESS, `L'utilisateur a bien ete supprimé`);
              //refresh list
              this.getUsers(true)
            },
            error: (errorResponse) => {
              this.sendNotification(NotificationType.ERROR, `Une erreur est survenu, veuillez ressayez`)
            }
          }
        )
      )
  }

  public onResetPassword(emailForm: NgForm): void {
    this.refreshing = true;
    const emailAddress = emailForm.value['reset-password-email'];
    console.log(emailAddress);
    this.subscriptions.push(
      this.userService.resetPassword(emailAddress).subscribe({
        next:(response) => {
          this.sendNotification(NotificationType.SUCCESS, 'Votre mot de passe a bien été reset, check vos mail"')
          this.refreshing = false;
        },
        error:(errorResponse) => {
          this.sendNotification(NotificationType.WARNING, "Verifiez votre email")
          this.refreshing=false;
        } ,
        complete:() => emailForm.reset()
      })
    )
  }




  public onLogOut(): void {
    this.authenticationService.logOut();
    this.router.navigate(['/login']);
    this.sendNotification(NotificationType.SUCCESS, 'Vous venez de vous deconnectez avec succes');
  }

  // appelle le backend
  public onUpdateProfileImage(): void {
    const formData = new FormData();
    formData.append('username' , this.user.username);
    formData.append('profileImage' , this.profileImage);
    this.subscriptions.push(
      this.userService.updateProfileImage(formData).subscribe({
        next:(event) => {
          this.reportUploadProgress(event)
        },
        error:(errorResponse) => {
          this.sendNotification(NotificationType.ERROR, `Une erreur est survenu, veuillez ressayez`)
          this.fileStatus.status='done';
        }
      })
    )
  }
  updateProfileImage():void {
    this.clickButton('profile-image-input')
  }

  private reportUploadProgress(event: HttpEvent<User>) {
    switch (event.type) {
      case HttpEventType.UploadProgress:
        this.fileStatus.percentage = Math.round(100*event.loaded / event.total);
        this.fileStatus.status = 'progress';
        break;
      case HttpEventType.Response:
        if (event.status === 200){
          this.user.profileImageUrl = `${event.body.profileImageUrl}?time=${new Date().getTime()}`;
          this.sendNotification(NotificationType.SUCCESS,'La photo de profil a bien été uploadé avec succes' )
          this.fileStatus.status = 'done';
          break;
        } else {
          this.sendNotification(NotificationType.SUCCESS, "Impossible d'upload l'image. veuillez ressayez");
          break;
        }
      default:
        'Finished all process'
    }
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(sub=>sub.unsubscribe());
  }


}
