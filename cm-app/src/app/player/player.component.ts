import { Component, OnInit } from '@angular/core';

import { Player } from '../player';
import { PlayerService } from '../player.service';

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.css']
})

export class PlayerComponent implements OnInit {

  players: Player[];
  
  constructor(private playerService: PlayerService) { }

  ngOnInit() {
    this.getPlayers();
  }
  
  getPlayers(): void {
    this.playerService.getPlayers().subscribe(players => this.players = players);
  }
}
