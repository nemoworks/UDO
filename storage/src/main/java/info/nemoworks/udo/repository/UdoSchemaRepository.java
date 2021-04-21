package info.nemoworks.udo.repository;

import java.util.List;

import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.repository.h2.exception.UDROSchemaPersistException;

public interface UdoSchemaRepository {
    List<UdoSchema> findAllSchemas();

    UdoSchema findSchemaById(String udoi) throws UDROSchemaPersistException;

    UdoSchema saveSchema(UdoSchema udoSchema) throws UDROSchemaPersistException;

    void deleteSchemaById(String udoi) throws UDROSchemaPersistException;

    UdoSchema updateSchema(UdoSchema udoSchema, String udoi) throws UDROSchemaPersistException;

}
