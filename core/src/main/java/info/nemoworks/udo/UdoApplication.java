package info.nemoworks.udo;

import org.dizitart.no2.Nitrite;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UdoApplication {

	public static void main(String[] args) {
		SpringApplication.run(UdoApplication.class, args);
	}

	@Bean
	public Nitrite nitriteDB() {
		return Nitrite.builder().openOrCreate();
	}
}
