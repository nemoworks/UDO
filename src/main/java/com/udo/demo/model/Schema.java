package com.udo.demo.model;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schema {
    private String id; // 唯一标识符
    private String schemaType; // Schema类型 如：air-purifier
    private JSONObject schemaContent;
    private List<MetaType> typeList;
    private Status status;

    public Schema(String id, List<MetaType> typeList, String schemaType) {
        this.id = id;
        this.schemaType = schemaType;
        this.typeList = typeList;
    }

    public Schema(String id, JSONObject schemaContent, String schemaType) {
        this.id = id;
        this.schemaType = schemaType;
        this.schemaContent = schemaContent;
    }

    public JSONObject toJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", this.getId());
        jsonObject.put("schemaContent", this.getSchemaContent());
        jsonObject.put("status", this.getStatus());

        return jsonObject;
    }

    /**
     * TODO: 将元层Type组合成Schema的JSON对象
     */
    public void metaType2Json() {

    }
}
