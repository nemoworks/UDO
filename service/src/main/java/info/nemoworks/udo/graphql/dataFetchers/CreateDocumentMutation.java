package info.nemoworks.udo.graphql.dataFetchers;

import com.alibaba.fastjson.JSONObject;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

public class CreateDocumentMutation implements DataFetcher<JSONObject> {


    @Override
    public JSONObject get(DataFetchingEnvironment dataFetchingEnvironment) {
        return null;
    }
}
