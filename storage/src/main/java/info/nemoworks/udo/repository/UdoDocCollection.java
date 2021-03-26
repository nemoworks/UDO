package info.nemoworks.udo.repository;

import org.dizitart.no2.Document;
import org.dizitart.no2.NitriteId;

import javax.print.Doc;
import java.util.List;

public interface UdoDocCollection {
    List<Document> findAllDocs();

    Document saveDoc(Document doc);

    Document findDocById(String id);

    Document findDocById(NitriteId id);

    void deleteDocById(String id);

    Document updateDoc(Document doc, String id);
}
