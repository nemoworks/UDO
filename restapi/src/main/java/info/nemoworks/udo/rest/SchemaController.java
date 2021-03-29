package info.nemoworks.udo.rest;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import graphql.ExecutionResult;
import graphql.GraphQL;
import info.nemoworks.udo.exception.UdoPersistException;
//import info.nemoworks.udo.graphql.GraphQLBuilder;
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

//    private GraphQL graphQL;
//    private GraphQLBuilder graphQlBuilder;
//
//    @Autowired
//    public SchemaController(GraphQLBuilder graphQlBuilder){
//        this.graphQL = graphQlBuilder.createGraphQl();
//        this.graphQlBuilder = graphQlBuilder;
//    }

    //    @CrossOrigin
//    @PostMapping(value = "/documents/query")
//    public ResponseEntity query(@RequestBody String query){
//        ExecutionResult result = graphQL.execute(query);
//        logger.info("errors: "+result.getErrors());
//        if(result.getErrors().isEmpty())
//            return ResponseEntity.ok(result.getData());
//        else return ResponseEntity.badRequest().body(result.getErrors());
//    }

    @GetMapping("/schemas/{collection}")
    public List<UdoSchema> allSchemas(@PathVariable String collection) {
        logger.info("find all schemas...");
        return schemaService.findAllSchemas(collection);
    }

    @PostMapping("/schemas")
    public UdoSchema createSchema(@RequestBody JSONObject params) throws UdoPersistException {
        logger.info("now saving a new schema...");
        //String udoi = params.getString("udoi");
        String name = params.getString("schemaName");
        JSONObject content = params.getJSONObject("schemaContent");
        String collection = params.getString("collection");
        UdoSchema udoSchema = new UdoSchema("udoi" + name, name, content, collection);
//        this.graphQL = graphQlBuilder.addTypeInGraphQL(udoSchema);
        return schemaService.saveSchema(udoSchema);
    }

    @DeleteMapping("/schemas/{collection}/{udoi}")
    public List<UdoSchema> deleteSchema(@PathVariable String collection, @PathVariable String udoi) throws UdoPersistException {
        logger.info("now deleting schema " + udoi + "...");
        return schemaService.deleteSchemaById(udoi, collection);
    }

    @GetMapping("/schemas/{collection}/{udoi}")
    public UdoSchema getSchemaById(@PathVariable String collection, @PathVariable String udoi) throws UdoPersistException {
        logger.info("now finding schema by udoi...");
        return schemaService.findSchemaById(udoi, collection);
    }

    @PutMapping("/schemas/{collection}/{udoi}")
    public UdoSchema updateSchema(@RequestBody JSONObject params, @PathVariable String collection, @PathVariable String udoi) throws UdoPersistException {
//        String udoi = params.getString("udoi");
        logger.info("now updating schema " + udoi + "...");
        String name = params.getString("schemaName");
        JSONObject content = params.getJSONObject("schemaContent");
        return schemaService.updateSchema(new UdoSchema(udoi, name, content, collection), udoi);
    }
}
