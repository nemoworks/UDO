package info.nemoworks.udo.graphql.dataFetchers;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.repository.h2.exception.UDROPersistException;
import info.nemoworks.udo.service.UdoService;
import net.sf.json.JSONObject;


//@Component
public class DocumentDataFetcher implements DataFetcher<JSONObject> {

    private String documentCollectionName;
    private String keyNameInParent;

    private final UdoService udoService;

    public DocumentDataFetcher(UdoService udoService) {
        this.udoService = udoService;
    }


    public void setDocumentCollectionName(String documentCollectionName) {
        this.documentCollectionName = documentCollectionName;
    }

    public void setKeyNameInParent(String keyNameInParent) {
        this.keyNameInParent = keyNameInParent;
    }


    @lombok.SneakyThrows
    @Override
    public JSONObject get(DataFetchingEnvironment dataFetchingEnvironment) {
        String id = String.valueOf(dataFetchingEnvironment.getArguments().get("udoi"));
        if (id.equals("null")) {
            JSONObject JsonObject = JSONObject.fromObject(dataFetchingEnvironment.getSource());
            id = JsonObject.getString(keyNameInParent);
        }
//        String collection = dataFetchingEnvironment.getArgument("collection").toString();
        return this.getDocumentByAggregation(id, documentCollectionName);
    }

    private JSONObject getDocumentByAggregation(String id, String collection) throws UdoPersistException, UDROPersistException {
//        System.out.println("get doc: " + udoService.findUdoById(id, collection));
        return udoService.findUdoById(id, collection).getContent();
        //return udoService.findDocument(id).getContent();
    }
}
