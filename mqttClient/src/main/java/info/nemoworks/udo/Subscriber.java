package info.nemoworks.udo;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;

public class Subscriber {
    public Subscriber(){
    }

    public MqttClient init(String url,String topic) throws  MqttException{
        System.out.println("== START SUBSCRIBER ==");
        MqttClient client=new MqttClient(url, MqttClient.generateClientId());
        client.setCallback( new SimpleMqttCallBack() );
        client.connect();
        client.subscribe(topic);
        return client;
    }

//    public static void main(String[] args) throws MqttException {
//
//
//
//    }

}
