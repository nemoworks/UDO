package info.nemoworks.udo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UdoApplication {

	public static void main(String[] args) {
		SpringApplication.run(UdoApplication.class, args);
	}

	@bean
	public Nitrite nitriteDB(){
		return  Nitrite.builder().openOrCreate();

	} 
	

}
