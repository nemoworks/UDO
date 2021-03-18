package info.nemoworks.udo.graphql;

import graphql.schema.DataFetcher;
import graphql.schema.idl.RuntimeWiring;
import info.nemoworks.udo.graphql.dataFetchers.CreateDocumentMutation;
import info.nemoworks.udo.graphql.dataFetchers.DocumentDataFetcher;
import info.nemoworks.udo.graphql.schemaParser.GraphQLPropertyConstructor;
import info.nemoworks.udo.graphql.schemaParser.SchemaTree;
import info.nemoworks.udo.model.Link;
import info.nemoworks.udo.service.UdoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class RuntimeWiringBuilder {
    private RuntimeWiring runtimeWiring;

    @Autowired
    public RuntimeWiringBuilder(){
        runtimeWiring = RuntimeWiring.newRuntimeWiring().build();
    }

    RuntimeWiring getRuntimeWiring() {
        return runtimeWiring;
    }

    void initRuntimeWiring(){
        Map<String, DataFetcher> map = new LinkedHashMap<>();
        runtimeWiring.getDataFetchers().put("Query",map);

    }

    void addDataFetchers(String name, Map<String, DataFetcher> dataFetcherMap){
        runtimeWiring.getDataFetchers().put(name,dataFetcherMap);
    }

    void addNewEntryInQueryDataFetcher(String name, DataFetcher dataFetcher){
        runtimeWiring.getDataFetchers().get("Query").put(name,dataFetcher);
    }

    void deleteEntryInQueryDataFetcher(String name){
        runtimeWiring.getDataFetchers().get("Query").remove(name);
    }

    void deleteDataFetcherByName(String name){
        runtimeWiring.getDataFetchers().remove(name);
    }

    void updateDataFetcherByName(String name,Map<String, DataFetcher> dataFetcherMap){
        runtimeWiring.getDataFetchers().put(name,dataFetcherMap);
    }

    public void addNewSchemaDataFetcher(UdoService udoService, SchemaTree schemaTree){
        schemaTree.getChildSchemas().forEach((key, value) -> addNewSchemaDataFetcher(udoService,value));

        GraphQLPropertyConstructor graphQLPropertyConstructor = new GraphQLPropertyConstructor(schemaTree.getName());

//        //orderDocumentList ==> documentListDataFetcher
//        DocumentListDataFetcher documentListDataFetcher = new DocumentListDataFetcher(mongoTemplate);
//        documentListDataFetcher.setDocumentCollectionName(graphQLPropertyConstructor.collectionName());
//        this.addNewEntryInQueryDataFetcher(graphQLPropertyConstructor.queryXxlistKeyWord(), documentListDataFetcher);

        //orderDocument ==>  documentDataFetcher
        DocumentDataFetcher documentDataFetcher = new DocumentDataFetcher(udoService);
        documentDataFetcher.setDocumentCollectionName(graphQLPropertyConstructor.collectionName());
        this.addNewEntryInQueryDataFetcher(graphQLPropertyConstructor.queryXxKeyWord(), documentDataFetcher);

        //createNewOrder ==> createDocumentMutation
        CreateDocumentMutation documentMutation = new CreateDocumentMutation(udoService);
        //documentMutation.setDocumentCollectionName(graphQLPropertyConstructor.collectionName());
        this.addNewEntryInQueryDataFetcher(graphQLPropertyConstructor.createNewXxKeyWord(),documentMutation);

//        //updateOrder ==> updateDocumentMutation
//        UpdateDocumentMutation updateDocumentMutation = new UpdateDocumentMutation(mongoTemplate,javers);
//        updateDocumentMutation.setDocumentCollectionName(graphQLPropertyConstructor.collectionName());
//        this.addNewEntryInQueryDataFetcher(graphQLPropertyConstructor.updateXxKeyWord(),updateDocumentMutation);
//
//        //deleteOrder ==> deleteDocumentMutation
//        DeleteDocumentMutation deleteDocumentMutation = new DeleteDocumentMutation(mongoTemplate,javers);
//        deleteDocumentMutation.setDocumentCollectionName(graphQLPropertyConstructor.collectionName());
//        this.addNewEntryInQueryDataFetcher(graphQLPropertyConstructor.deleteXxKeyWord(),deleteDocumentMutation);
//
//        //orderCommits ==> DocumentCommitsMutation
//        DocumentCommitsMutation documentCommitsMutation = new DocumentCommitsMutation(mongoTemplate,javers);
//        documentCommitsMutation.setDocumentCollectionName(graphQLPropertyConstructor.collectionName());
//        this.addNewEntryInQueryDataFetcher(graphQLPropertyConstructor.commitsXxKeyWord(),documentCommitsMutation);
//
//        if(!schemaTree.getLinkList().isEmpty()){
//            this.addDataFetchers(graphQLPropertyConstructor.schemaKeyWordInGraphQL(),linkTypeDataFetcher(schemaTree.getLinkList(),mongoTemplate));
//        }
    }

//    private Map<String,DataFetcher> linkTypeDataFetcher(List<Link> linkList, MongoTemplate mongoTemplate){
//        Map<String,DataFetcher> dataFetcherMap = new HashMap<>();
//        linkList.forEach(link -> {
//            if(link.getLinkType().equals("Link")){
//                DocumentDataFetcher documentDataFetcher1 = new DocumentDataFetcher(mongoTemplate);
//                documentDataFetcher1.setDocumentCollectionName(link.getCollectionName());
//                documentDataFetcher1.setKeyNameInParent(link.getName());
//                dataFetcherMap.put(link.getName(),documentDataFetcher1);
//            }else if(link.getLinkType().equals("LinkList")){
//                DocumentListDataFetcher documentListDataFetcher1 = new DocumentListDataFetcher(mongoTemplate);
//                documentListDataFetcher1.setDocumentCollectionName(link.getCollectionName());
//                documentListDataFetcher1.setKeyNameInParent(link.getName());
//                dataFetcherMap.put(link.getName(),documentListDataFetcher1);
//            }
//        });
//        return dataFetcherMap;
//    }
}
