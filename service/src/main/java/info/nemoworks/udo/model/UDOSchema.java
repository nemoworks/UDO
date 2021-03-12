package info.nemoworks.udo.model;

import com.alibaba.fastjson.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UDOSchema {

    private String udoi;
    private JSONObject schemaContent;

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("udoi", this.getUdoi());
        jsonObject.put("schemaContent", this.getSchemaContent());
        return jsonObject;
    }
}
