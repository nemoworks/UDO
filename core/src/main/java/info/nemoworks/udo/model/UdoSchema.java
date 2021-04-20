package info.nemoworks.udo.model;

import com.alibaba.fastjson.JSONObject;

public class UdoSchema implements IUdo {
    private String udoi;
    private String schemaName;
    private JSONObject schemaContent;

    public UdoSchema() {
        super();
    }

    public UdoSchema(String udoi, String schemaName, JSONObject schemaContent) {
        this.udoi = udoi;
        this.schemaName = schemaName;
        this.schemaContent = schemaContent;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("udoi", this.getUdoi());
        jsonObject.put("schemaName", this.getSchemaName());
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

    public String getSchemaName() {
        return this.schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public JSONObject getSchemaContent() {
        return schemaContent;
    }

    public void setSchemaContent(JSONObject schemaContent) {
        this.schemaContent = schemaContent;
    }

}
