package info.nemoworks.udo;

import com.alibaba.fastjson.JSONObject;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DemoPublisher {


    public static void main(String[] args) throws MqttException {

        String messageString = "{\n" +
                "  \"udoi\":\"123\",\n" +
                "  \"schemaId\" : \"Air_purifier\",\n" +
                "  \"content\":{\n" +
                "    \"Name\" : \"cba\",\n" +
                "    \"Brand\" : \"xiaomi\",\n" +
                "    \"Speed\" : 120\n" +
                "  }\n" +
                "}";

//        try {
//            messageString = new String(Files.readAllBytes(Paths.get("/Users/tangcong/Desktop/UDO/restapi/src/main/resources/test.json")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        if (args.length == 2 ) {
            messageString = args[1];
        }


        System.out.println("== START PUBLISHER ==");


        MqttClient client = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
        client.connect();
        while(true){
            MqttMessage message = new MqttMessage();
            message.setPayload(messageString.getBytes());
            client.publish("udo", message);

            System.out.println("\tMessage '"+ messageString +"' to 'udo'");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        //client.disconnect();

        //System.out.println("== END PUBLISHER ==");

    }


}
