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

    @Autowired
    public GraphQLBuilder(TypeRegistryBuilder typeRegistryBuilder, RuntimeWiringBuilder runtimeWiringBuilder, UdoService udoService, UdoSchemaService udoSchemaService) {
        this.typeRegistryBuilder = typeRegistryBuilder;
        this.runtimeWiringBuilder = runtimeWiringBuilder;
        this.udoService = udoService;
        this.udoSchemaService = udoSchemaService;
    }

    @PostConstruct
    public GraphQL createGraphQl(){

        typeRegistryBuilder.initSchemaDefinition();
        typeRegistryBuilder.initTypeDefinition();
        runtimeWiringBuilder.initRuntimeWiring();
        String s = null;
        try {
            s = new String(Files.readAllBytes(Paths.get("/Users/congtang/Desktop/UDO/service/src/main/resources/purifier.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSON.parseObject(s);
        UdoSchema schema = new UdoSchema("udo1","purifier", jsonObject);
        SchemaTree schemaTree = new SchemaTree().createSchemaTree( new Gson().fromJson(schema.getSchemaContent().toString(), JsonObject.class));
        typeRegistryBuilder.addSchema(schemaTree);
        runtimeWiringBuilder.addNewSchemaDataFetcher(udoService,schemaTree);

        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeRegistryBuilder.getTypeDefinitionRegistry(), runtimeWiringBuilder.getRuntimeWiring());
        return  newGraphQL(graphQLSchema).build();
    }

    public GraphQL addTypeInGraphQL(UdoSchema udoSchema){
        this.addNewTypeAndDataFetcherInGraphQL(udoSchema);
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeRegistryBuilder.getTypeDefinitionRegistry(), runtimeWiringBuilder.getRuntimeWiring());
        return  newGraphQL(graphQLSchema).build();
    }

    private void addNewTypeAndDataFetcherInGraphQL(UdoSchema udoSchema){
        JSONObject json = udoSchema.getSchemaContent();
        SchemaTree schemaTree = new SchemaTree().createSchemaTree( new Gson()
                .fromJson(udoSchema.getSchemaContent().toString(), JsonObject.class));
        typeRegistryBuilder.addSchema(schemaTree);
        runtimeWiringBuilder.addNewSchemaDataFetcher(udoService,schemaTree);

    }


}

