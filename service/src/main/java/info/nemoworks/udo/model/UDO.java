package info.nemoworks.udo.model;

import com.alibaba.fastjson.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UDO {

    private String udoi;

    private UDOSchema schema;

    private JSONObject data;

    public UDO() {
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("udoi", this.getUdoi());
        jsonObject.put("schema", this.getSchema().getUdoi());
        jsonObject.put("data", data);
        return jsonObject;
    }
}
