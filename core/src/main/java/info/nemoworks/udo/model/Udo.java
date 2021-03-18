package info.nemoworks.udo.model;

import com.alibaba.fastjson.JSONObject;

public class Udo implements IUdo {

    private String udoi;

    private UdoSchema schema;

    private JSONObject content;

    public Udo(String udoi,JSONObject content) {
        this.udoi = udoi;
        this.content = content;
    }

    public Udo(String udoi, UdoSchema schema, JSONObject content) {
        this.udoi = udoi;
        this.schema = schema;
        this.content = content;
    }

    @Override
    public String getUdoi() {
        return this.udoi;
    }

    public void setUdoi(String udoi) {
        this.udoi = udoi;
    }

    public UdoSchema getSchema() {
        return schema;
    }

    public void setSchema(UdoSchema schema) {
        this.schema = schema;
    }

    public JSONObject getContent() {
        return content;
    }

    public void setContent(JSONObject content) {
        this.content = content;
    }
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("udoi", this.getUdoi());
        jsonObject.put("schema", this.getSchema().getUdoi());
        jsonObject.put("data", content);
        return jsonObject;
    }
}
