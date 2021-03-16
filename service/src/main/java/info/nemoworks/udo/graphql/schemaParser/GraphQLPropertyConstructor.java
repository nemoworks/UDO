package info.nemoworks.udo.graphql.schemaParser;

public class GraphQLPropertyConstructor {
    private String schemaName;

    private final static String COLLECTION_NAME_PRE = "sddm_";
    private final static String QUERY_XXLIST_SUFFIX ="Documents";
    private final static String QUERY_XX_SUFFIX = "";
    private final static String CREATE_NEW_XX_PRE = "new";
    private final static String UPDATE_XX_PRE = "update";
    private final static String DELETE_XX_PRE = "delete";
    private final static String FILTER_XX_SUFFIX = "Filters";
    private final static String INPUT_XX_SUFFIX = "Inputs";
    private final static String COMMITS__XX_SUFFIX = "Commits";


    public GraphQLPropertyConstructor(String schemaName) {
        this.schemaName = schemaName;
    }

    public String collectionName(){
        return COLLECTION_NAME_PRE+schemaName;
    }

    public String queryXxlistKeyWord(){
        return schemaName+QUERY_XXLIST_SUFFIX;
    }

    public String queryXxKeyWord(){
        return schemaName+QUERY_XX_SUFFIX;
    }

    public String createNewXxKeyWord(){
        return CREATE_NEW_XX_PRE+schemaName;
    }

    public String updateXxKeyWord(){
        return UPDATE_XX_PRE+schemaName;
    }

    public String deleteXxKeyWord(){
        return DELETE_XX_PRE+schemaName;
    }

    public String schemaKeyWordInQuery(){
        return lowerCase(schemaName);
    }

    public String schemaKeyWordInGraphQL(){
        return upperCase(schemaName);
    }

    public String commitsXxKeyWord(){
        return schemaName+COMMITS__XX_SUFFIX;
    }

    public String commitsTypeInGraphQL(){
        return upperCase(schemaName)+COMMITS__XX_SUFFIX;
    }

    public String filterKeyWordInQueryXxlist(){
        return schemaName+FILTER_XX_SUFFIX;
    }

    public String inputKeyWordInQuery(){
        return schemaName+INPUT_XX_SUFFIX;
    }

    private String upperCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private String lowerCase(String str){
        return str.substring(0,1).toLowerCase() + str.substring(1);
    }

}
