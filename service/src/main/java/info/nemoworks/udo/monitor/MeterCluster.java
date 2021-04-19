package info.nemoworks.udo.monitor;

import info.nemoworks.udo.graphql.schema.SchemaTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeterCluster {

    private static final Logger logger = LoggerFactory.getLogger(MeterCluster.class);

    private static final Map<String, List<String>> meterMap = new HashMap<>();

    public static void addSchemaMeter(SchemaTree schemaTree){
        logger.info("register meters in udoSchema "+schemaTree.getName()+"...");
        meterMap.put(schemaTree.getName(),schemaTree.getMeterList());
    }

    public static Map<String, List<String>> getMeterMap() {
        return meterMap;
    }
}
