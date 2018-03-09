package nl.vanbijleveld.cm.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;

import javassist.NotFoundException;
import nl.vanbijleveld.cm.api.PlayerService;
import nl.vanbijleveld.cm.exception.ConflictException;
import nl.vanbijleveld.cm.player.Player;
import nl.vanbijleveld.cm.validate.PlayerValidator;

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
public class PlayerController {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    static final String CONTEXT_ROOT = "/player";

    @Autowired
    PlayerValidator playerValidator;

    @Autowired
    private PlayerService playerService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(playerValidator);
    }

    @ResponseBody
    @GetMapping(value = CONTEXT_ROOT + "/{id}")
    public Player showPlayer(@PathVariable long id) throws NotFoundException {
        LOGGER.info("Looking for player " + id);
        return playerService.getPlayer(id);
    }
    
    @ResponseBody
    @GetMapping(value = CONTEXT_ROOT)
    public List<Player> showAllPlayers(){
        return playerService.listPLayers();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PutMapping(value = CONTEXT_ROOT)
    public Player addNewPlayer(@ModelAttribute("player") @Validated Player player) throws ConflictException {
        LOGGER.info("Creating new Player");
        return playerService.createNewPlayer(player);
    }

}
