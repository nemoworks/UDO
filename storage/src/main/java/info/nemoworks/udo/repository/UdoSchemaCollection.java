package info.nemoworks.udo.repository;

import org.dizitart.no2.Document;
import org.dizitart.no2.NitriteId;

import java.util.List;

public interface UdoSchemaCollection {
    List<Document> findAllDocs();

    Document saveDoc(Document doc);

    Document findDocById(String id);

    void deleteDocById(String id);

    Document findDocById(NitriteId id);

    Document updateDoc(Document doc, String id);
}
