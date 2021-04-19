package info.nemoworks.udo;


import info.nemoworks.udo.service.UdoService;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Subscriber {
    public Subscriber(){}

    public MqttClient init(String url, String topic, UdoService udoService) throws  MqttException{
        System.out.println("== START SUBSCRIBER ==");
        MqttClient client=new MqttClient(url, MqttClient.generateClientId());
        client.setCallback( new SimpleMqttCallBack(udoService) );
        client.connect();
        client.subscribe(topic);
        return client;
    }
}
