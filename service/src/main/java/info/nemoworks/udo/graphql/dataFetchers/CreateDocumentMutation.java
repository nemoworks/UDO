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
        System.out.println("balabala");
        String name = dataFetchingEnvironment.getArgument("name").toString();
        System.out.println("name");
        String schema = dataFetchingEnvironment.getArgument("schema").toString();
        System.out.println("schema");
        String collection = dataFetchingEnvironment.getArgument("collection").toString();
        System.out.println(name + " wuwuwu " + schema + "  " + collection);
        Udo udo =  this.createNewUdo(schemaId, name, schema, content, collection);
        assert udo != null;
        JSONObject json = udo.getContent();
        json.put("udoi",udo.getUdoi());
        json.put("name",udo.getName());
        json.put("schema",udo.getSchema());
        json.put("collection",udo.getCollection());
        return json;
    }

    private Udo createNewUdo(String schemaId, String name, String schema, JSONObject content, String collection){
        Udo udo = new Udo(schemaId, name, schema, content, collection);
        try {
          //  udo = udoService.saveUdo(udo);
            return udoService.saveUdo(udo);
        } catch (UdoPersistException e) {
            e.printStackTrace();
        }
        return null;
    }


}
