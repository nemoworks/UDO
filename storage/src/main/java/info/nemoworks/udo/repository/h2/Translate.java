package info.nemoworks.udo.repository.h2;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import info.nemoworks.udo.repository.h2.UTuple;

import java.util.*;

/*
Translate JSON to Tuples
 */
public class Translate {
    private List<UTuple> UTuples;
    private JSONObject jsonObject;
    private int uid;

    public Translate(JSONObject jsonObject) {
        this.UTuples = new ArrayList<>();
        this.uid = 0;
        this.jsonObject = jsonObject;
    }

    public Translate(List<UTuple> UTuples) {
        this.UTuples = UTuples;
        this.uid = 0;
        this.jsonObject = new JSONObject();
    }

    public void startTrans() {
        this.UTuples = new ArrayList<>();
        this.uid = 0;
        this.translatingObj(this.jsonObject, "");
    }

    public void startBackTrans() {
        this.jsonObject = new JSONObject();
        this.backTranslatingTuple(this.UTuples);
    }

    private void backTranslatingTuple(List<UTuple> uTuples) {
        for (UTuple uTuple: uTuples) {
            if (uTuple.getName().contains("[")) {
                if (uTuple.getName().contains(".")) {
                    if (uTuple.getName().indexOf("[") < uTuple.getName().indexOf("."))
                        backTranslatingArr();
                    else backTranslatingObj();
                }
            }
            else if (uTuple.getName().contains(".")) backTranslatingObj();
            else {
                this.jsonObject.put(uTuple.getName(), uTuple.getVal());
            }
        }
//        return this.jsonObject;
    }

    private void backTranslatingObj() {

    }

    private void backTranslatingArr() {

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
                UTuples.add(new UTuple(this.uid, suffix + dot + entry.getKey().toString(), entry.getValue().toString()));
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

    public List<UTuple> getUTuples() {
        return UTuples;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public void setUTuples(List<UTuple> UTuples) {
        this.UTuples = UTuples;
    }

    public void printTuples() {
        for (UTuple UTuple : UTuples) {
            UTuple.printTuple();
        }
    }
}
