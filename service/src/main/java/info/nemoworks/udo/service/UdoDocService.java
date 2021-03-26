package info.nemoworks.udo.service;

import com.alibaba.fastjson.JSONObject;
import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.repository.UdoDocCollection;
import info.nemoworks.udo.repository.UdoSchemaCollection;
import org.dizitart.no2.Document;
import org.dizitart.no2.mapper.JacksonFacade;
import org.dizitart.no2.mapper.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UdoDocService {
    @Autowired
    private UdoDocCollection udoDocCollection;

    @Autowired
    private UdoSchemaCollection udoSchemaCollection;

    public UdoDocService(UdoDocCollection udoDocCollection, UdoSchemaCollection udoSchemaCollection) {
        this.udoDocCollection = udoDocCollection;
        this.udoSchemaCollection = udoSchemaCollection;
    }

    public Document saveDoc(Udo udo) throws UdoPersistException {
        if (udoDocCollection.findDocById(udo.getUdoi()) != null) {
            throw new UdoPersistException("A doc with a same id already exists.");
        }
        MapperFacade mapperFacade = new JacksonFacade();
        return udoDocCollection.saveDoc(mapperFacade.parse(JSONObject.toJSONString(udo)));
    }

    public Document saveDoc(UdoSchema udoSchema) throws UdoPersistException {

        if (udoDocCollection.findDocById(udoSchema.getUdoi()) != null) {
            throw new UdoPersistException("A schema with a same id already exists.");
        }
        MapperFacade mapperFacade = new JacksonFacade();
        return udoDocCollection.saveDoc(mapperFacade.parse(JSONObject.toJSONString(udoSchema)));
    }

    public Document findDocById(String udoi, String collection) throws UdoPersistException {
        Document document;
        switch (collection) {
            case "Schema":
                document = udoSchemaCollection.findDocById(udoi);
                if (document == null) throw new UdoPersistException("Schema " + udoi + " does not exist.");
                return document;
            case "Document":
                document = udoDocCollection.findDocById(udoi);
                if (document == null) throw new UdoPersistException("Doc " + udoi + " does not exist.");
                return document;
            default: return null;
        }
    }

    public List<Document> findAllDocs(String collection) {
        switch (collection) {
            case "Schema":
                return udoSchemaCollection.findAllDocs();
            case "Document":
                return udoDocCollection.findAllDocs();
            default: return null;
        }
    }


    public List<Document> deleteDocById(String udoi, String collection) throws UdoPersistException {
        Document document;
        switch (collection) {
            case "Schema":
                document = udoSchemaCollection.findDocById(udoi);
                if (document == null) {
                    throw new UdoPersistException("Schema " + udoi + " does not exist.");
                }
                udoSchemaCollection.deleteDocById(udoi);
                return udoSchemaCollection.findAllDocs();
            case "Document":
                document = udoDocCollection.findDocById(udoi);
                if (document == null) {
                    throw new UdoPersistException("Document " + udoi + " does not exist.");
                }
                udoDocCollection.deleteDocById(udoi);
                return udoDocCollection.findAllDocs();
            default: return null;
        }
    }

    public Document updateDocument(Document document, String udoi, String collection) throws UdoPersistException {
        Document doc;
        switch (collection) {
            case "Schema":
                doc = udoSchemaCollection.findDocById(udoi);
                if (doc == null) {
                    throw new UdoPersistException("Schema " + udoi + " does not exist.");
                }
                return udoSchemaCollection.updateDoc(document, udoi);
            case "Document":
                doc = udoSchemaCollection.findDocById(udoi);
                if (doc == null) {
                    throw new UdoPersistException("Document " + udoi + " does not exist.");
                }
                return udoSchemaCollection.updateDoc(document, udoi);
            default: return null;
        }
    }
}
