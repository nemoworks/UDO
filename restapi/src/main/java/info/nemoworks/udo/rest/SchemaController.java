package info.nemoworks.udo.rest;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import graphql.ExecutionResult;
import graphql.GraphQL;
import info.nemoworks.udo.Publisher;
import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.graphql.GraphQLBuilder;
import info.nemoworks.udo.graphql.schema.SchemaTree;
import info.nemoworks.udo.monitor.MeterCluster;
import org.eclipse.paho.client.mqttv3.MqttException;
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

    @Autowired
    private UdoSchemaService schemaService;

    @Autowired
    private Publisher publisher;

    private GraphQL graphQL;
    private GraphQLBuilder graphQlBuilder;

    @Autowired
    public SchemaController(GraphQLBuilder graphQlBuilder){
        this.graphQL = graphQlBuilder.createGraphQl();
        this.graphQlBuilder = graphQlBuilder;
    }

    @CrossOrigin
    @PostMapping(value = "/documents/query")
    public ResponseEntity query(@RequestBody String query){
        ExecutionResult result = graphQL.execute(query);
        logger.info("errors: "+result.getErrors());
        try {
            publisher.publishUdo(query);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        if(result.getErrors().isEmpty())
            return ResponseEntity.ok(result.getData());
        else return ResponseEntity.badRequest().body(result.getErrors());
    }

    @GetMapping("/schemas")
    public List<UdoSchema> allSchemas() {
        logger.info("find all schemas...");
        return schemaService.findAllSchemas();
    }

    @PostMapping("/schemas")
    public UdoSchema createSchema(@RequestBody JSONObject params) throws UdoPersistException {
        logger.info("now saving a new schema...");
        //String udoi = params.getString("udoi");
        String name = params.getString("schemaName");
        JSONObject content = params.getJSONObject("schemaContent");
//        String collection = params.getString("collection");
        UdoSchema udoSchema = new UdoSchema(name, name, content);
        SchemaTree schemaTree = new SchemaTree().createSchemaTree( new Gson()
                .fromJson(udoSchema.getSchemaContent().toString(), JsonObject.class));
        this.graphQL = graphQlBuilder.addTypeInGraphQL(schemaTree);
        MeterCluster.addSchemaMeter(schemaTree);
        return schemaService.saveSchema(udoSchema);
    }

    @DeleteMapping("/schemas/{udoi}")
    public List<UdoSchema> deleteSchema(@PathVariable String udoi) throws UdoPersistException {
        logger.info("now deleting schema " + udoi + "...");
        return schemaService.deleteSchemaById(udoi);
    }

    @GetMapping("/schemas/{udoi}")
    public UdoSchema getSchemaById(@PathVariable String udoi) throws UdoPersistException {
        logger.info("now finding schema by udoi...");
        return schemaService.findSchemaById(udoi);
    }

    @PutMapping("/schemas/{udoi}")
    public UdoSchema updateSchema(@RequestBody JSONObject params, @PathVariable String udoi) throws UdoPersistException {
//        String udoi = params.getString("udoi");
        logger.info("now updating schema " + udoi + "...");
        String name = params.getString("schemaName");
        JSONObject content = params.getJSONObject("schemaContent");
        return schemaService.updateSchema(new UdoSchema(udoi, name, content), udoi);
    }
}
