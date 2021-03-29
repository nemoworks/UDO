package info.nemoworks.udo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.repository.UdoSchemaRepository;

@Service
public class UdoSchemaService {

    @Autowired
    private UdoSchemaRepository udoSchemaRepository;

    public UdoSchemaService(UdoSchemaRepository udoSchemaRepository) {
        this.udoSchemaRepository = udoSchemaRepository;
    }

    public UdoSchema saveSchema(UdoSchema schema) throws UdoPersistException {

//        UdoSchema oldSchema = udoSchemaRepository.findSchemaById(schema.getUdoi());
        if (udoSchemaRepository.findSchemaById(schema.getUdoi(), schema.getCollection()) != null) {
            throw new UdoPersistException("A schema with a same id already exists.");
        }
//        if (udoSchemaRepository.findSchemaById(schema.getUdoi()).getSchemaName().equals(schema.getSchemaName())) {
//            throw new UdoPersistException("A schema with a same name already exists.");
//        }
        return udoSchemaRepository.saveSchema(schema, schema.getCollection());
    }

    public UdoSchema findSchemaById(String udoi, String collection) throws UdoPersistException {
        UdoSchema schema = udoSchemaRepository.findSchemaById(udoi, collection);
        if (schema == null) {
            throw new UdoPersistException("Schema " + udoi + " does not exist.");
        }
        return schema;
    }

    public List<UdoSchema> findAllSchemas(String collection) {
        return udoSchemaRepository.findAllSchemas(collection);
    }

    public UdoSchema findSchemaByUdo(Udo udo, String collection) {
        return udoSchemaRepository.findSchemaById(udo.getSchema(), collection);
    }

    public List<UdoSchema> deleteSchemaById(String udoi, String collection) throws UdoPersistException {
        UdoSchema schema = udoSchemaRepository.findSchemaById(udoi, collection);
        if (schema == null) {
            throw new UdoPersistException("Udo " + udoi + " does not exist.");
        }
        udoSchemaRepository.deleteSchemaById(udoi, collection);
        return udoSchemaRepository.findAllSchemas(collection);
    }

    public UdoSchema updateSchema(UdoSchema udoSchema, String udoi) throws UdoPersistException {

        UdoSchema schema = udoSchemaRepository.findSchemaById(udoi, udoSchema.getCollection());
        if (schema == null) {
            throw new UdoPersistException("Udo " + udoi + " does not exist.");
        }
        return udoSchemaRepository.updateSchema(udoSchema, udoi, udoSchema.getCollection());
    }
}