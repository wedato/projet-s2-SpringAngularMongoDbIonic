import { Component, OnInit } from '@angular/core';
import {BehaviorSubject} from "rxjs";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  // par defaut le titre est utilisateurs, le titre change quand on clique
  private titleSubject = new BehaviorSubject<string>('Utilisateurs');

  public titleAction$ = this.titleSubject.asObservable();

  constructor() { }

  // change le titre chaque fois qu'on clique sur un nouveu tab
  public changeTitle(title: string): void {
    this.titleSubject.next(title)
  }
  ngOnInit(): void {
  }

}
