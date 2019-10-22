/**
 * 
 */
package league;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import league.clients.MicroserviceClient;

/**
 * @author javier.elias
 *
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public MicroserviceClient footballDataClient() {
		return new MicroserviceClient("https://api.football-data.org/v2");
	}

}
