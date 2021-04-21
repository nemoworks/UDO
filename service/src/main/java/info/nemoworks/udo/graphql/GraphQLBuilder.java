package info.nemoworks.udo.graphql;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.SchemaGenerator;
import info.nemoworks.udo.graphql.schema.SchemaTree;
import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.repository.PrometheusService;
import info.nemoworks.udo.service.UdoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static graphql.GraphQL.newGraphQL;

@Component
public class GraphQLBuilder {
    private final TypeRegistryBuilder typeRegistryBuilder;
    private final RuntimeWiringBuilder runtimeWiringBuilder;
    private final UdoService udoService;
    private final PrometheusService prometheusService;

    private static final Logger logger = LoggerFactory.getLogger(GraphQLBuilder.class);


    @Autowired
    public GraphQLBuilder(TypeRegistryBuilder typeRegistryBuilder, RuntimeWiringBuilder runtimeWiringBuilder, UdoService udoService, PrometheusService prometheusService) {
        this.typeRegistryBuilder = typeRegistryBuilder;
        this.runtimeWiringBuilder = runtimeWiringBuilder;
        this.udoService = udoService;
        this.prometheusService = prometheusService;
    }

    @PostConstruct
    public GraphQL createGraphQl(){

        typeRegistryBuilder.initSchemaDefinition();
        typeRegistryBuilder.initTypeDefinition();
        runtimeWiringBuilder.initRuntimeWiring();
        String s = "{\n" +
                "    \"type\": \"object\",\n" +
                "    \"title\": \"light\",\n" +
                "    \"properties\": {\n" +
                "        \"Name\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"title\": \"产品名称\",\n" +
                "            \"filter\": true\n" +
                "        },\n" +
                "        \"Brand\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"title\": \"品牌\",\n" +
                "            \"filter\": false\n" +
                "        }\n" +
                "    }\n" +
                "}";
        JSONObject JsonObject = JSON.parseObject(s);
        UdoSchema schema = new UdoSchema("purifier", JsonObject);
        SchemaTree schemaTree = new SchemaTree().createSchemaTree( new Gson().fromJson(schema.getSchemaContent().toString(), JsonObject.class));
        typeRegistryBuilder.addSchema(schemaTree);
        runtimeWiringBuilder.addNewSchemaDataFetcher(udoService,schemaTree, prometheusService);

        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeRegistryBuilder.getTypeDefinitionRegistry(), runtimeWiringBuilder.getRuntimeWiring());
        return  newGraphQL(graphQLSchema).build();
    }

    public GraphQL addTypeInGraphQL(SchemaTree schemaTree){
        logger.info("add new schema definition in graphql "+schemaTree.getName()+"...");
        this.addNewTypeAndDataFetcherInGraphQL(schemaTree);
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeRegistryBuilder.getTypeDefinitionRegistry(), runtimeWiringBuilder.getRuntimeWiring());
        return  newGraphQL(graphQLSchema).build();
    }

    private void addNewTypeAndDataFetcherInGraphQL(SchemaTree schemaTree){
        typeRegistryBuilder.addSchema(schemaTree);
        runtimeWiringBuilder.addNewSchemaDataFetcher(udoService,schemaTree, prometheusService);

    }


}

