package info.nemoworks.udo.model;

import com.alibaba.fastjson.JSONObject;

@Data
@AllArgsConstructor
public class Udo  implements IUdo{

    private String udoi;

    private UdoSchema schema;

    private JSONObject content;

    public UDO(UDOI udoi, UDOSchema schema) {
        this.udoi = udoi;
        this.schema = schema;
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("udoi", this.getUdoi());
        jsonObject.put("schema", this.getSchema().getUdoi());
        jsonObject.put("data", data);
        return jsonObject;
    }
}
