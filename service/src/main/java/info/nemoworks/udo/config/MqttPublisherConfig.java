package info.nemoworks.udo.config;

import info.nemoworks.udo.Publisher;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttPublisherConfig {
    @Value("${mqtt_publish_topic}")
    private String topic;

    @Value("${mqtt_url}")
    private String serverURI;

    @Bean
    public Publisher publisher(){
        MqttClient mqttClient = null;
        try {
            mqttClient = new MqttClient(serverURI, MqttClient.generateClientId());
            mqttClient.connect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
        return new Publisher(topic, mqttClient);
    }
}
