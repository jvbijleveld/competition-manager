package nl.vanbijleveld.cm;

import nl.vanbijleveld.cm.player.Player;
import nl.vanbijleveld.cm.player.PlayerService;
import nl.vanbijleveld.cm.team.Team;
import nl.vanbijleveld.cm.team.TeamService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.lang.invoke.MethodHandles;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@PropertySource({"classpath:env.properties"})
@RestController
@SpringBootApplication
public class CompetitionManagerApplication {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    PlayerService playerService;

    @Autowired
    TeamService teamService;

    @GetMapping(value = "/")
    public String index() {
        return "Hello all";
    }
    
//Default ErrorHandling
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleGeneralException(final NotFoundException exception, final HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(final Exception exception, final HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    
    
//Players
    @ResponseBody
    @GetMapping(value = "/player/{id}")
    public Player showPlayer(@PathVariable long id) throws NotFoundException {
    	LOGGER.info("Looking for player " + id);
      	return playerService.getPlayer(id);
    }
    
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(value="/player")
    public Player addNewPlayer(@ModelAttribute Player request){
        LOGGER.info("Creating new Player");
        playerService.createNewPlayer(Player);
    }
    
    
//Teams    
    @ResponseBody
    @GetMapping(value = "/team/{id}")
    public Team showTeam(@PathVariable long id) throws NotFoundException {
    	LOGGER.info("Looking for team " + id);
      	return teamService.getTeam(id);
    }

    public static void main(String[] args) {
        SpringApplication.run(CompetitionManagerApplication.class, args);
    }
}
