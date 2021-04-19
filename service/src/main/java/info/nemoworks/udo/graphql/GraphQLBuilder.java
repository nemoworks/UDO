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
import info.nemoworks.udo.service.PrometheusService;
import info.nemoworks.udo.service.UdoSchemaService;
import info.nemoworks.udo.service.UdoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static graphql.GraphQL.newGraphQL;

@Component
public class GraphQLBuilder {
    private TypeRegistryBuilder typeRegistryBuilder;
    private RuntimeWiringBuilder runtimeWiringBuilder;
    private UdoService udoService;
    private UdoSchemaService udoSchemaService;
    private PrometheusService prometheusService;

    @Autowired
    public GraphQLBuilder(TypeRegistryBuilder typeRegistryBuilder, RuntimeWiringBuilder runtimeWiringBuilder, UdoService udoService, UdoSchemaService udoSchemaService, PrometheusService prometheusService) {
        this.typeRegistryBuilder = typeRegistryBuilder;
        this.runtimeWiringBuilder = runtimeWiringBuilder;
        this.udoService = udoService;
        this.udoSchemaService = udoSchemaService;
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
//        try {
//            s = new String(Files.readAllBytes(Paths.get("/Users/congtang/Desktop/UDO/service/src/main/resources/light.json")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        JSONObject jsonObject = JSON.parseObject(s);
        UdoSchema schema = new UdoSchema("udo1","purifier", jsonObject);
        SchemaTree schemaTree = new SchemaTree().createSchemaTree( new Gson().fromJson(schema.getSchemaContent().toString(), JsonObject.class));
        typeRegistryBuilder.addSchema(schemaTree);
        runtimeWiringBuilder.addNewSchemaDataFetcher(udoService,schemaTree, prometheusService);

        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeRegistryBuilder.getTypeDefinitionRegistry(), runtimeWiringBuilder.getRuntimeWiring());
        return  newGraphQL(graphQLSchema).build();
    }

    public GraphQL addTypeInGraphQL(SchemaTree schemaTree){
        this.addNewTypeAndDataFetcherInGraphQL(schemaTree);
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeRegistryBuilder.getTypeDefinitionRegistry(), runtimeWiringBuilder.getRuntimeWiring());
        return  newGraphQL(graphQLSchema).build();
    }

    private void addNewTypeAndDataFetcherInGraphQL(SchemaTree schemaTree){
//        JSONObject json = udoSchema.getSchemaContent();
//        System.out.println(json);
        typeRegistryBuilder.addSchema(schemaTree);
        runtimeWiringBuilder.addNewSchemaDataFetcher(udoService,schemaTree, prometheusService);

    }


}

