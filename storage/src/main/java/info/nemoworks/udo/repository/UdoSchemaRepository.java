package info.nemoworks.udo.repository;

import java.util.List;

import info.nemoworks.udo.model.UdoSchema;

//import javax.swing.text.Document;

public interface UdoSchemaRepository {
    List<UdoSchema> findAllSchemas();

    UdoSchema findSchemaById(String udoi);

    UdoSchema saveSchema(UdoSchema udoSchema);

    void deleteSchemaById(String udoi);

    UdoSchema updateSchema(UdoSchema udoSchema, String udoi);

}
