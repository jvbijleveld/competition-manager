import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { of } from 'rxjs/observable/of';
import { catchError, map, tap } from 'rxjs/operators';

import {Team} from './team';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class TeamService {
    
    private teamUrl = 'teams';    

    constructor(private http: HttpClient) { }
    
    getTeams(): Observable<Team[]> {
    return this.http.get<Team[]>(this.teamUrl)
    .pipe(
        catchError(this.handleError('getTeams', []))
    );
  }
  
  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
      return of(result as T);
    };
  }
 

}
