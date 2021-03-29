package info.nemoworks.udo.model;

import com.alibaba.fastjson.JSONObject;

public class UdoSchema implements IUdo{
    private String udoi;
    private String schemaName;
    private JSONObject schemaContent;
    private String collection;
    public UdoSchema() {
        super();
    }

    public UdoSchema(String udoi, String schemaName, JSONObject schemaContent, String collection) {
        this.udoi = udoi;
        this.schemaName = schemaName;
        this.schemaContent = schemaContent;
        this.collection = collection;
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

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }
}
