package info.nemoworks.udo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.service.UdoService;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;

public class SimpleMqttCallBack implements MqttCallback {

    @Autowired
    UdoService udoService;

    public void connectionLost(Throwable throwable) {
        System.out.println("Connection to MQTT broker lost!");
    }

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        String msg = new String(mqttMessage.getPayload());
        JSONObject jsonObject = JSON.parseObject(s);
        String udoi = jsonObject.getString("udoi");
        String collection = jsonObject.getString("schemaId");
        JSONObject content = jsonObject.getJSONObject("content");
        udoService.updateUdo(new Udo(udoi,collection,content),udoi);
        System.out.println("Message received:\t"+ new String(mqttMessage.getPayload()) );
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }
}
