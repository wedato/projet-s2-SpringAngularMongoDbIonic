import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpErrorResponse, HttpEvent, HttpResponse} from "@angular/common/http";
import {User} from "../models/user";
import { GroupeTp } from '../models/groupe.model';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class GroupeTpService {
    private host = environment.apiUrl

    constructor(private http : HttpClient){}

    public getListGroupeTp() : Observable<GroupeTp[]>{
        return this.http.get<GroupeTp[]>(`${this.host}/api/groupetp`);
    }

    public addGroupeTpLocalCache(groupe : GroupeTp[]) : void{
        localStorage.setItem('listGroupeTp', JSON.stringify(groupe))
    }

    public deleteGroupe(nomGroupe : String): Observable<any>{
        return this.http.delete<GroupeTp>(`${this.host}/api/groupetp/${nomGroupe}`)
    }

    public addGroupeTp(formData: FormData): Observable<GroupeTp>{
        // la raison pour laquelle on passe des request param -> le formData
        return this.http.post<GroupeTp>(`${this.host}/api/groupetp`, formData);
      }

    public createGroupeFormData(groupe : GroupeTp): FormData{
        const formData = new FormData();
        formData.append('nomGroupe', groupe.nomGroupe);
        return formData;
    }
}