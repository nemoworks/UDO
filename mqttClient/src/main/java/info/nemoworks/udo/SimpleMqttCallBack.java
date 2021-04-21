package info.nemoworks.udo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.service.UdoService;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class SimpleMqttCallBack implements MqttCallback {

    private final UdoService udoService;

    public SimpleMqttCallBack(UdoService udoService) {
        this.udoService = udoService;
    }

    public void connectionLost(Throwable throwable) {
        System.out.println("Connection to MQTT broker lost!");
    }

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        String msg = new String(mqttMessage.getPayload());
        System.out.println("Message received:\t" + new String(mqttMessage.getPayload()));
        JSONObject jsonObject = JSON.parseObject(msg);
//        JsonObject jsonObject = JsonParser.parseString(msg).getAsJsonObject();
        String udoi = jsonObject.getString("udoi");
        String collection = jsonObject.getString("schemaId");
        JSONObject content = jsonObject.getJSONObject("content");
//        System.out.println(jsonObject.toJSONString());
        udoService.updateUdo(new Udo(udoi, collection, content), udoi);
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }
}
