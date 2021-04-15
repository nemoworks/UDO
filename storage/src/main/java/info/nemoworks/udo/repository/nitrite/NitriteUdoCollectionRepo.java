//package info.nemoworks.udo.repository.nitrite;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import info.nemoworks.udo.exception.UdoPersistException;
//import info.nemoworks.udo.model.Udo;
//import info.nemoworks.udo.model.UdoSchema;
//import info.nemoworks.udo.repository.UdoRepository;
//import org.dizitart.no2.*;
//import org.dizitart.no2.filters.Filters;
//import org.dizitart.no2.mapper.JacksonFacade;
//import org.dizitart.no2.mapper.MapperFacade;
//import org.dizitart.no2.util.Iterables;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class NitriteUdoCollectionRepo implements UdoRepository {
//    @Autowired
//    private Nitrite db;
//
//    private NitriteCollection nitriteCollection;
//
//    public NitriteUdoCollectionRepo(Nitrite db) {
//        this.db = db;
//    }
//
//    private NitriteCollection getCollection(String collection) {
//        return db.getCollection(collection);
//    }
//
//    public Nitrite getDb() {
//        return db;
//    }
//
//    @Override
//    public Udo saveUdo(Udo udo, String schemaId) throws UdoPersistException {
//        nitriteCollection = db.getCollection(schemaId);
//        MapperFacade mapperFacade = new JacksonFacade();
//        Document sav = mapperFacade.parse(JSONObject.toJSONString(udo));
//        WriteResult writeResult = nitriteCollection.insert(sav);
//        NitriteId nitriteId = Iterables.firstOrDefault(writeResult);
//        Document doc = nitriteCollection.getById(nitriteId);
//        return JSON.parseObject(mapperFacade.toJson(doc), Udo.class);
//    }
//
//    @Override
//    public Udo findUdo(String udoi, String schemaId) {
//        nitriteCollection = this.getCollection(schemaId);
//        Document doc = nitriteCollection.find(Filters.eq("udoi", udoi)).firstOrDefault();
//        MapperFacade mapperFacade = new JacksonFacade();
//        return JSON.parseObject(mapperFacade.toJson(doc), Udo.class);
//    }
//
//    @Override
//    public List<Udo> findAllUdos(String schemaId) {
//        nitriteCollection = this.getCollection(schemaId);
//        List<Document> docList = nitriteCollection.find().toList();
//        List<Udo> resList = new ArrayList<>();
//        for (Document doc: docList) {
//            MapperFacade mapperFacade = new JacksonFacade();
//            resList.add(JSON.parseObject(mapperFacade.toJson(doc), Udo.class));
//        }
//        return resList;
//    }
//
//
//
//    @Override
//    public void deleteUdo(String udoi, String schemaId) {
//        nitriteCollection = this.getCollection(schemaId);
//        nitriteCollection.remove(Filters.eq("udoi", udoi));
//    }
//
//    @Override
//    public Udo updateUdo(Udo udo, String udoi, String schemaId) {
//        nitriteCollection = this.getCollection(schemaId);
//        MapperFacade mapperFacade = new JacksonFacade();
//        Document upd = mapperFacade.parse(JSONObject.toJSONString(udo));
//        nitriteCollection.update(Filters.eq("udoi", udoi), upd);
//        Document doc = nitriteCollection.find(Filters.eq("udoi", udoi)).firstOrDefault();
//        return JSON.parseObject(mapperFacade.toJson(doc), Udo.class);
//    }
//}
