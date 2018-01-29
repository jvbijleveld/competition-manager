package nl.vanbijleveld.cm;

import nl.vanbijleveld.cm.player.Player;
import nl.vanbijleveld.cm.player.PlayerService;

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
import org.springframework.web.bind.annotation.RestController;

@PropertySource({"classpath:application.properties"})
@RestController
@SpringBootApplication
public class CompetitionManagerApplication {

    @Autowired
    PlayerService playerService;

    @RequestMapping(value = "/")
    public String index() {
        return "Hello all";
    }
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleGeneralException(final NotFoundException exception, final HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @ResponseBody
    @RequestMapping(value = "/player/{id}")
    public Player showPlayer(@PathVariable long id) throws NotFoundException {
    	System.out.println("Looking for player " + id);
      	return playerService.getPlayer(id);
    }

    public static void main(String[] args) {
        SpringApplication.run(CompetitionManagerApplication.class, args);
    }
}
