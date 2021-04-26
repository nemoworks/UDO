package info.nemoworks.udo.repository.h2.manager;

import info.nemoworks.udo.repository.h2.model.UTuple;
//import javafx.util.Pair;
import java.util.AbstractMap.SimpleEntry;
//import org.json.JSONObject;
import net.sf.json.*;
import java.util.*;

/*
Translate JSON to Tuples
 */
public class Translate {
    private List<UTuple> UTuples;
    private JSONObject JsonObject;
    private int uid;

    public Translate(JSONObject JsonObject) {
        this.UTuples = new ArrayList<>();
        this.uid = 0;
        this.JsonObject = JsonObject;
    }

    public Translate(List<UTuple> UTuples) {
        this.UTuples = UTuples;
        this.uid = 0;
        this.JsonObject = new JSONObject();
    }

    public void startTrans() {
        this.UTuples = new ArrayList<>();
        this.uid = 0;
        this.translatingObj(this.JsonObject, "");
    }

    public void startBackTrans() {
        this.JsonObject = new JSONObject();
        this.backTranslatingTuple(this.UTuples);
    }

    private void backTranslatingTuple(List<UTuple> uTuples) {
        for (UTuple uTuple: uTuples) {
            if (uTuple.getName().contains("[")) { // 当前Tuple为JsonArray
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
//        return this.JsonObject;
    }

    private void backTranslatingEle(UTuple uTuple) {
        this.JsonObject.put(uTuple.getName(), uTuple.getVal());
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
//            System.out.println("Now prefix: " + pPrefix);
            sb = new StringBuffer(pPrefix);
            reverse = sb.reverse().toString();
            endIndex = pPrefix.length() - reverse.indexOf(".");
            if (!reverse.contains(".")) endIndex = 0; // 处理到根节点的情况
            objName = pPrefix.substring(endIndex);
            if (endIndex == 0) pPrefix = pPrefix.substring(0, 0);
            else pPrefix = pPrefix.substring(0, endIndex - 1);
            JSONObject nObj = new JSONObject();
            nObj.put(objName, obj);
            obj = nObj;
            nameStack.push(objName);
            objectStack.push(obj);
        }
        if (!this.JsonObject.containsKey(objName)) {
//            System.out.println("add obj" + objName);
//            System.out.println("obj" + obj.getJsonObject(objName));
            this.JsonObject.put(objName, obj.getJSONObject(objName));
        }
        else {
            JSONObject fatherObj = this.JsonObject.getJSONObject(objName); //获取JsonObject中已经存在的obj
//            nameStack.pop(); // 去掉栈顶，即尾部名称
//            objectStack.pop();
            JSONObject lObj = new JSONObject();
            lObj.put(objName, fatherObj);
//            System.out.println("pushing: " + objName + "into" + packUpObj(fatherObj, nameStack, objectStack));
//            System.out.println("before: " + this.JsonObject);
//            System.out.println("father: " + fatherObj);
            JSONObject obj2put = packUpObj(lObj, nameStack, objectStack);
//            System.out.println("obj2" + obj2put);
            this.JsonObject.put(objName, obj2put);
//            System.out.println(objName);
//            System.out.println(this.JsonObject);
//            String curName = nameStack.pop();
//            JsonObject curObj = objectStack.pop();
//            while (fatherObj.containsKey(curName)) {
//                fatherObj = fatherObj.getJsonObject(curName);
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
        JSONObject curObj = objStack.pop();
        if (!fatherObj.containsKey(curName)) {
            if (curObj.get(curName) instanceof String)
                fatherObj.put(curName, curObj.getString(curName));
            else fatherObj.put(curName, curObj.get(curName));
            return fatherObj;
        } else {
            return (JSONObject) fatherObj.put(curName, packUpObj(fatherObj.getJSONObject(curName), nameStack, objStack));
//            return fatherObj;
        }
    }
    private void backTranslatingArr(UTuple uTuple, String prefix) {
        if (prefix.equals("")) return;
        StringBuffer sb = new StringBuffer(prefix);
        String reverse = sb.reverse().toString();
        int endIndex = prefix.length() - reverse.indexOf(".");
        String objName = prefix.substring(endIndex);
        JSONObject obj = new JSONObject();
        obj.put(objName, uTuple.getVal());
        String pPrefix = prefix.substring(0, endIndex - 1);
        Stack<String> nameStack = new Stack<>();
        Stack<SimpleEntry<JSONObject, String>> objectStack = new Stack<>();
        nameStack.push(objName);
        objectStack.push(new SimpleEntry<>(obj, "Object"));
        // 考虑 Object 与 Array 互相嵌套的 5 种情况
        while (!pPrefix.equals("") && !pPrefix.equals(pPrefix.substring(0, 0))) {
            sb = new StringBuffer(pPrefix);
            reverse = sb.reverse().toString();
            int indexArr = reverse.indexOf("]");
            int indexLeftArr = reverse.indexOf("[");
            int indexDot = reverse.indexOf(".");
            if (indexArr == -1 && indexDot == -1) { // 根节点 形如a
                String ObjName = pPrefix;
                nameStack.push(ObjName);
                pPrefix = pPrefix.substring(0, 0);
                JSONObject JsonObject = new JSONObject();
                JsonObject.put(ObjName, obj);
                objectStack.push(new SimpleEntry<>(JsonObject, "Object"));
                obj = JsonObject;
                objName = ObjName;
            } else if (indexDot == -1) { // 根节点，形如a[x]
                String ArrName = pPrefix.substring(0, pPrefix.indexOf("["));
                nameStack.push(ArrName);
                pPrefix = pPrefix.substring(0, 0);
                JSONArray jsonArray = new JSONArray();
                jsonArray.add(obj);
//                objectStack.push(new Pair<>(JsonArray, "Array"));
                obj = new JSONObject();
                objName = ArrName;
                obj.put(objName, jsonArray);
//                obj = JsonArray;
                objectStack.push(new SimpleEntry<>(obj, "Array"));
            } else if (indexArr == -1) { // 形如 a.b.c
                endIndex = pPrefix.length() - reverse.indexOf(".");
                String ObjName = pPrefix.substring(endIndex);
                pPrefix = pPrefix.substring(0, endIndex - 1);
                JSONObject JsonObject = new JSONObject();
                nameStack.push(ObjName);
                JsonObject.put(ObjName, obj);
                objectStack.push(new SimpleEntry<>(JsonObject, "Object"));
                obj = JsonObject;
                objName = ObjName;
            } else if (indexArr < indexDot) { //形如 c.a[x]
                endIndex = pPrefix.length() - indexDot;
                String ArrName = pPrefix.substring(endIndex, pPrefix.length() - indexLeftArr - 1);
                pPrefix = pPrefix.substring(0, endIndex - 1);
                nameStack.push(ArrName);
                JSONArray JsonArray = new JSONArray();
                JsonArray.add(obj);
//                objectStack.push(new Pair<>(JsonArray, "Array"));
                obj = new JSONObject();
                objName = ArrName;
                ((JSONObject)obj).put(objName, JsonArray);
//                obj = JsonArray;
                objName = ArrName;
                objectStack.push(new SimpleEntry<>(obj, "Array"));
//                System.out.println("c.a[x]: " + ArrName + " " + obj);
            } else { //形如 a[x].c
                endIndex = pPrefix.length() - indexArr + 1;
                String ObjName = pPrefix.substring(endIndex);
                pPrefix = pPrefix.substring(0, endIndex - 1);
                nameStack.push(ObjName);
                JSONObject JsonObject = new JSONObject();
                JsonObject.put(ObjName, obj);
                objectStack.push(new SimpleEntry<>(JsonObject, "Object"));

                obj = JsonObject;
                objName = ObjName;
            }
        }
        if (!this.JsonObject.containsKey(objName)) {
            this.JsonObject.put(objName, ((JSONObject) obj).get(objName));
        } else {
            Object fatherObj = this.JsonObject.get(objName);
//            nameStack.pop();
//            objectStack.pop();
            JSONObject lObj = new JSONObject();
            lObj.put(objName, fatherObj);
//            System.out.println("lObj: " + lObj);
//            System.out.println("objName: " + objName);
//            System.out.println("jsonObj: " + this.JsonObject);
//            System.out.println("father before pack: " + fatherObj);
            JSONObject obj2put = packUpArray(lObj, nameStack, objectStack);
            this.JsonObject.put(objName, obj2put.get(objName));
        }
    }

    private JSONObject packUpArray(JSONObject fatherObj, Stack<String> nameStack, Stack<SimpleEntry<JSONObject, String>> objStack) {
        String curName = nameStack.pop();
        SimpleEntry<JSONObject, String> curPair = objStack.pop();
        String curType = curPair.getValue();
        if (curType.equals("Object")) { // 当前节点为JsonObject
            if (!(fatherObj).containsKey(curName)) { // 节点中不包含此层定义，说明是新加入的内容，直接添加并返回
                JSONObject curObj = curPair.getKey();
//                if (curObj instanceof JsonObject)
//                    ((JsonObject) fatherObj).put(curName, ((JsonObject) curObj).get(curName));
//                else if (curObj instanceof JsonArray)
//                    ((JsonObject) fatherObj).put(curName, curObj);
                fatherObj.put(curName, curObj.get(curName));
                return fatherObj;
            } else { // 向下递归
                fatherObj.put(curName, packUpArray(fatherObj.getJSONObject(curName), nameStack, objStack));
                return fatherObj;
            }
        }
        else { // 当前节点为JsonArray
            JSONObject curObj = curPair.getKey();
            boolean exist = false;
            JSONObject switchObj = new JSONObject();
            int index = 0;
            String nextName;
//            System.out.println("fatherObj: " + fatherObj);
//            System.out.println("curName: " + curName);
            for (int i = 0; i < ((JSONArray) fatherObj.get(curName)).size(); i++) { // JsonArray的数组元素只可能是JsonObject
                // 因为就算是Array嵌套Array，也必定会经过一个a:[{b:[{}]}]的形式
                switchObj = (JSONObject) ((JSONArray) fatherObj.get(curName)).get(i);
//                System.out.println("father: " + fatherObj);
                nextName = nameStack.pop();
                nameStack.push(nextName);
//                System.out.println("nextName: " + nextName);
                if (((JSONObject) switchObj).containsKey(nextName)) {
                    exist = true;
                    index = i;
                    break;
                }
            }
            if (!exist) { // fatherObj代表的Array种不包含与当前处理的Obj节点同名的数组元素，则直接将当前节点加入Array
                ((JSONArray) fatherObj.get(curName)).add(curObj.get(curName));
//                if (curObj instanceof JsonObject) {
//                    JsonObject obj = new JsonObject();
//                    obj.put(curName, ((JsonObject) curObj).get(curName));
//                    ((JsonArray) fatherObj).add(obj);
//                } else if (curObj instanceof JsonArray) {
//                    JsonObject obj = new JsonObject();
//                    obj.put(curName, curObj);
//                    ((JsonArray) fatherObj).add(obj);
//                }
                return fatherObj;
            } else { // fatherObj所代表的Array中存在已有定义，向下递归
                ((JSONArray) fatherObj.get(curName)).set(index, packUpArray(switchObj, nameStack, objStack));
                return fatherObj;
            }
        }
    }

//    private boolean arrayContainsObj(JsonArray array) {
//        boolean exist = false;
//        for (Object obj: array) {
//            if (obj instanceof JsonObject) {
//                if (((JsonObject) obj).containsKey()) {
//                    exist = true;
//                    break;
//                }
//            } else if (obj instanceof JsonArray) {
//
//            }
//        }
//    }

    private void translatingObj(JSONObject obj, String suffix) {
        String dot = ".";
        if (suffix.equals("")) dot = "";
        for (Object attr: obj.entrySet()) {
            Map.Entry entry = (Map.Entry) attr;
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
        return JsonObject;
    }

    public List<UTuple> getUTuples() {
        return UTuples;
    }

    public void setJsonObject(JSONObject JsonObject) {
        this.JsonObject = JsonObject;
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
