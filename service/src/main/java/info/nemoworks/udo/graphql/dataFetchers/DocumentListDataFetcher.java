package info.nemoworks.udo.graphql.dataFetchers;

import com.alibaba.fastjson.JSONObject;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.service.UdoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DocumentListDataFetcher implements DataFetcher<List<JSONObject>> {

    private final UdoService udoService;
    //private String documentCollectionName;
    private String keyNameInParent;

    @Autowired
    public DocumentListDataFetcher(UdoService udoService) {
        this.udoService = udoService;
    }

//    public void setDocumentCollectionName(String documentCollectionName){
//        this.documentCollectionName = documentCollectionName;
//    }
    public void setKeyNameInParent(String keyNameInParent) {
        this.keyNameInParent = keyNameInParent;
    }

    @Override
    public List<JSONObject> get(DataFetchingEnvironment dataFetchingEnvironment) {
//        if(keyNameInParent != null){
//            JSONObject jsonObject = dataFetchingEnvironment.getSource();
//            List<String> ids = (List<String>) jsonObject.get(keyNameInParent);
//            return this.getDocumentsByLinkList(ids);
//        }
        List<Udo> udos = this.getDocuments();
        List<JSONObject> udoContents = new ArrayList<>();
        udos.forEach(udo -> {
            JSONObject json = udo.getContent();
            json.put("udoi",udo.getUdoi());
            udoContents.add(json);
        });
        return udoContents;
   }

   public List<Udo> getDocuments(){
        return udoService.findAllUdos();
   }


}
