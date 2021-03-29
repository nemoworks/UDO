package info.nemoworks.udo.model;

import com.alibaba.fastjson.JSONObject;

public class Udo implements IUdo {

    private String udoi;

    private String schema;

    private JSONObject content;

    private String name;

    private String collection;

    public Udo() { super(); }

    public Udo(String udoi, JSONObject content) {
        this.udoi = udoi;
        this.content = content;
    }

    public Udo(String udoi, String name, String schema, JSONObject content, String collection) {
        this.udoi = udoi;
        this.schema = schema;
        this.content = content;
        this.name = name;
        this.collection = collection;
    }

    @Override
    public String getUdoi() {
        return this.udoi;
    }

    public void setUdoi(String udoi) {
        this.udoi = udoi;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public JSONObject getContent() {
        return content;
    }

    public void setContent(JSONObject content) {
        this.content = content;
    }

    public String getName() { return this.name; }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public void setName(String name) { this.name = name; }
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("udoi", this.getUdoi());
        jsonObject.put("name", name);
        jsonObject.put("schema", this.getSchema());
        jsonObject.put("data", content);
        return jsonObject;
    }
}
