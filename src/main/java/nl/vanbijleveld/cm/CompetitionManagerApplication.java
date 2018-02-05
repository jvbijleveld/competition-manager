package nl.vanbijleveld.cm;

import java.lang.invoke.MethodHandles;

import javassist.NotFoundException;

import javax.servlet.http.HttpServletRequest;

import nl.vanbijleveld.cm.api.PlayerService;
import nl.vanbijleveld.cm.api.TeamService;
import nl.vanbijleveld.cm.exception.ConflictException;
import nl.vanbijleveld.cm.exception.ExceptionResponse;
import nl.vanbijleveld.cm.player.Player;
import nl.vanbijleveld.cm.team.Team;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@PropertySource({"classpath:env.properties"})
@RestController
@SpringBootApplication
public class CompetitionManagerApplication extends SpringBootServletInitializer {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    PlayerService playerService;

    @Autowired
    TeamService teamService;

    @GetMapping(value = "/")
    public String index() {
        return "Hello all";
    }

    // Default ErrorHandling
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleGeneralException(final NotFoundException exception, final HttpServletRequest request) {
        ExceptionResponse response = new ExceptionResponse(exception, HttpStatus.NOT_FOUND);
        return new ResponseEntity<ExceptionResponse>(response, response.getStatusCode());
    }

    @ResponseBody
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> handleConflictException(final ConflictException exception, final HttpServletRequest request) {
        ExceptionResponse response = new ExceptionResponse(exception, HttpStatus.CONFLICT);
        return new ResponseEntity<ExceptionResponse>(response, response.getStatusCode());
    }

    // Players
    @ResponseBody
    @GetMapping(value = "/player/{id}")
    public Player showPlayer(@PathVariable long id) throws NotFoundException {
        LOGGER.info("Looking for player " + id);
        return playerService.getPlayer(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PutMapping(value = "/player")
    public Player addNewPlayer(@RequestBody Player request) throws ConflictException {
        LOGGER.info("Creating new Player");
        return playerService.createNewPlayer(request);
    }

    // Teams
    @ResponseBody
    @GetMapping(value = "/team/{id}")
    public Team showTeam(@PathVariable long id) throws NotFoundException {
        LOGGER.info("Looking for team " + id);
        return teamService.getTeam(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PutMapping(value = "/team")
    public Team createTeam(@RequestBody Team request) {
        LOGGER.info("Creating new Team");
        return teamService.createTeam(request.getName(), request.getYell());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PutMapping(value = "/team/{teamId}/addPlayer/{playerId}")
    public Team addPlayerToTeam(@PathVariable Long teamId, @PathVariable Long playerId) throws NotFoundException {
        LOGGER.info("Adding player with id {} to team with id {}", playerId, teamId);
        return teamService.addPlayer(teamId, playerId);
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CompetitionManagerApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(CompetitionManagerApplication.class, args);
    }
}
