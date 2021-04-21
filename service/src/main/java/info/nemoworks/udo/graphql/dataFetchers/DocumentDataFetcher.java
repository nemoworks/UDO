package info.nemoworks.udo.graphql.dataFetchers;

import com.google.gson.JsonObject;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.repository.h2.UDROPersistException;
import info.nemoworks.udo.service.UdoService;


//@Component
public class DocumentDataFetcher implements DataFetcher<JsonObject> {

    private String documentCollectionName;
    private String keyNameInParent;

    private final UdoService udoService;

    public DocumentDataFetcher(UdoService udoService) {
        this.udoService = udoService;
    }


    public void setDocumentCollectionName(String documentCollectionName){
        this.documentCollectionName = documentCollectionName;
    }
    public void setKeyNameInParent(String keyNameInParent) {
        this.keyNameInParent = keyNameInParent;
    }


    @lombok.SneakyThrows
    @Override
    public JsonObject get(DataFetchingEnvironment dataFetchingEnvironment) {
        String id = String.valueOf(dataFetchingEnvironment.getArguments().get("udoi"));
        if(id.equals("null")){
            JsonObject JsonObject = dataFetchingEnvironment.getSource();
            id = JsonObject.get(keyNameInParent).getAsString();
        }
//        String collection = dataFetchingEnvironment.getArgument("collection").toString();
        return this.getDocumentByAggregation(id, documentCollectionName);
    }

    private JsonObject getDocumentByAggregation(String id, String collection) throws UdoPersistException, UDROPersistException {
        return udoService.findUdoById(id, collection).getContent();
       //return udoService.findDocument(id).getContent();
    }
}
