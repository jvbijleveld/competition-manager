import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule, Routes } from '@angular/router';

import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule }    from '@angular/common/http';

import { AppComponent } from './app.component';
//import { TeamsComponent } from './teams/teams.component';
import { TeamListComponent } from './team-list/team-list.component';
import { PlayerComponent } from './player/player.component';
import { TeamService } from './team.service';
import { PlayerService } from './player.service';

import { AppRoutingModule }     from './app-routing.module';
import { MenuComponent } from './menu/menu.component';

//import { MatMenuModule, MatButtonModule, MatIconModule, MatCardModule } from '@angular/material';


const appRoutes: Routes = [
  {
    path: 'teams',
    component: TeamListComponent,
    data: { title: 'Teams' }
  },
  { path: 'players',
    component: PlayerComponent,
    pathMatch: 'full'
  },
  { path: '**', 
    redirectTo: '/',
    pathMatch: 'full' }
];

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    PlayerComponent,
    TeamListComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    TeamService,
    PlayerService
  ],
  bootstrap: [AppComponent],
  exports: [ RouterModule ]
})
export class AppModule { }
