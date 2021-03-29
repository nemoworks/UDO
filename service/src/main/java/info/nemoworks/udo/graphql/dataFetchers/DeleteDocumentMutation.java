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

    public DeleteDocumentMutation(UdoService udoService) {
        this.udoService = udoService;
    }

    @SneakyThrows
    @Override
    public JSONObject get(DataFetchingEnvironment dataFetchingEnvironment) {
        String id = dataFetchingEnvironment.getArgument("id").toString();
        String collection = dataFetchingEnvironment.getArgument("collection").toString();
        return  deleteDocumentById(id, collection);
    }

    private JSONObject deleteDocumentById(String id, String collection) throws UdoPersistException {
        udoService.deleteUdoById(id, collection);
        JSONObject res = new JSONObject();
        res.put("deleteResult","document has been deleted");
        return res;
    }
}
