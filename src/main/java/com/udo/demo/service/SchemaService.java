package com.udo.demo.service;

import com.udo.demo.model.Schema;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectFilter;
import org.dizitart.no2.objects.ObjectRepository;
import org.springframework.stereotype.Service;

@Service
public class SchemaService {

    private  Nitrite db;
    private ObjectRepository<Schema> schemaRepository;

    public ObjectRepository<Schema> getSchemaRepository() {
        return schemaRepository;
    }

    public SchemaService() {
        db = Nitrite.builder()
                .openOrCreate();
        schemaRepository = db.getRepository(Schema.class);
    }

    public void insertSchema(Schema schema) {
        schemaRepository.insert(schema);
    }

    public void deleteSchema(ObjectFilter filter) {
        schemaRepository.remove(filter);
    }

    public void updateSchema(ObjectFilter filter, Schema update) {
        schemaRepository.update(filter, update);
    }
}
