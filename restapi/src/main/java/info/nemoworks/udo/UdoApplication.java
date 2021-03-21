package info.nemoworks.udo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.dizitart.no2.Nitrite;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.repository.nitrite.NitriteSchemaRepository;

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
//			f = UdoApplication.loadFromFile("src/main/resources/purifier.json");
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
