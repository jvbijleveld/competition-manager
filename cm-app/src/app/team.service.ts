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
    
    getTeamDetails(id: number): Observable<any> {
      const url = `teams/${id}`;
      return this.http.get<Team>(url).pipe(catchError(this.handleError('getTeamDetails', [])));
    }
    
  
    private handleError<T> (operation = 'operation', result?: T) {
      return (error: any): Observable<T> => {
        console.error(error); 
        return of(result as T);
      };
    }

}
