package info.nemoworks.udo.repository.h2;

import com.alibaba.fastjson.JSON;
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
            if (uTuple.getName().contains("[")) { // 当前Tuple为JSONArray
//                StringBuffer sb = new StringBuffer(uTuple.getName());
//                String reverse = sb.reverse().toString();
                backTranslatingArr(uTuple, uTuple.getName());
            }
            // 当前Tuple为纯Obj
            else if (uTuple.getName().contains(".")) backTranslatingObj(uTuple, uTuple.getName());
            // 当前Tuple为简单Element
            else {
                backTranslatingEle(uTuple);
            }
        }
//        return this.jsonObject;
    }

    private void backTranslatingEle(UTuple uTuple) {
        this.jsonObject.put(uTuple.getName(), uTuple.getVal());
    }

    private void backTranslatingObj(UTuple uTuple, String prefix) {
//        String name = uTuple.getName();
        if (prefix.equals("")) return;
        StringBuffer sb = new StringBuffer(prefix);
        String reverse = sb.reverse().toString();
        int endIndex = prefix.length() - reverse.indexOf(".");
        String objName = prefix.substring(endIndex);
        JSONObject obj = new JSONObject();
        obj.put(objName, uTuple.getVal());
        String pPrefix = prefix.substring(0, endIndex - 1);
        Stack<String> nameStack = new Stack<>();
        Stack<JSONObject> objectStack = new Stack<>();
        nameStack.push(objName);
        objectStack.push(obj);
        while (!pPrefix.equals("") && !pPrefix.equals(pPrefix.substring(0, 0))) {
            System.out.println("Now prefix: " + pPrefix);
            sb = new StringBuffer(pPrefix);
            reverse = sb.reverse().toString();
            endIndex = pPrefix.length() - reverse.indexOf(".");
            if (!reverse.contains(".")) endIndex = 0;
            objName = pPrefix.substring(endIndex);
            if (endIndex == 0) pPrefix = pPrefix.substring(0, 0);
            else pPrefix = pPrefix.substring(0, endIndex - 1);
            JSONObject nObj = new JSONObject();
            nObj.put(objName, obj);
            obj = nObj;
            nameStack.push(objName);
            objectStack.push(obj);
        }
        if (!this.jsonObject.containsKey(objName)) {
//            System.out.println("add obj" + objName);
//            System.out.println("obj" + obj.getJSONObject(objName));
            this.jsonObject.put(objName, obj.getJSONObject(objName));
        }
        else {
            JSONObject fatherObj = this.jsonObject.getJSONObject(objName); //获取jsonObject中已经存在的obj
            nameStack.pop(); // 去掉栈顶，即尾部名称
            objectStack.pop();
//            System.out.println("pushing: " + objName + "into" + packUpObj(fatherObj, nameStack, objectStack));
//            System.out.println("before: " + this.jsonObject);
//            System.out.println("father: " + fatherObj);
            JSONObject obj2put = packUpObj(fatherObj, nameStack, objectStack);
//            System.out.println("obj2" + obj2put);
            this.jsonObject.put(objName, obj2put);
//            System.out.println(objName);
//            System.out.println(this.jsonObject);
//            String curName = nameStack.pop();
//            JSONObject curObj = objectStack.pop();
//            while (fatherObj.containsKey(curName)) {
//                fatherObj = fatherObj.getJSONObject(curName);
//                curName = nameStack.pop();
//                curObj = objectStack.pop();
////                if (fatherObj.entrySet().contains(curName))
//            }
//            fatherObj.put(curName, curObj);
        }
    }

    /**
     * 递归，自下而上搭建新的JSONObj
     * @param fatherObj 上层节点
     * @param nameStack 存储节点name
     * @param objStack 存储下层节点内容
     * @return 填充完成的Obj
     */
    private JSONObject packUpObj(JSONObject fatherObj, Stack<String> nameStack, Stack<JSONObject> objStack) {
        String curName = nameStack.pop();
//        System.out.println("cname: " + curName);
        JSONObject curObj = objStack.pop();
//        System.out.println("cobj: " + curObj);
        if (!fatherObj.containsKey(curName)) {
            if (curObj.get(curName) instanceof String)
                fatherObj.put(curName, curObj.getString(curName));
            else fatherObj.put(curName, curObj.get(curName));
            return fatherObj;
        }
        else return (JSONObject) fatherObj.put(curName, packUpObj(fatherObj.getJSONObject(curName), nameStack, objStack));
    }

    private void backTranslatingArr(UTuple uTuple, String prefix) {
        if (prefix.equals(uTuple.getName())) {
            String cStr = prefix.substring(0, prefix.indexOf("["));
        }
//        String name = uTuple.getName();
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
