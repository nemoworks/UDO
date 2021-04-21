package info.nemoworks.udo.graphql.dataFetchers;

import com.google.gson.JsonObject;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.repository.h2.UDROPersistException;
import info.nemoworks.udo.service.UdoService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class DeleteDocumentMutation implements DataFetcher<JsonObject> {
    private final UdoService udoService ;

    private String documentCollectionName;

    public DeleteDocumentMutation(UdoService udoService) {
        this.udoService = udoService;
    }

    public void setDocumentCollectionName(String documentCollectionName){
        this.documentCollectionName = documentCollectionName;
    }

    @SneakyThrows
    @Override
    public JsonObject get(DataFetchingEnvironment dataFetchingEnvironment) {
        String udoi = dataFetchingEnvironment.getArgument("udoi").toString();
//        String collection = dataFetchingEnvironment.getArgument("collection").toString();
        return  deleteDocumentById(udoi, documentCollectionName);
    }

    private JsonObject deleteDocumentById(String id, String collection) throws UdoPersistException, UDROPersistException {
        udoService.deleteUdoById(id, collection);
        JsonObject res = new JsonObject();
        res.addProperty("deleteResult","document has been deleted");
        return res;
    }
}
