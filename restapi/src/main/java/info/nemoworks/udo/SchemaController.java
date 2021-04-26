package info.nemoworks.udo;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import graphql.ExecutionResult;
import graphql.GraphQL;
import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.graphql.GraphQLBuilder;
import info.nemoworks.udo.graphql.schema.SchemaTree;
import info.nemoworks.udo.monitor.MeterCluster;
import info.nemoworks.udo.repository.h2.exception.UDROSchemaPersistException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.service.UdoSchemaService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class SchemaController {

    private static final Logger logger = LoggerFactory.getLogger(SchemaController.class);

    private final UdoSchemaService schemaService;

//    @Autowired
//    private Publisher publisher;

    private GraphQL graphQL;
    private GraphQLBuilder graphQlBuilder;

    @Autowired
    public SchemaController(GraphQLBuilder graphQlBuilder, UdoSchemaService schemaService){
        this.graphQL = graphQlBuilder.createGraphQl();
        this.graphQlBuilder = graphQlBuilder;
        this.schemaService = schemaService;
    }

    @CrossOrigin
    @PostMapping(value = "/documents/query")
    public ResponseEntity query(@RequestBody String query){
        ExecutionResult result = graphQL.execute(query);
        logger.info("errors: "+result.getErrors());
//        try {
//            publisher.publishUdo(query);
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
        if(result.getErrors().isEmpty())
            return ResponseEntity.ok(result.getData());
        else return ResponseEntity.badRequest().body(result.getErrors());
    }

    @GetMapping("/schemas")
    public List<UdoSchema> allSchemas() {
        logger.info("find all schemas...");
//        Gson gson = new Gson();
        return schemaService.findAllSchemas();
    }

    @PostMapping("/schemas")
    public UdoSchema createSchema(@RequestBody JSONObject params) throws UdoPersistException, UDROSchemaPersistException {
        logger.info("now saving a new schema...");
//        System.out.println(params);
        String name = params.getString("schemaName");
        JSONObject content = params.getJSONObject("schemaContent");
//        Gson gson = new Gson();
//        JsonObject param = JsonParser.parseString(params).getAsJsonObject();
//        String name = param.get("schemaName").getAsString();
//        JsonObject content = param.get("schemaContent").getAsJsonObject();

        UdoSchema udoSchema = new UdoSchema(name, content);
//        System.out.println(udoSchema.toJson());
        SchemaTree schemaTree = new SchemaTree().createSchemaTree( new Gson()
                .fromJson(udoSchema.getSchemaContent().toString(), JsonObject.class));

        this.graphQL = graphQlBuilder.addTypeInGraphQL(schemaTree);
        MeterCluster.addSchemaMeter(schemaTree);
        return schemaService.saveSchema(udoSchema);
    }

    @DeleteMapping("/schemas/{udoi}")
    public List<UdoSchema> deleteSchema(@PathVariable String udoi) throws UdoPersistException, UDROSchemaPersistException {
        logger.info("now deleting schema " + udoi + "...");
//        Gson gson = new Gson();
        return schemaService.deleteSchemaById(udoi);
    }

    @GetMapping("/schemas/{udoi}")
    public UdoSchema getSchemaById(@PathVariable String udoi) throws UdoPersistException, UDROSchemaPersistException {
        logger.info("now finding schema by udoi...");
//        Gson gson = new Gson();
        return schemaService.findSchemaById(udoi);
    }

    @PutMapping("/schemas/{udoi}")
    public UdoSchema updateSchema(@RequestBody JSONObject params, @PathVariable String udoi) throws UdoPersistException, UDROSchemaPersistException {
//        String udoi = params.getString("udoi");
        logger.info("now updating schema " + udoi + "...");
//        String name = params.get("schemaName").getAsString();
        JSONObject content = params.getJSONObject("schemaContent");
//        Gson gson = new Gson();
        return schemaService.updateSchema(new UdoSchema(udoi, content), udoi);
    }
}
