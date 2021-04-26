package info.nemoworks.udo.model;


import net.sf.json.JSONObject;

public class Udo implements IUdo {

    private String udoi;

    private String schemaId;

    private JSONObject content;

    public Udo() {
        super();
    }

    public Udo(String udoi, JSONObject content) {
        this.udoi = udoi;
        this.content = content;
    }

    public Udo(String udoi, String schemaId, JSONObject content) {
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

    public JSONObject getContent() {
        return content;
    }

    public void setContent(JSONObject content) {
        this.content = content;
    }

    public String getSchemaId() {
        return schemaId;
    }

    public void setSchemaId(String schemaId) {
        this.schemaId = schemaId;
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("udoi", this.getUdoi());
        jsonObject.put("schemaId", this.getSchemaId());
        jsonObject.put("data", content);
        return jsonObject;
//        Gson gson = new Gson();
//        String jStr = gson.toJson(new Udo(this.udoi, this.schemaId, this.content));
//        return JsonParser.parseString(jStr).getAsJsonObject();
    }
}
