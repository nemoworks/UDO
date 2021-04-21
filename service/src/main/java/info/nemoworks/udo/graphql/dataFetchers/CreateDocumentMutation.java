package info.nemoworks.udo.graphql.dataFetchers;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.monitor.UdoMeterRegistry;
import info.nemoworks.udo.repository.h2.UDROPersistException;
import info.nemoworks.udo.service.UdoService;
import org.springframework.stereotype.Component;

@Component
public class CreateDocumentMutation implements DataFetcher<JsonObject> {

    private final UdoService udoService;

    final UdoMeterRegistry udoMeterRegistry;

    public CreateDocumentMutation(UdoService udoService, UdoMeterRegistry udoMeterRegistry) {
        this.udoService = udoService;
        this.udoMeterRegistry = udoMeterRegistry;
    }


    @Override
    public JsonObject get(DataFetchingEnvironment dataFetchingEnvironment) {
        String udoi = dataFetchingEnvironment.getArgument("udoi").toString();
        JSONObject cont = new JSONObject(dataFetchingEnvironment.getArgument("content"));
        Gson gson = new Gson();
        JsonObject content = JsonParser.parseString(cont.toJSONString()).getAsJsonObject();
//        String name = dataFetchingEnvironment.getArgument("name").toString();
//        System.out.println("name");
        String schemaId = dataFetchingEnvironment.getArgument("schemaId").toString();
//        System.out.println("schemaId");
//        String collection = dataFetchingEnvironment.getArgument("collection").toString();
        Udo udo =  this.createNewUdo(udoi, schemaId, content);
        assert udo != null;
//        JsonObject json = udo.getContent();
//        json.add("udoi", udo.getUdoi());
//        json.put("name",udo.getName());
//        json.add("schemaId",udo.getSchemaId());
//        json.put("collection",udo.getCollection());
//        Gson gson = new Gson();
        JsonObject json = JsonParser.parseString(gson.toJson(udo)).getAsJsonObject();
        System.out.println("json: " + json);
        return json;
    }

    private Udo createNewUdo(String schemaId, String schema, JsonObject content){
        Udo udo = new Udo(schemaId, schema, content);
        try {
          //  udo = udoService.saveUdo(udo);
            udoMeterRegistry.addUdoMeter(udo);
            return udoService.saveUdo(udo);
        } catch (UdoPersistException | UDROPersistException e) {
            e.printStackTrace();
        }
        return null;
    }


}
