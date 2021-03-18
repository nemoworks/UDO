package info.nemoworks.udo.graphql;

import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.language.*;
import info.nemoworks.udo.graphql.InputValueDefinitionConstructor.*;
import info.nemoworks.udo.graphql.schemaParser.GraphQLPropertyConstructor;
import info.nemoworks.udo.graphql.schemaParser.SchemaTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TypeRegistryBuilder {
    private TypeDefinitionRegistry typeDefinitionRegistry;
    public static Map<String, Map<String,Type>> typeDefinitionsMap = new HashMap<>();

    @Autowired
    TypeRegistryBuilder() {
        this.typeDefinitionRegistry = new TypeDefinitionRegistry();
    }

    TypeDefinitionRegistry getTypeDefinitionRegistry() {
        return typeDefinitionRegistry;
    }

    void initSchemaDefinition() {
        SchemaDefinition schemaDefinition = new SchemaDefinition();
        OperationTypeDefinition operationTypeDefinition = new OperationTypeDefinition("query", new TypeName("Query"));
        schemaDefinition.getOperationTypeDefinitions().add(operationTypeDefinition);
        typeDefinitionRegistry.add(schemaDefinition);
    }

    void initTypeDefinition() {
        Map<String, Type> typeMap = new HashMap<>();
        typeDefinitionRegistry.add(newObjectTypeDefinition("Query", newFieldDefinitions(typeMap)));

        Map<String, Type> deleteTypeMap = new HashMap<>();
        deleteTypeMap.put("deleteResult", new TypeName("String"));
        typeDefinitionRegistry.add(newObjectTypeDefinition("DeleteResult", newFieldDefinitions(deleteTypeMap)));
    }

    public void addSchema(SchemaTree schemaTree) {
        schemaTree.getChildSchemas().forEach((key, value) -> addSchema(value));

        if (!typeDefinitionsMap.keySet().contains(schemaTree.getName())) {

            typeDefinitionsMap.put(schemaTree.getName(),schemaTree.getTypeMap());

            GraphQLPropertyConstructor graphQLPropertyConstructor = new GraphQLPropertyConstructor(schemaTree.getName());

            this.addTypeDefinition(graphQLPropertyConstructor.schemaKeyWordInGraphQL(), schemaTree.getTypeMap());

            this.addDocumentTypeInQuery(graphQLPropertyConstructor);

            this.addDocumentListTypeInQuery(graphQLPropertyConstructor,schemaTree.getFilterMap());

            this.addCreateNewDocumentTypeInQuery(graphQLPropertyConstructor,schemaTree.getInputMap());

            this.addUpdateDocumentByIdInQuery(graphQLPropertyConstructor);

            this.addDeleteDocumentByIdInQuery(graphQLPropertyConstructor);

            this.addDocumentCommitsInQuery(graphQLPropertyConstructor);

        }
    }

    private void addDocumentTypeInQuery(GraphQLPropertyConstructor graphQLPropertyConstructor){
        //orderDocument(id:String):OrderDocument
        this.addFieldDefinitionsInQueryType(graphQLPropertyConstructor.schemaKeyWordInQuery()
                ,new TypeName(graphQLPropertyConstructor.schemaKeyWordInGraphQL())
                ,new IdInputDefBuilder().inputValueDefinitionListBuilder(graphQLPropertyConstructor));
    }

    private void addDocumentListTypeInQuery(GraphQLPropertyConstructor graphQLPropertyConstructor,Map<String, Type> filterMap){
        //input filter Type in Type
        this.addInputObjectTypeDefinition(graphQLPropertyConstructor.filterKeyWordInQueryXxlist(), filterMap);
        //orderDocumentList:[OrderDocument]
        this.addFieldDefinitionsInQueryType(graphQLPropertyConstructor.queryXxlistKeyWord()
                ,new ListType(new TypeName(graphQLPropertyConstructor.schemaKeyWordInGraphQL()))
                ,new FilterDocListInputDefBuilder().inputValueDefinitionListBuilder(graphQLPropertyConstructor));
    }

    private void addCreateNewDocumentTypeInQuery(GraphQLPropertyConstructor graphQLPropertyConstructor,Map<String,Type> inputMap){
        this.addInputObjectTypeDefinition(graphQLPropertyConstructor.inputKeyWordInQuery(),inputMap);
        this.addFieldDefinitionsInQueryType(graphQLPropertyConstructor.createNewXxKeyWord()
                ,new TypeName(graphQLPropertyConstructor.schemaKeyWordInGraphQL())
                , new CreateNewDocInputDefBuilder().inputValueDefinitionListBuilder(graphQLPropertyConstructor));
    }

    private void addUpdateDocumentByIdInQuery(GraphQLPropertyConstructor graphQLPropertyConstructor){
        this.addFieldDefinitionsInQueryType(graphQLPropertyConstructor.updateXxKeyWord()
                ,new TypeName(graphQLPropertyConstructor.schemaKeyWordInGraphQL())
                ,new UpdateDocInputDefBuilder().inputValueDefinitionListBuilder(graphQLPropertyConstructor));
    }

    private void addDeleteDocumentByIdInQuery(GraphQLPropertyConstructor graphQLPropertyConstructor){
        this.addFieldDefinitionsInQueryType(graphQLPropertyConstructor.deleteXxKeyWord()
                ,new TypeName("DeleteResult")
                ,new IdInputDefBuilder().inputValueDefinitionListBuilder(graphQLPropertyConstructor));
    }

    private void addDocumentCommitsInQuery(GraphQLPropertyConstructor graphQLPropertyConstructor){
        //register DocumentCommits Type
        Map<String,Type> commitsTypeMap = new HashMap<>();
        commitsTypeMap.put("commitId",new TypeName("String"));
        commitsTypeMap.put("commitDate",new TypeName("String"));
        commitsTypeMap.put("data",new TypeName(graphQLPropertyConstructor.schemaKeyWordInGraphQL()));
        this.addTypeDefinition(graphQLPropertyConstructor.commitsTypeInGraphQL(),commitsTypeMap);
        //init documentCommits(id:String,name:String):[DocumentCommits] in Query
        this.addFieldDefinitionsInQueryType(graphQLPropertyConstructor.commitsXxKeyWord()
                ,new ListType(new TypeName(graphQLPropertyConstructor.commitsTypeInGraphQL()))
                ,new IdInputDefBuilder().inputValueDefinitionListBuilder(graphQLPropertyConstructor));

    }


    void addInputObjectTypeDefinition(String name, Map<String, Type> typeMap) {
        InputObjectTypeDefinition inputObjectTypeDefinition = new InputObjectTypeDefinition(name);
//        inputObjectTypeDefinition.getInputValueDefinitions().add(new InputValueDefinition("OR",new ListType(new TypeName(name))));
//        inputObjectTypeDefinition.getInputValueDefinitions().add(new InputValueDefinition("AND",new ListType(new TypeName(name))));
        typeMap.forEach((key, value) -> inputObjectTypeDefinition.getInputValueDefinitions().add(new InputValueDefinition(key, value)));
        typeDefinitionRegistry.add(inputObjectTypeDefinition);
    }

    void addTypeDefinition(String name, Map<String, Type> typeMap) {
        typeDefinitionRegistry.add(newObjectTypeDefinition(name, newFieldDefinitions(typeMap)));
    }

    //todo: typeDefinitionRegistry.types()不能获取
    void deleteTypeDefinition(String name) {
//        typeDefinitionRegistry.getType(name).ifPresent(typeDefinition -> {
//            if(typeDefinition instanceof ObjectTypeDefinition)
//                ((ObjectTypeDefinition) typeDefinition).getFieldDefinitions().clear();
//        });
        typeDefinitionRegistry.types().remove(name);
    }

    void addFieldDefinitionsInQueryType(String name, Type type) {
        typeDefinitionRegistry.getType("Query").ifPresent(queryType -> {
            if (queryType instanceof ObjectTypeDefinition)
                ((ObjectTypeDefinition) queryType).getFieldDefinitions().add(new FieldDefinition(name, type));
        });
    }

    void deleteFieldDefinitionsInQueryType(String name) {
        typeDefinitionRegistry.getType("Query").ifPresent(queryType -> {
            if (queryType instanceof ObjectTypeDefinition)
                ((ObjectTypeDefinition) queryType).getFieldDefinitions().remove(this.getFieldDefinitionInQueryType(name));
        });
    }

    void updateFieldDefinitionsInQueryType(String name, Type type) {
        typeDefinitionRegistry.getType("Query").ifPresent(queryType -> {
            if (queryType instanceof ObjectTypeDefinition) {
                ((ObjectTypeDefinition) queryType).getFieldDefinitions().remove(this.getFieldDefinitionInQueryType(name));
                ((ObjectTypeDefinition) queryType).getFieldDefinitions().add(new FieldDefinition(name, type));
            }
        });
    }

    void updateFieldDefinitions(String name, Map<String, Type> typeMap) {
        typeDefinitionRegistry.getType(name).ifPresent(typeDefinition -> {
            if (typeDefinition instanceof ObjectTypeDefinition) {
                ((ObjectTypeDefinition) typeDefinition).getFieldDefinitions().clear();
                ((ObjectTypeDefinition) typeDefinition).getFieldDefinitions()
                        .addAll(newFieldDefinitions(typeMap));
            }
        });
    }

    private FieldDefinition getFieldDefinitionInQueryType(String name) {
        if (typeDefinitionRegistry.getType("Query").get() instanceof ObjectTypeDefinition) {
            for (FieldDefinition fieldDefinition : ((ObjectTypeDefinition) typeDefinitionRegistry.getType("Query").get()).getFieldDefinitions()) {
                if (fieldDefinition.getName().equals(name))
                    return fieldDefinition;
            }
        }
        return null;
    }

    private List<FieldDefinition> getFieldDefinitionsInType(String name) {
        if (typeDefinitionRegistry.getType(name).get() instanceof ObjectTypeDefinition)
            return ((ObjectTypeDefinition) typeDefinitionRegistry.getType(name).get()).getFieldDefinitions();
        return null;
    }

    Map<String, TypeDefinition> getFieldDefinitionsInMyTypeRegistry() {
        return typeDefinitionRegistry.types();
    }

    void addFieldDefinitionsInQueryType(String name, Type type, List<InputValueDefinition> inputValueDefinitions) {
        FieldDefinition fieldDefinition = new FieldDefinition(name, type);
        inputValueDefinitions.forEach(inputValueDefinition -> {
            fieldDefinition.getInputValueDefinitions().add(inputValueDefinition);
        });
        typeDefinitionRegistry.getType("Query").ifPresent(queryType -> {
            if (queryType instanceof ObjectTypeDefinition) {
                ((ObjectTypeDefinition) queryType).getFieldDefinitions()
                        .add(fieldDefinition);
            }
        });
    }

    private List<FieldDefinition> newFieldDefinitions(Map<String, Type> typeMap) {
        List<FieldDefinition> fieldDefinitions = new ArrayList<>();
        typeMap.forEach((name, Type) -> fieldDefinitions.add(new FieldDefinition(name, Type)));
        return fieldDefinitions;
    }

    private ObjectTypeDefinition newObjectTypeDefinition(String name, List<FieldDefinition> fieldDefinitions) {
        ObjectTypeDefinition objectTypeDefinition = new ObjectTypeDefinition(name);
        fieldDefinitions.forEach(fieldDefinition -> objectTypeDefinition.getFieldDefinitions().add(fieldDefinition));
        return objectTypeDefinition;
    }
}
