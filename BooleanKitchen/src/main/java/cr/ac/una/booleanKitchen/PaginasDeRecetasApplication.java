package cr.ac.una.booleanKitchen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class PaginasDeRecetasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaginasDeRecetasApplication.class, args);
	}

}
