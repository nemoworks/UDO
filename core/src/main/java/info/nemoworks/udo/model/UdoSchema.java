package info.nemoworks.udo.model;

import com.alibaba.fastjson.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class UdoSchema implements IUdo {
    private String udoi;
//    private String schemaName;
    private JSONObject schemaContent;

    public UdoSchema() {
        super();
    }

    public UdoSchema(String udoi, JSONObject schemaContent) {
        this.udoi = udoi;
        this.schemaContent = schemaContent;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("udoi", this.getUdoi());
//        jsonObject.put("schemaName", this.getSchemaName());
        jsonObject.put("schemaContent", this.getSchemaContent());
        return jsonObject;
//        Gson gson = new Gson();
//        String jStr = gson.toJson(new UdoSchema(this.udoi, this.schemaContent));
//        return JSONParser.parseString(jStr).getAsJsonObject();
    }

    @Override
    public String getUdoi() {
        return udoi;
    }

    public void setUdoi(String udoi) {
        this.udoi = udoi;
    }

//    public String getSchemaName() {
//        return this.schemaName;
//    }
//
//    public void setSchemaName(String schemaName) {
//        this.schemaName = schemaName;
//    }

    public JSONObject getSchemaContent() {
        return schemaContent;
    }

    public void setSchemaContent(JSONObject schemaContent) {
        this.schemaContent = schemaContent;
    }

}
