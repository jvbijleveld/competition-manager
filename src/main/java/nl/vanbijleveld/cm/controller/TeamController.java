package nl.vanbijleveld.cm.controller;

import java.lang.invoke.MethodHandles;

import javassist.NotFoundException;
import nl.vanbijleveld.cm.api.TeamService;
import nl.vanbijleveld.cm.team.Team;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    static final String CONTEXT_ROOT = "/team";

    @Autowired
    TeamService teamService;

    @ResponseBody
    @GetMapping(value = CONTEXT_ROOT + "/{id}")
    public Team showTeam(@PathVariable long id) throws NotFoundException {
        LOGGER.info("Looking for team " + id);
        return teamService.getTeam(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PutMapping(value = CONTEXT_ROOT)
    public Team createTeam(@RequestBody Team request) {
        LOGGER.info("Creating new Team");
        return teamService.createTeam(request.getName(), request.getYell());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PutMapping(value = CONTEXT_ROOT + "/{teamId}/addPlayer/{playerId}")
    public Team addPlayerToTeam(@PathVariable Long teamId, @PathVariable Long playerId) throws NotFoundException {
        LOGGER.info("Adding player with id {} to team with id {}", playerId, teamId);
        return teamService.addPlayer(teamId, playerId);
    }
}
