package info.nemoworks.udo.rest;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import graphql.ExecutionResult;
import graphql.GraphQL;
import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.graphql.GraphQLBuilder;
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

    private GraphQL graphQL;
    private GraphQLBuilder graphQlBuilder;

    @Autowired
    public SchemaController(GraphQLBuilder graphQlBuilder){
        this.graphQL = graphQlBuilder.createGraphQl();
        this.graphQlBuilder = graphQlBuilder;
    }

    //    @CrossOrigin
    @PostMapping(value = "documents/query")
    public ResponseEntity query(@RequestBody String query){
        ExecutionResult result = graphQL.execute(query);
        logger.info("errors: "+result.getErrors());
        if(result.getErrors().isEmpty())
            return ResponseEntity.ok(result.getData());
        else return ResponseEntity.badRequest().body(result.getErrors());
    }

    @GetMapping("/schemas")
    public List<UdoSchema> allSchemas() {
        logger.info("find all schemas...");
        return schemaService.findAllSchemas();
    }

    @PutMapping("/schemas")
    public UdoSchema createSchema(@RequestBody JSONObject params) throws UdoPersistException {
        logger.info("now saving a new schema...");
        String udoi = params.getString("udoi");
        String name = params.getString("schemaName");
        JSONObject content = params.getJSONObject("schemaContent");
        return schemaService.saveSchema(new UdoSchema(udoi, name, content));
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

    @PostMapping("/schemas/{udoi}")
    public UdoSchema updateSchema(@RequestBody JSONObject params, @PathVariable String udoi) throws UdoPersistException {
//        String udoi = params.getString("udoi");
        logger.info("now updating schema " + udoi + "...");
        String name = params.getString("schemaName");
        JSONObject content = params.getJSONObject("schemaContent");
        return schemaService.updateSchema(new UdoSchema(udoi, name, content), udoi);
    }
}
