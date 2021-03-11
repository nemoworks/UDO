package com.udo.demo.service;

import com.udo.demo.model.UDOSchema;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectFilter;
import org.dizitart.no2.objects.ObjectRepository;
import org.springframework.stereotype.Service;

@Service
public class UDOSchemaService {

//    private  Nitrite db;
    private ObjectRepository<UDOSchema> schemaRepository;

    public ObjectRepository<UDOSchema> getSchemaRepository() {
        return schemaRepository;
    }

    public UDOSchemaService() {
        schemaRepository = UDOSchemaRepository.getSchemaRepository();
    }

    public void insertSchema(UDOSchema schema) {
        schemaRepository.insert(schema);
    }

    public void deleteSchema(ObjectFilter filter) {
        schemaRepository.remove(filter);
    }

    public void updateSchema(ObjectFilter filter, UDOSchema update) {
        schemaRepository.update(filter, update);
    }
}
