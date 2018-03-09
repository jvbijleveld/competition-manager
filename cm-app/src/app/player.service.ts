import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { of } from 'rxjs/observable/of';
import { catchError, map, tap } from 'rxjs/operators';

import {Player} from './player';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class PlayerService {

  private playerUrl = 'player';    

    constructor(private http: HttpClient) { }
    
    getPlayers(): Observable<Player[]> {
    return this.http.get<Player[]>(this.playerUrl)
    .pipe(
        catchError(this.handleError('getPlayers', []))
    );
  }
  
  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); 
      return of(result as T);
    };
  }

}
