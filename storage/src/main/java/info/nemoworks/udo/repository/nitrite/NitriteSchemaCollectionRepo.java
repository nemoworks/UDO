package info.nemoworks.udo.repository.nitrite;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.repository.UdoSchemaRepository;
import org.dizitart.no2.*;
import org.dizitart.no2.filters.Filters;
import org.dizitart.no2.mapper.JacksonFacade;
import org.dizitart.no2.mapper.MapperFacade;
import org.dizitart.no2.util.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NitriteSchemaCollectionRepo implements UdoSchemaRepository {

    @Autowired
    private Nitrite db;

    private NitriteCollection nitriteCollection;

//    MapperFacade mapperFacade = new JacksonFacade();

    public NitriteSchemaCollectionRepo(Nitrite db) {
        this.db = db;
        nitriteCollection = db.getCollection("Schema");
    }

    public Nitrite getDb() {
        return db;
    }

//    private NitriteCollection getCollection(String collection) {
//        return db.getCollection(collection);
//    }

    @Override
    public List<UdoSchema> findAllSchemas() {
//        nitriteCollection = db.getCollection(collection);
//        nitriteCollection = this.getCollection(collection);
        List<Document> docList = nitriteCollection.find().toList();
        List<UdoSchema> resList = new ArrayList<>();
        for (Document doc: docList) {
            MapperFacade mapperFacade = new JacksonFacade();
            resList.add(JSON.parseObject(mapperFacade.toJson(doc), UdoSchema.class));
        }
        return resList;
    }

    @Override
    public UdoSchema findSchemaById(String udoi) {
//        nitriteCollection = db.getCollection(collection);
//        nitriteCollection = this.getCollection(collection);
        Document doc = nitriteCollection.find(Filters.eq("udoi", udoi)).firstOrDefault();
        MapperFacade mapperFacade = new JacksonFacade();
        return JSON.parseObject(mapperFacade.toJson(doc), UdoSchema.class);
    }

    @Override
    public UdoSchema saveSchema(UdoSchema udoSchema) {
//        nitriteCollection = db.getCollection(collection);
//        nitriteCollection = this.getCollection(collection);
        MapperFacade mapperFacade = new JacksonFacade();
        Document sav = mapperFacade.parse(JSONObject.toJSONString(udoSchema));
        WriteResult writeResult = nitriteCollection.insert(sav);
        NitriteId nitriteId = Iterables.firstOrDefault(writeResult);
        Document doc = nitriteCollection.getById(nitriteId);
        return JSON.parseObject(mapperFacade.toJson(doc), UdoSchema.class);
    }

    @Override
    public void deleteSchemaById(String udoi) {
//        nitriteCollection = db.getCollection(collection);
//        nitriteCollection = this.getCollection(collection);
        nitriteCollection.remove(Filters.eq("udoi", udoi));
    }

    @Override
    public UdoSchema updateSchema(UdoSchema udoSchema, String udoi) {
//        nitriteCollection = db.getCollection(collection);
//        nitriteCollection = this.getCollection(collection);
        MapperFacade mapperFacade = new JacksonFacade();
        Document upd = mapperFacade.parse(JSONObject.toJSONString(udoSchema));
        nitriteCollection.update(Filters.eq("udoi", udoi), upd);
        Document doc = nitriteCollection.find(Filters.eq("udoi", udoi)).firstOrDefault();
        return JSON.parseObject(mapperFacade.toJson(doc), UdoSchema.class);
    }
}
