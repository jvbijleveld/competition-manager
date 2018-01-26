package nl.vanbijleveld.cm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CompetitionManagerApplication {
	
	@RequestMapping(value = "/")
	public String available() {
    	return "Hello all";
	}

	public static void main(String[] args) {
		SpringApplication.run(CompetitionManagerApplication.class, args);
	}
}
