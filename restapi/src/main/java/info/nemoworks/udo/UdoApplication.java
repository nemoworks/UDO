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

//	@Bean
//	CommandLineRunner initDatabase(NitriteSchemaRepository schemaRepository) {
//
//		return args -> {
//			String f = UdoApplication.loadFromFile("src/main/resources/room.json");
//
//			JSONObject jsonObject = JSON.parseObject(f);
//			UdoSchema schema = new UdoSchema("3-15-1", "room", jsonObject);
//
//			schemaRepository.saveSchema(schema);
//
//			f = UdoApplication.loadFromFile("src/main/resources/light.json");
//
//			jsonObject = JSON.parseObject(f);
//			schema = new UdoSchema("3-15-2", "air", jsonObject);
//
//			schemaRepository.saveSchema(schema);
//
//		};
//	}
//
//	public static String loadFromFile(String path) throws IOException {
//		return new String(Files.readAllBytes(Paths.get(path)));
//	}

}
