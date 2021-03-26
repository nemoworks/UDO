package info.nemoworks.udo.repository.nitrite;

import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.repository.UdoDocCollection;
import info.nemoworks.udo.repository.UdoSchemaCollection;
import info.nemoworks.udo.repository.UdoSchemaRepository;
import org.dizitart.no2.*;
import org.dizitart.no2.filters.Filters;
import org.dizitart.no2.objects.ObjectFilter;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.dizitart.no2.util.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NitriteSchemaCollection implements UdoSchemaCollection {

    @Autowired
    private Nitrite db;

    private NitriteCollection nitriteCollection;

    public NitriteSchemaCollection(Nitrite db) {
        this.db = db;
        nitriteCollection = db.getCollection("Schema");
    }

    public Nitrite getDb() {
        return db;
    }

    @Override
    public List<Document> findAllDocs() {
        return nitriteCollection.find().toList();
    }

    @Override
    public Document saveDoc(Document doc) {
        WriteResult writeResult = nitriteCollection.insert(doc);
        NitriteId nitriteId = Iterables.firstOrDefault(writeResult);
        return nitriteCollection.getById(nitriteId);
    }

    @Override
    public Document findDocById(String id) {
        return nitriteCollection.find(Filters.eq("udoi", id)).firstOrDefault();
    }

    @Override
    public void deleteDocById(String id) {
        nitriteCollection.remove(Filters.eq("udoi", id));
    }

    @Override
    public Document findDocById(NitriteId id) {
        return nitriteCollection.getById(id);
    }

    @Override
    public Document updateDoc(Document doc, String id) {
        nitriteCollection.update(Filters.eq("udoi", id), doc);
        return nitriteCollection.find(Filters.eq("udoi", id)).firstOrDefault();
    }
}
