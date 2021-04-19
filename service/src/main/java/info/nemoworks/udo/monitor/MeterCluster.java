package info.nemoworks.udo.monitor;

import info.nemoworks.udo.graphql.schema.SchemaTree;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeterCluster {
    private static Map<String, List<String>> meterMap = new HashMap<>();

    public static void addSchemaMeter(SchemaTree schemaTree){
        meterMap.put(schemaTree.getName(),schemaTree.getMeterList());
    }

    public static Map<String, List<String>> getMeterMap() {
        return meterMap;
    }
}
