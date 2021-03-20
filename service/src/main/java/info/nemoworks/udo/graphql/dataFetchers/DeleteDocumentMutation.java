package info.nemoworks.udo.graphql.dataFetchers;

import com.alibaba.fastjson.JSONObject;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import info.nemoworks.udo.service.UdoService;
import org.springframework.stereotype.Component;

@Component
public class DeleteDocumentMutation implements DataFetcher<JSONObject> {
    private final UdoService udoService ;

    public DeleteDocumentMutation(UdoService udoService) {
        this.udoService = udoService;
    }

    @Override
    public JSONObject get(DataFetchingEnvironment dataFetchingEnvironment) {
        String id = dataFetchingEnvironment.getArgument("id").toString();
        return  deleteDocumentById(id);
    }

    private JSONObject deleteDocumentById(String id){
        udoService.deleteUdoById(id);
        JSONObject res = new JSONObject();
        res.put("deleteResult","document has been deleted");
        return res;
    }
}
