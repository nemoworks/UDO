package info.nemoworks.udo;

import java.util.stream.IntStream;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class DemoPublisher {

    public static void main(String[] args) throws MqttException {

        System.out.println("== START PUBLISHER ==");

        MqttClient client = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
        client.connect();

        IntStream.range(0, 500).forEach(i -> {

            String messageString = "{\n" + "  \"udoi\":\"123\",\n" + "  \"schemaId\" : \"Air_purifier\",\n"
                    + "  \"content\":{\n" + "    \"Name\" : \"cba\",\n" + "    \"Brand\" : \"xiaomi\",\n"
                    + "    \"Speed\" : 120\n" + "  }\n" + "}";

            if (args.length == 2) {
                messageString = args[1];
            }

            MqttMessage message = new MqttMessage();
            message.setPayload(messageString.getBytes());

            try {
                client.publish("udo", message);
                System.out.println("\tMessage '" + messageString + "' to 'udo'");

                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (MqttException me) {
                me.printStackTrace();
            }
        });

        client.disconnect();

        System.out.println("== END PUBLISHER ==");

    }

}
