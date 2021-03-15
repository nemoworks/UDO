package info.nemoworks.udo.model;

import com.alibaba.fastjson.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Udo implements IUdo {

    private String udoi;

    private UdoSchema schema;

    private JSONObject content;

    @Override
    public String getUdoi() {
        return this.udoi;
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("udoi", this.getUdoi());
        jsonObject.put("schema", this.getSchema().getUdoi());
        jsonObject.put("data", content);
        return jsonObject;
    }
}
