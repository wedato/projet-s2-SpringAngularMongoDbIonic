import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../models/user";
import { JwtHelperService } from "@auth0/angular-jwt";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  public host = environment.apiUrl;
  private token: string;
  private loggedInUsername: string;
  private jwtHelper = new JwtHelperService();

  constructor(private http: HttpClient) {}

  // login
  // observable car les requete ca prend du mal , quand ta requete est finis hop il t'apppelle
  public login(user: User): Observable<HttpResponse<any>> {

    return this.http.post<HttpResponse<any>>(`${this.host}/user/login`, user, {observe: "response"}) // donne moi l'url , request body, et toute la reponse
  }

  public register(user: User): Observable<User> {

      return this.http.post<User>(`${this.host}/user/register`, user)

  }

  public logOut() : void {
    this.token = null;
    this.loggedInUsername = null;
    // le cache
    localStorage.removeItem('user');
    localStorage.removeItem('token');
    localStorage.removeItem('users');
  }

  public saveToken(token: string) : void {
    this.token = token;
    //save le token dans le local cache
    localStorage.setItem('token', token);
  }
  public addUserToLocalCache(user: User) : void {
    // json strigify pour transformer user en string
    localStorage.setItem('user', JSON.stringify(user));
  }
  public getUserFromLocalCache() : User {
    return JSON.parse(localStorage.getItem('user'));
  }
  public loadToken(): void{
    this.token = localStorage.getItem('token');
  }
  public getToken(): string {
    return this.token;
  }
  public isUserLoggedIn(): boolean {
    this.loadToken();
    if (this.token != null && this.token != ''){
      if (this.jwtHelper.decodeToken(this.token).sub != null || ''){ // si le token n'est pas null ou vide on continue
        if (!this.jwtHelper.isTokenExpired(this.token)){
          this.loggedInUsername = this.jwtHelper.decodeToken(this.token).sub
          return true;
        }
      }
    } else {
      this.logOut();
      return false;
    }
    return false;
  }
}
