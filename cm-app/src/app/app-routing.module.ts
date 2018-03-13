import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { TeamListComponent } from './team-list/team-list.component';
import { PlayerComponent } from './player/player.component';
import { TeamDetailComponent } from './team-detail/team-detail.component';

const appRoutes: Routes = [
  {
    path: 'teams',
    component: TeamListComponent,
    data: { title: 'Teams' }
  },
  {
    path: 'teams/:id',
    component: TeamDetailComponent
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
