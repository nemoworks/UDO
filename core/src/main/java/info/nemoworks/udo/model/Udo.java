package info.nemoworks.udo.model;

//import com.alibaba.fastjson.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Udo implements IUdo {

    private String udoi;

    private String schemaId;

    private JsonObject content;

    public Udo() {
        super();
    }

    public Udo(String udoi, JsonObject content) {
        this.udoi = udoi;
        this.content = content;
    }

    public Udo(String udoi, String schemaId, JsonObject content) {
        this.udoi = udoi;
        this.schemaId = schemaId;
        this.content = content;
    }

    @Override
    public String getUdoi() {
        return this.udoi;
    }

    public void setUdoi(String udoi) {
        this.udoi = udoi;
    }

    public JsonObject getContent() {
        return content;
    }

    public void setContent(JsonObject content) {
        this.content = content;
    }

    public String getSchemaId() {
        return schemaId;
    }

    public void setSchemaId(String schemaId) {
        this.schemaId = schemaId;
    }

    public JsonObject toJSON() {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("udoi", this.getUdoi());
//        jsonObject.put("schemaId", this.getSchemaId());
//        jsonObject.put("data", content);
        Gson gson = new Gson();
        String jStr = gson.toJson(new Udo(this.udoi, this.schemaId, this.content));
        return JsonParser.parseString(jStr).getAsJsonObject();
    }
}
