//package info.nemoworks.udo.graphql.dataFetchers;
//
//import com.alibaba.fastjson.JSONObject;
//import com.google.common.collect.Lists;
//import graphql.schema.DataFetcher;
//import graphql.schema.DataFetchingEnvironment;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class DocumentListDataFetcher implements DataFetcher<List<JSONObject>> {
//    private String documentCollectionName;
//    private String keyNameInParent;
//
//    @Autowired
//    public DocumentListDataFetcher(MongoTemplate mongoTemplate){
//        this.mongoTemplate = mongoTemplate;
//    }
//    public void setDocumentCollectionName(String documentCollectionName){
//        this.documentCollectionName = documentCollectionName;
//    }
//    public void setKeyNameInParent(String keyNameInParent) {
//        this.keyNameInParent = keyNameInParent;
//    }
//
//    @Override
//    public List<JSONObject> get(DataFetchingEnvironment dataFetchingEnvironment) {
//        if(keyNameInParent != null){
//            JSONObject jsonObject = dataFetchingEnvironment.getSource();
//            List<String> ids = (List<String>) jsonObject.get(keyNameInParent);
//            return this.getDocumentsByLinkList(ids);
//        }
//   }
//
//
//}
