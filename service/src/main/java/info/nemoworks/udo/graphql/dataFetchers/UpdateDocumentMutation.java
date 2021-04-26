package info.nemoworks.udo.graphql.dataFetchers;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.repository.h2.exception.UDROPersistException;
import info.nemoworks.udo.service.UdoService;
import lombok.SneakyThrows;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UpdateDocumentMutation implements DataFetcher<JSONObject> {

    private final UdoService udoService;

    private String documentCollectionName;

    public UpdateDocumentMutation(UdoService udoService) {
        this.udoService = udoService;
    }

    public void setDocumentCollectionName(String documentCollectionName){
        this.documentCollectionName = documentCollectionName;
    }

    @SneakyThrows
    @Override
    public JSONObject get(DataFetchingEnvironment dataFetchingEnvironment) {
        String udoi = dataFetchingEnvironment.getArgument("udoi").toString();
        JSONObject content = JSONObject.fromObject(dataFetchingEnvironment.getArgument("content"));
//        Gson gson = new Gson();
//        JsonObject content = JsonParser.parseString(cont.toJSONString()).getAsJsonObject();
//        String collection = dataFetchingEnvironment.getArgument("collection").toString();
        return Objects.requireNonNull(this.updateDocumentById(udoi, content, documentCollectionName)).getContent();
    }

    private Udo updateDocumentById(String id, JSONObject content, String collection) throws UdoPersistException, UDROPersistException {
        Udo udo = udoService.findUdoById(id, collection);
        assert udo!=null;
        udo.setContent(content);
        try {
            return udoService.updateUdo(udo,id);
        } catch (UdoPersistException e) {
            e.printStackTrace();
        }
        return null;
    }

}
