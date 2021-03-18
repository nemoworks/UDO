package info.nemoworks.udo.model;

import com.alibaba.fastjson.JSONObject;
import org.dizitart.no2.Document;
import org.dizitart.no2.objects.Id;

public class UdoSchema implements IUdo{
    @Id
    private String udoi;
    private JSONObject schemaContent;

    public UdoSchema(String udoi, JSONObject schemaContent) {
        this.udoi = udoi;
        this.schemaContent = schemaContent;
    }

    public UdoSchema(JSONObject schemaContent) {
        this.schemaContent = schemaContent;
    }

    public UdoSchema(Document document) {
        this.udoi = document.get("udoi",String.class);
        this.schemaContent = document.get("schemaContent",JSONObject.class);
    }

    @Override
    public String toString() {
        return "UdoSchema{" +
                "udoi='" + udoi + '\'' +
                ", schemaContent=" + schemaContent +
                '}';
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("udoi", this.getUdoi());
        jsonObject.put("schemaContent", this.getSchemaContent());
        return jsonObject;
    }

    @Override
    public String getUdoi() {
        return udoi;
    }

    public void setUdoi(String udoi) {
        this.udoi = udoi;
    }

    public JSONObject getSchemaContent() {
        return schemaContent;
    }

    public void setSchemaContent(JSONObject schemaContent) {
        this.schemaContent = schemaContent;
    }
}
