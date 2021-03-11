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
public class UDOSchema {
    private String id; // 唯一标识符
    private JSONObject schemaContent;



    public JSONObject toJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", this.getId());
        jsonObject.put("schemaContent", this.getSchemaContent());
        return jsonObject;
    }
}
