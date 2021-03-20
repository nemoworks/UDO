package info.nemoworks.udo.graphql.schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;

import graphql.language.ListType;
import graphql.language.Type;
import graphql.language.TypeName;
import info.nemoworks.udo.model.Link;

public class SchemaTree {

    private String name;

    private Map<String, Type> typeMap = new HashMap<>();

    private Map<String, SchemaTree> childSchemas = new HashMap<>();

    private Map<String, Type> inputMap = new HashMap<>();

    private Map<String, Type> filterMap = new HashMap<>();

    private List<Link> linkList = new ArrayList<>();

    public Map<String, Type> getInputMap() {
        return inputMap;
    }

    public void setInputMap(Map<String, Type> inputMap) {
        this.inputMap = inputMap;
    }

    public Map<String, Type> getFilterMap() {
        return filterMap;
    }

    public void setFilterMap(Map<String, Type> filterMap) {
        this.filterMap = filterMap;
    }

    @Override
    public String toString() {
        return "SchemaTree{" + "typeMap=" + typeMap + ", childSchemas=" + childSchemas + ", linkList=" + linkList + '}';
    }

    public SchemaTree() {
        typeMap.put("id", new TypeName("String"));
        filterMap.put("schemaId", new TypeName("String"));
    }

    public SchemaTree createSchemaTree(JsonObject schema) {
        SchemaTree schemaTree = new SchemaTree();
        JsonObject properties = schema.getAsJsonObject("properties");
        schemaTree.name = schema.get("name").getAsString();
        if (properties != null) {
            HashMap<String, LinkedTreeMap> hashMap = new Gson().fromJson(properties.toString(), HashMap.class);
            hashMap.forEach((key, value1) -> {
                SchemaPropertyType typeName = SchemaPropertyType.valueOf(value1.get("type").toString());
                switch (typeName) {
                case Link:
                    String typeName1 = value1.get("linkTo").toString();
                    schemaTree.linkList.add(new Link(key, "Link"));
                    schemaTree.typeMap.put(key, new TypeName(typeName1));
                    schemaTree.inputMap.put(key, new TypeName("String"));
                    break;
                case LinkList:
                    String typeName2 = value1.get("linkTo").toString();
                    schemaTree.linkList.add(new Link(key, "LinkList"));
                    schemaTree.typeMap.put(key, new ListType(new TypeName(typeName2)));
                    schemaTree.inputMap.put(key, new ListType(new TypeName("String")));
                    break;
                case Embedded:
                    schemaTree.typeMap.put(key, new TypeName(value1.get("typeName").toString()));
                    schemaTree.inputMap.put(key, new TypeName(
                            new GraphQLPropertyConstructor(value1.get("typeName").toString()).inputKeyWordInQuery()));
                    break;
                case EmbeddedList:
                    schemaTree.typeMap.put(key, new ListType(new TypeName(value1.get("typeName").toString())));
                    schemaTree.inputMap.put(key, new ListType(new TypeName(
                            new GraphQLPropertyConstructor(value1.get("typeName").toString()).inputKeyWordInQuery())));
                    break;
                case number:
                    schemaTree.typeMap.put(key, new TypeName("Int"));
                    schemaTree.inputMap.put(key, new TypeName("Int"));
                    schemaTree.filterMap.put(key, new TypeName("Int"));
                    break;
                case object:
                    JsonObject jsonObject = new Gson().toJsonTree(value1).getAsJsonObject();
                    schemaTree.typeMap.put(key, new TypeName(jsonObject.get("name").getAsString()));
                    schemaTree.inputMap.put(key,
                            new TypeName(new GraphQLPropertyConstructor(jsonObject.get("name").getAsString())
                                    .inputKeyWordInQuery()));
                    schemaTree.filterMap.put(key,
                            new TypeName(new GraphQLPropertyConstructor(jsonObject.get("name").getAsString())
                                    .filterKeyWordInQueryXxlist()));
                    schemaTree.childSchemas.put(key, this.createSchemaTree(jsonObject));
                    break;
                case array:
                    schemaTree.typeMap.put(key, new ListType(new TypeName("String")));
                    schemaTree.inputMap.put(key, new ListType(new TypeName("String")));
                    schemaTree.filterMap.put(key, new ListType(new TypeName("String")));
                    break;
                default:
                    schemaTree.typeMap.put(key, new TypeName("String"));
                    schemaTree.inputMap.put(key, new TypeName("String"));
                    schemaTree.filterMap.put(key, new TypeName("String"));
                }

            });
        }
        return schemaTree;
    }

    public void traceSchemaTree(SchemaTree schemaTree) {
        schemaTree.childSchemas.forEach((key, value) -> {
            traceSchemaTree(value);
            System.out.println(key);
        });
        schemaTree.typeMap.forEach((key, value) -> {
            System.out.println(key + "   " + value);
        });
    }

    public Map<String, SchemaTree> getChildSchemas() {
        return childSchemas;
    }

    public void setChildSchemas(Map<String, SchemaTree> childSchemas) {
        this.childSchemas = childSchemas;
    }

    public List<Link> getLinkList() {
        return linkList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLinkList(List<Link> linkList) {
        this.linkList = linkList;
    }

    public Map<String, Type> getTypeMap() {
        return typeMap;
    }

    public void setTypeMap(Map<String, Type> typeMap) {
        this.typeMap = typeMap;
    }
}