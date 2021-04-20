package info.nemoworks.udo;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Publisher {
    private final MqttClient mqttClient;

    public Publisher(MqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }

    public void publishUdo(String udo) throws MqttException {
        System.out.println("udo-pub: " + udo);
        MqttMessage message = new MqttMessage();
        message.setPayload(udo.getBytes());
        mqttClient.publish("udo-pub", message);
    }
}
