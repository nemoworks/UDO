package info.nemoworks.udo.model;

import com.alibaba.fastjson.JSONObject;
import org.dizitart.no2.Document;

public class UdoSchema implements IUdo{

    private String udoi;
    private JSONObject schemaContent;

    public UdoSchema(String udoi, JSONObject schemaContent) {
        this.udoi = udoi;
        this.schemaContent = schemaContent;
    }

    public UdoSchema(Document document) {
        this.udoi = document.get("udoi",String.class);
        this.schemaContent = document.get("schemaContent",JSONObject.class);
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
