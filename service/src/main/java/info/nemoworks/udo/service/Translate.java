package info.nemoworks.udo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import info.nemoworks.udo.model.Tuple;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/*
Translate JSON to Tuples
 */
public class Translate {
    private List<Tuple> tuples;
    private JSONObject jsonObject;
    private int uid;

    public Translate(JSONObject jsonObject) {
        this.tuples = new ArrayList<>();
        this.uid = 0;
        this.jsonObject = jsonObject;
    }

    public void startTrans() {
        this.tuples = new ArrayList<>();
        this.uid = 0;
        this.translatingObj(this.jsonObject, "");
    }

    private void translatingObj(JSONObject obj, String suffix) {
        String dot = ".";
        if (suffix.equals("")) dot = "";
        for (Map.Entry entry: obj.entrySet()) {
            if (entry.getValue() instanceof JSONObject) {
                translatingObj((JSONObject) entry.getValue(), suffix + dot + entry.getKey().toString());
            } else if (entry.getValue() instanceof JSONArray){
                translatingArr((JSONArray) entry.getValue(), suffix + dot + entry.getKey().toString());
            } else {
                tuples.add(new Tuple(this.uid, suffix + dot + entry.getKey().toString(), entry.getValue().toString()));
                this.uid++;
            }
        }
    }

    private void translatingArr(JSONArray arr, String suffix) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) instanceof JSONObject) {
                translatingObj((JSONObject) arr.get(i), suffix + "[" + String.valueOf(i) + "]");
            } else if (arr.get(i) instanceof JSONArray) {
                translatingArr((JSONArray) arr.get(i), suffix + "[" + String.valueOf(i) + "]");
            }
//            else {
//                tuples.add(new Tuple(this.uid, suffix + "[" + String.valueOf(i) + "]", arr.get(i).toString()));
//            }
        }
    }

//    public void addTuple() {
//
//    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public List<Tuple> getTuples() {
        return tuples;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public void setTuples(List<Tuple> tuples) {
        this.tuples = tuples;
    }

    public void printTuples() {
        for (Tuple tuple: tuples) {
            tuple.printTuple();
        }
    }
}
