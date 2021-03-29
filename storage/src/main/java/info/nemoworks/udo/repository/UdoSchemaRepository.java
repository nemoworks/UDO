package info.nemoworks.udo.repository;

import java.util.List;

import info.nemoworks.udo.model.UdoSchema;

//import javax.swing.text.Document;

public interface UdoSchemaRepository {
    List<UdoSchema> findAllSchemas(String collection);

    UdoSchema findSchemaById(String udoi, String collection);

    UdoSchema saveSchema(UdoSchema udoSchema, String collection);

    void deleteSchemaById(String udoi, String collection);

    UdoSchema updateSchema(UdoSchema udoSchema, String udoi, String collection);

}
