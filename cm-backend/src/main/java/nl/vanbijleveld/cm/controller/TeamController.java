package nl.vanbijleveld.cm.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;

import javassist.NotFoundException;
import nl.vanbijleveld.cm.api.TeamService;
import nl.vanbijleveld.cm.team.Team;
import nl.vanbijleveld.cm.validate.TeamValidator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    static final String CONTEXT_ROOT = "/team";

    @Autowired
    TeamService teamService;

    @Autowired
    TeamValidator teamValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(teamValidator);
    }

    @ResponseBody
    @GetMapping(value = CONTEXT_ROOT + "/{id}")
    public Team showTeam(@PathVariable long id) throws NotFoundException {
        LOGGER.info("Looking for team " + id);
        return teamService.getTeam(id);
    }

    @ResponseBody
    @GetMapping(value = CONTEXT_ROOT + "/all")
    public List<Team> getAllTeams() {
        LOGGER.info("Fetching all teams");
        return teamService.listTeams();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PutMapping(value = CONTEXT_ROOT)
    public Team createTeam(@ModelAttribute("team") @Validated Team team) {
        LOGGER.info("Creating new Team");
        return teamService.createTeam(team.getName(), team.getYell());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PutMapping(value = CONTEXT_ROOT + "/{teamId}/addPlayer/{playerId}")
    public Team addPlayerToTeam(@PathVariable Long teamId, @PathVariable Long playerId) throws NotFoundException {
        LOGGER.info("Adding player with id {} to team with id {}", playerId, teamId);
        return teamService.addPlayer(teamId, playerId);
    }
}
