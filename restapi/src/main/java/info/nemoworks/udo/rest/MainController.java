//package info.nemoworks.udo.rest;
//
//import graphql.ExecutionResult;
//import graphql.GraphQL;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import info.nemoworks.udo.graphql.GraphQLBuilder;
//@RestController
//@CrossOrigin
//@RequestMapping("/api")
//public class MainController {
//    private GraphQL graphQL;
//    private GraphQLBuilder graphQlBuilder;
//    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
//
//    @Autowired
//    public MainController(GraphQLBuilder graphQlBuilder){
//        this.graphQL = graphQlBuilder.createGraphQl();
//        this.graphQlBuilder = graphQlBuilder;
//    }
//
//    //    @CrossOrigin
//    @PostMapping(value = "documents/query")
//    public ResponseEntity query(@RequestBody String query){
//        ExecutionResult result = graphQL.execute(query);
//        logger.info("errors: "+result.getErrors());
//        if(result.getErrors().isEmpty())
//            return ResponseEntity.ok(result.getData());
//        else return ResponseEntity.badRequest().body(result.getErrors());
//    }
//}
