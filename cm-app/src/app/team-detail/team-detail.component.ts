import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";

import { Team } from '../team';
import { TeamService } from '../team.service';

@Component({
  selector: 'app-team-detail',
  templateUrl: './team-detail.component.html',
  styleUrls: ['./team-detail.component.css']
})
export class TeamDetailComponent implements OnInit {
  
  team : Team;
 
  constructor(private teamService: TeamService, private route: ActivatedRoute) { 
  }

  ngOnInit(): void {
    this.getTeamDetails();
  }
  
  getTeamDetails(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.teamService.getTeamDetails(id).subscribe(team => this.team = team);
  }

}
