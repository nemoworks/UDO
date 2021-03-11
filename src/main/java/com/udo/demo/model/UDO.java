package com.udo.demo.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.dizitart.no2.objects.Id;

@Data
@AllArgsConstructor
public class UDO {
    @Id
    private String id;

    private String schemaId;

    private JSONObject data;

    public UDO() {
    }

//    public UDO(String schemaId, String collectionName, JSONObject data) {
//        this.schemaId = schemaId;
////        this.collectionName = collectionName;
//        this.data = data;
//    }

//    @Override
//    public String toString() {
//        return String.format(
//                "{id:'%s', schemaId:'%s',collectionName:'%s', data:'%s',status:'%s'}",
//                id, schemaId, JSON.toJSONString(data));
//    }

    public JSONObject toJSONObject() {
        return JSONObject.parseObject(this.toString());
    }
}
