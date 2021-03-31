package info.nemoworks.udo.graphql.InputValueDefinitionConstructor;


import java.util.ArrayList;
import java.util.List;

import graphql.language.InputValueDefinition;
import graphql.language.TypeName;
import info.nemoworks.udo.graphql.schema.GraphQLPropertyConstructor;

public class CreateNewDocInputDefBuilder implements InputValueDefinitionBuilder {
    @Override
    public List<InputValueDefinition> inputValueDefinitionListBuilder(GraphQLPropertyConstructor graphQLPropertyConstructor) {
        List<InputValueDefinition> inputValueDefinitions = new ArrayList<>();
        inputValueDefinitions.add(new InputValueDefinition("content",new TypeName(graphQLPropertyConstructor.inputKeyWordInQuery())));
        inputValueDefinitions.add(new InputValueDefinition("udoi",new TypeName("String")));
//        inputValueDefinitions.add(new InputValueDefinition("name",new TypeName("String")));
        inputValueDefinitions.add(new InputValueDefinition("schemaId",new TypeName("String")));
//        inputValueDefinitions.add(new InputValueDefinition("collection",new TypeName("String")));
        return inputValueDefinitions;
    }
}
