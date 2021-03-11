package com.udo.demo.service;

import com.udo.demo.model.UDOSchema;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UDOSchemaRepository {
    private static Nitrite db = Nitrite.builder()
            .openOrCreate();

    private static ObjectRepository<UDOSchema> schemaRepository;

    public static ObjectRepository<UDOSchema> getSchemaRepository() {
        schemaRepository = db.getRepository(UDOSchema .class);
        return schemaRepository;
    }
}
