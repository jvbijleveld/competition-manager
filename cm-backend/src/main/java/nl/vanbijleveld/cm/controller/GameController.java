package nl.vanbijleveld.cm.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;

import javassist.NotFoundException;
import nl.vanbijleveld.cm.api.GameService;
import nl.vanbijleveld.cm.game.Game;
import nl.vanbijleveld.cm.team.Team;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    static final String CONTEXT_ROOT = "/games";

    @Autowired
    GameService gameService;

    @ResponseBody
    @GetMapping(value = CONTEXT_ROOT)
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @ResponseBody
    @GetMapping(value = CONTEXT_ROOT + "/{id}")
    public Game showTeam(@PathVariable long id) throws NotFoundException {
        LOGGER.info("Looking for game " + id);
        return gameService.getGame(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PutMapping(value = CONTEXT_ROOT)
    public Game createGame(@ModelAttribute("contenders") List<Team> teams) {
        LOGGER.info("Creating a new Game");
        return gameService.createNewGame(teams);
    }
}
