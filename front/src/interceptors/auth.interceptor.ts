import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {AuthenticationService} from "../app/services/authentication.service";

// intereceptor : intercept les requetes avant qu'elle atteigne le serveur et la transformer avant qu'on l'envoie au backend
// on ajoute la token a la requete avant de l'envoyer au back , comme ca pas besoin de faire l'ajout dans chaque requete

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authenticationService: AuthenticationService) {}

  intercept(httpRequest: HttpRequest<any>, httpHandler: HttpHandler): Observable<HttpEvent<any>> {

    // on vire les requete login et register car pas de token a ce moment
    if (httpRequest.url.includes(`${this.authenticationService.host}/user/login`)) {
      return httpHandler.handle(httpRequest);
    }
    if (httpRequest.url.includes(`${this.authenticationService.host}/user/register`)) {
      return httpHandler.handle(httpRequest);
    }
    if (httpRequest.url.includes(`${this.authenticationService.host}/user/resetpassword`)) {
      return httpHandler.handle(httpRequest);
    }
    // load le token si c'est pas une des requete qui n'en a pas besoin
    this.authenticationService.loadToken();
    const token = this.authenticationService.getToken();
    const request = httpRequest.clone({setHeaders: { Authorization: `Bearer ${token}`}})
    return httpHandler.handle(request);
  }
}
