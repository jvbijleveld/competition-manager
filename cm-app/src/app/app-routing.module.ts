import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { TeamListComponent } from './team-list/team-list.component';
import { PlayerComponent } from './player/player.component';

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
  imports: [ RouterModule.forRoot(appRoutes) ],
  exports: [ RouterModule ]
})

export class AppRoutingModule { }
