import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpErrorResponse, HttpEvent, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../models/user";


@Injectable({
  providedIn: 'root'
})
export class FicheService {
  private host = environment.apiUrl;

  constructor(private http: HttpClient) { }

  public getUsersInscritCours(nomCours: String) :Observable<User[]>{
    return this.http.get<User[]>(`${this.host}/fiche/liste/${nomCours}`);
}

  // public getUsers(nomCours:String): Observable<User[]>{
  //   return this.http.get<User[]>(`${this.host}/user/list`);
  // }
  // public addUser(formData: FormData): Observable<User>{
  //   // la raison pour laquelle on passe des request param -> le formData
  //   return this.http.post<User>(`${this.host}/user/add`, formData);
  // }
  // public updateUser(formData: FormData): Observable<User>{
  //   return this.http.post<User>(`${this.host}/user/update`, formData);
  // }
  // public resetPassword(email: string): Observable<any>{
  //   return this.http.get(`${this.host}/user/resetpassword/${email}`);
  // }
  // public updateProfileImage(formData: FormData): Observable<HttpEvent<User>>{
  //   return this.http.post<User>(`${this.host}/user/updateProfileImage/`,formData,
  //     {reportProgress: true,  // progres de l'image
  //               observe:'events'
  //     });
  // }
  // public deleteUser(username: string): Observable<any>{
  //   return this.http.delete<User>(`${this.host}/user/delete/${username}`);
  // }
  //
  // public addUsersToLocalCache(users: User[]): void {
  //   localStorage.setItem('users', JSON.stringify(users));
  // }
  // public getUsersFromLocalCache(): User[] {
  //   if (localStorage.getItem('users')){
  //     return JSON.parse(localStorage.getItem('users'));
  //   }
  //   return null;
  // }
  //
  // // formData equivalent au formData de postman pour les request param
  // public createUserFormData(loggedInUsername: string, user: User, profileImage: File): FormData {
  //   const formData = new FormData();
  //   formData.append('currentUsername', loggedInUsername);
  //   formData.append('firstName', user.firstName);
  //   formData.append('lastName', user.lastName);
  //   formData.append('username', user.username);
  //   formData.append('email', user.email);
  //   formData.append('role', user.role);
  //   formData.append('profileImage', profileImage);
  //   formData.append('isActive', JSON.stringify(user.active));
  //   formData.append('isNonLocked', JSON.stringify(user.notLocked));
  //   formData.append('profileImage', profileImage);
  //
  //
  //
  //   console.log( formData.get('firstName'))
  //
  //   return formData;
  // }
}

