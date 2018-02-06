package nl.vanbijleveld.cm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RestController;

@PropertySource({"classpath:env.properties"})
@RestController
@SpringBootApplication
public class CompetitionManagerApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CompetitionManagerApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(CompetitionManagerApplication.class, args);
    }
}
