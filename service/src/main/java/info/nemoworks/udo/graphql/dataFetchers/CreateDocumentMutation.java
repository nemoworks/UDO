package info.nemoworks.udo.graphql.dataFetchers;

import com.alibaba.fastjson.JSONObject;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import info.nemoworks.udo.exception.UdoPersistException;
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
        Udo udo =  this.createNewUdo(schemaId,content);
        JSONObject json = udo.getContent();
        json.put("udoi",udo.getUdoi());
        return json;
    }

    private Udo createNewUdo(String schemaId,JSONObject content){
        Udo udo = new Udo(schemaId,content);
        try {
          //  udo = udoService.saveUdo(udo);
            return udoService.saveUdo(udo);
        } catch (UdoPersistException e) {
            e.printStackTrace();
        }
        return null;
    }


}
