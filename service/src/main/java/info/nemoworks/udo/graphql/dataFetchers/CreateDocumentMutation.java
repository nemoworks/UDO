package info.nemoworks.udo.graphql.dataFetchers;

import com.alibaba.fastjson.JSONObject;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import info.nemoworks.udo.exception.TablePersistException;
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
        String udoi = dataFetchingEnvironment.getArgument("udoi").toString();
        JSONObject content = new JSONObject(dataFetchingEnvironment.getArgument("content"));
//        String name = dataFetchingEnvironment.getArgument("name").toString();
//        System.out.println("name");
        String schemaId = dataFetchingEnvironment.getArgument("schemaId").toString();
//        System.out.println("schemaId");
//        String collection = dataFetchingEnvironment.getArgument("collection").toString();
        Udo udo =  this.createNewUdo(udoi, schemaId, content);
        assert udo != null;
        JSONObject json = udo.getContent();
        json.put("udoi",udo.getUdoi());
//        json.put("name",udo.getName());
        json.put("schemaId",udo.getSchemaId());
//        json.put("collection",udo.getCollection());
        return json;
    }

    private Udo createNewUdo(String schemaId, String schema, JSONObject content){
        Udo udo = new Udo(schemaId, schema, content);
        try {
          //  udo = udoService.saveUdo(udo);
            return udoService.saveUdo(udo);
        } catch (UdoPersistException | TablePersistException e) {
            e.printStackTrace();
        }
        return null;
    }


}
