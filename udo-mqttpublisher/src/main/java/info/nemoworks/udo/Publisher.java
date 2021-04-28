package info.nemoworks.udo;

import info.nemoworks.udo.model.Udo;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.nio.charset.StandardCharsets;

public class Publisher {
    private final String topic;
    private final MqttClient mqttClient;

    public Publisher(String topic, MqttClient mqttClient) {
        this.topic = topic;
        this.mqttClient = mqttClient;
    }

    public void publishUdo(Udo udo) throws MqttException {
        System.out.println("publish udo to mqtt: " + udo.toJSON());
        MqttMessage message = new MqttMessage();
        message.setPayload(udo.toJSON().toString().getBytes(StandardCharsets.UTF_8));
        mqttClient.publish(topic, message);
    }
}
