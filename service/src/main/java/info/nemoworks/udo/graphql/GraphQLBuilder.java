package info.nemoworks.udo.graphql;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.SchemaGenerator;
import info.nemoworks.udo.graphql.schema.GraphQLPropertyConstructor;
import info.nemoworks.udo.graphql.schema.SchemaTree;
import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.monitor.MeterCluster;
import info.nemoworks.udo.repository.PrometheusService;
import info.nemoworks.udo.service.UdoService;
import net.sf.json.JSONObject;
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
    public GraphQL createGraphQl() {

        typeRegistryBuilder.initSchemaDefinition();
        typeRegistryBuilder.initTypeDefinition();
        runtimeWiringBuilder.initRuntimeWiring();
        String s = "{\n" +
                "  \"type\": \"object\",\n" +
                "  \"title\": \"VirtualMachineInstance\",\n" +
                "  \"properties\": {\n" +
                "    \"CPU\": {\n" +
                "      \"properties\": {\n" +
                "        \"cores\": {\n" +
                "          \"type\": \"meter\"\n" +
                "        },\n" +
                "        \"sockets\": {\n" +
                "          \"type\": \"integer\"\n" +
                "        },\n" +
                "        \"threads\": {\n" +
                "          \"type\": \"integer\"\n" +
                "        },\n" +
                "        \"model\": {\n" +
                "          \"type\": \"string\"\n" +
                "        },\n" +
                "        \"features\": {\n" +
                "          \"items\": {\n" +
                "            \"type\": \"embedded\",\n" +
                "            \"typeName\": \"CPUFeature\"\n" +
                "          },\n" +
                "          \"type\": \"array\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"additionalProperties\": false,\n" +
                "      \"title\": \"CPU\",\n" +
                "      \"type\": \"object\"\n" +
                "    },\n" +
                "    \"CPUFeature\": {\n" +
                "      \"required\": [\n" +
                "        \"name\"\n" +
                "      ],\n" +
                "      \"properties\": {\n" +
                "        \"name\": {\n" +
                "          \"type\": \"string\"\n" +
                "        },\n" +
                "        \"policy\": {\n" +
                "          \"type\": \"string\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"additionalProperties\": false,\n" +
                "      \"title\": \"CPUFeature\",\n" +
                "      \"type\": \"object\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        JSONObject JsonObject = JSONObject.fromObject(s);
        UdoSchema schema = new UdoSchema("purifier", JsonObject);
        SchemaTree schemaTree = new SchemaTree().createSchemaTree(new Gson().fromJson(schema.getSchemaContent().toString(), JsonObject.class));
        typeRegistryBuilder.addSchema(schemaTree);
        runtimeWiringBuilder.addNewSchemaDataFetcher(udoService, schemaTree, prometheusService);
        typeRegistryBuilder.buildTypeRegistry();

        MeterCluster.addSchemaMeter(schemaTree);
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeRegistryBuilder.getTypeDefinitionRegistry(), runtimeWiringBuilder.getRuntimeWiring());
        return newGraphQL(graphQLSchema).build();
    }

    public GraphQL addTypeInGraphQL(SchemaTree schemaTree) {
        logger.info("add new schema definition in graphql " + schemaTree.getName() + "...");
        this.addNewTypeAndDataFetcherInGraphQL(schemaTree);
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeRegistryBuilder.getTypeDefinitionRegistry(), runtimeWiringBuilder.getRuntimeWiring());
        return newGraphQL(graphQLSchema).build();
    }

    private void addNewTypeAndDataFetcherInGraphQL(SchemaTree schemaTree) {
        typeRegistryBuilder.addSchema(schemaTree);
        runtimeWiringBuilder.addNewSchemaDataFetcher(udoService, schemaTree, prometheusService);
        typeRegistryBuilder.buildTypeRegistry();
    }

    public GraphQL updateTypeInGraphQl(SchemaTree schemaTree){
        this.updateTypeAndDataFetcherInGraphQl(schemaTree);
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeRegistryBuilder.getTypeDefinitionRegistry()
                , runtimeWiringBuilder.getRuntimeWiring());
        return  newGraphQL(graphQLSchema).build();
    }

    public GraphQL deleteTypeInGraphQl(SchemaTree schemaTree){
        // todo fix this
        this.deleteTypeAndDataFetcherInGraphQl(schemaTree);
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeRegistryBuilder.getTypeDefinitionRegistry(), runtimeWiringBuilder.getRuntimeWiring());
        return  newGraphQL(graphQLSchema).build();
    }



    private void updateTypeAndDataFetcherInGraphQl(SchemaTree schemaTree){
    }

    //todo fix delete
    private void deleteTypeAndDataFetcherInGraphQl(SchemaTree schemaTree){
        //String schemaName = schema.getSchemaTypeName();
        //String schemaName = schema.getSchemaContent().getString("name");

        schemaTree.getChildSchemas().forEach((key, value) -> deleteTypeAndDataFetcherInGraphQl(value));
        String schemaName = schemaTree.getName();
        GraphQLPropertyConstructor graphQLPropertyConstructor = new GraphQLPropertyConstructor(schemaName);
        //delete orderDocumentList in Query
        typeRegistryBuilder.deleteFieldDefinitionsInQueryType(graphQLPropertyConstructor.queryXxlistKeyWord());
        typeRegistryBuilder.deleteTypeDefinition(graphQLPropertyConstructor.filterKeyWordInQueryXxlist());
        //delete orderDocument in Query
        //typeRegistryBuilder.deleteFieldDefinitionsInQueryType(schema.getSchemaTypeName());
        typeRegistryBuilder.deleteFieldDefinitionsInQueryType(schemaName);
        //delete createNewOrder in Query
        typeRegistryBuilder.deleteFieldDefinitionsInQueryType(graphQLPropertyConstructor.createNewXxKeyWord());
        typeRegistryBuilder.deleteTypeDefinition(graphQLPropertyConstructor.deleteXxKeyWord());
        //delete deleteOrder in Query
        typeRegistryBuilder.deleteFieldDefinitionsInQueryType(graphQLPropertyConstructor.deleteXxKeyWord());
        //delete orderCommits in Query
        typeRegistryBuilder.deleteFieldDefinitionsInQueryType(graphQLPropertyConstructor.commitsTypeInGraphQL());
        typeRegistryBuilder.deleteTypeDefinition(graphQLPropertyConstructor.commitsXxKeyWord());
        //delete update in Query
        typeRegistryBuilder.deleteFieldDefinitionsInQueryType(graphQLPropertyConstructor.updateXxKeyWord());
        //delete orderDocument
        //typeRegistryBuilder.deleteTypeDefinition(schema.getDocumentTypeName());
        typeRegistryBuilder.deleteTypeDefinition(graphQLPropertyConstructor.collectionName());



        runtimeWiringBuilder.deleteEntryInQueryDataFetcher(schemaName);

        runtimeWiringBuilder.deleteEntryInQueryDataFetcher(schemaName);

        runtimeWiringBuilder.deleteEntryInQueryDataFetcher(schemaName);

        runtimeWiringBuilder.deleteEntryInQueryDataFetcher(schemaName);

        runtimeWiringBuilder.deleteEntryInQueryDataFetcher(schemaName);

        runtimeWiringBuilder.deleteEntryInQueryDataFetcher(schemaName);

        runtimeWiringBuilder.deleteDataFetcherByName(schemaName);

        runtimeWiringBuilder.deleteDataFetcherByName(schemaName);
    }

    
}

