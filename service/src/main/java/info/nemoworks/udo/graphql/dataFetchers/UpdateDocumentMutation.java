package info.nemoworks.udo.graphql.dataFetchers;

import com.alibaba.fastjson.JSONObject;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.service.UdoService;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UpdateDocumentMutation implements DataFetcher<JSONObject> {

    private final UdoService udoService;

   // private String documentCollectionName;

    public UpdateDocumentMutation(UdoService udoService) {
        this.udoService = udoService;
    }

//    public void setDocumentCollectionName(String documentCollectionName){
//        this.documentCollectionName = documentCollectionName;
//    }

    @Override
    public JSONObject get(DataFetchingEnvironment dataFetchingEnvironment) {
        String id = dataFetchingEnvironment.getArgument("id").toString();
        JSONObject content = new JSONObject(dataFetchingEnvironment.getArgument("content"));
        return Objects.requireNonNull(this.updateDocumentById(id, content)).getContent();
    }

    private Udo updateDocumentById(String id, JSONObject content){
        Udo udo = udoService.findUdo(id);
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
