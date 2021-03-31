package info.nemoworks.udo.graphql.dataFetchers;

import com.alibaba.fastjson.JSONObject;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.service.UdoService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class DeleteDocumentMutation implements DataFetcher<JSONObject> {
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
    public JSONObject get(DataFetchingEnvironment dataFetchingEnvironment) {
        String udoi = dataFetchingEnvironment.getArgument("udoi").toString();
//        String collection = dataFetchingEnvironment.getArgument("collection").toString();
        return  deleteDocumentById(udoi, documentCollectionName);
    }

    private JSONObject deleteDocumentById(String id, String collection) throws UdoPersistException {
        udoService.deleteUdoById(id, collection);
        JSONObject res = new JSONObject();
        res.put("deleteResult","document has been deleted");
        return res;
    }
}
