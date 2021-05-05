package info.nemoworks.udo;

import info.nemoworks.udo.service.UdoService;
import org.dizitart.no2.Nitrite;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UdoApplication {

    public UdoApplication(UdoService udoService) {
        this.udoService = udoService;
    }

    public static void main(String[] args) {
        SpringApplication.run(UdoApplication.class, args);
    }

    final UdoService udoService;

    @Bean
    public Nitrite nitriteDB() {
        return Nitrite.builder().openOrCreate();

    }

//	@Bean
//	public MqttClient mqttClient(){
//		try {
//			return new Subscriber().init("tcp://localhost:1883","udo",udoService);
//		} catch (MqttException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
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
