package info.nemoworks.udo.graphql.dataFetchers;

import com.alibaba.fastjson.JSONObject;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.service.UdoService;

public class CreateDocumentMutation implements DataFetcher<JSONObject> {

    private final UdoService udoService ;

    public CreateDocumentMutation(UdoService udoService) {
        this.udoService = udoService;
    }


    @Override
    public JSONObject get(DataFetchingEnvironment dataFetchingEnvironment) {
        String schemaId = dataFetchingEnvironment.getArgument("schemaId").toString();
        JSONObject content = new JSONObject(dataFetchingEnvironment.getArgument("content"));
        return this.createNewUdo(schemaId,content).getContent();
    }

    private Udo createNewUdo(String schemaId,JSONObject content){
        Udo udo = new Udo(schemaId,content);
        return udo;
    }


}
