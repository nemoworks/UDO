package info.nemoworks.udo.graphql.InputValueDefinitionConstructor;

import java.util.List;

import graphql.language.InputValueDefinition;
import info.nemoworks.udo.graphql.schema.GraphQLPropertyConstructor;

public interface InputValueDefinitionBuilder {
    public List<InputValueDefinition> inputValueDefinitionListBuilder(GraphQLPropertyConstructor graphQLPropertyConstructor);
}
