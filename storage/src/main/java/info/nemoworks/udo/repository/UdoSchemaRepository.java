package info.nemoworks.udo.repository;

import java.util.List;

import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.repository.h2.exception.UdroSchemaPersistException;

public interface UdoSchemaRepository {
    List<UdoSchema> findAllSchemas();

    UdoSchema findSchemaById(String udoi) throws UdroSchemaPersistException;

    UdoSchema saveSchema(UdoSchema udoSchema) throws UdroSchemaPersistException;

    void deleteSchemaById(String udoi) throws UdroSchemaPersistException;

    UdoSchema updateSchema(UdoSchema udoSchema, String udoi) throws UdroSchemaPersistException;

}
