package info.nemoworks.udo.graphql.InputValueDefinitionConstructor;


import graphql.language.InputValueDefinition;
import graphql.language.TypeName;
import info.nemoworks.udo.graphql.schemaParser.GraphQLPropertyConstructor;

import java.util.ArrayList;
import java.util.List;

public class CreateNewDocInputDefBuilder implements InputValueDefinitionBuilder {
    @Override
    public List<InputValueDefinition> inputValueDefinitionListBuilder(GraphQLPropertyConstructor graphQLPropertyConstructor) {
        List<InputValueDefinition> inputValueDefinitions = new ArrayList<>();
        inputValueDefinitions.add(new InputValueDefinition("content",new TypeName(graphQLPropertyConstructor.inputKeyWordInQuery())));
        inputValueDefinitions.add(new InputValueDefinition("schemaId",new TypeName("String")));
        return inputValueDefinitions;
    }
}
