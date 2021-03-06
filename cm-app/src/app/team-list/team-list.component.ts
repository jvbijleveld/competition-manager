import { Component, OnInit } from '@angular/core';

import { Team } from '../team';
import { TeamService } from '../team.service';

@Component({
  selector: 'app-team-list',
  templateUrl: './team-list.component.html',
  styleUrls: ['./team-list.component.css']
})
export class TeamListComponent implements OnInit {

  teams: Team [];
  
  constructor(private teamService: TeamService) { }

  ngOnInit() {
    this.getTeams();
  }
  
  getTeams(): void {
    this.teamService.getTeams().subscribe(teams => this.teams = teams);
  }

}
