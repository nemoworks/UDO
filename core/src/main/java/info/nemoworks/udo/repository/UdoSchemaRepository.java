package info.nemoworks.udo.repository;

import info.nemoworks.udo.model.UdoSchema;

import java.util.List;

public interface UdoSchemaRepository {
    List<UdoSchema> findSchemaList();
    UdoSchema findSchema(String udoi);
    UdoSchema createSchema(UdoSchema udoSchema);
    void deleteSchema(String udoi);
}
