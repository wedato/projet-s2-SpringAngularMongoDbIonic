import { Component, OnInit } from '@angular/core';
import {UserComponent} from "../user/user.component";
import {User} from "../../models/user";
import {AuthenticationService} from "../../services/authentication.service";
import {Role} from "../../enum/Role";


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private authenticationService: AuthenticationService) { }

  user : User;

  ngOnInit(): void {
    this.user = this.authenticationService.getUserFromLocalCache();
  }



  public get isAdmin(): boolean {
    return this.authenticationService.getUserFromLocalCache().role === Role.ADMIN;
  }


}
