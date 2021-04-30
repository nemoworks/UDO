package info.nemoworks.udo.service;

import java.util.List;

import info.nemoworks.udo.repository.h2.exception.UdroSchemaPersistException;
import org.springframework.stereotype.Service;

import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.repository.UdoSchemaRepository;

@Service
public class UdoSchemaService {

    private final UdoSchemaRepository udoSchemaRepository;

    public UdoSchemaService(UdoSchemaRepository udoSchemaRepository) {
        this.udoSchemaRepository = udoSchemaRepository;
    }

    public UdoSchema saveSchema(UdoSchema schema) throws UdoPersistException, UdroSchemaPersistException {

//        if (udoSchemaRepository.findSchemaById(schema.getUdoi()) != null) {
//            throw new UdoPersistException("A schema with a same id already exists.");
//        }
        return udoSchemaRepository.saveSchema(schema);
    }

    public UdoSchema findSchemaById(String udoi) throws UdoPersistException, UdroSchemaPersistException {
        UdoSchema schema = udoSchemaRepository.findSchemaById(udoi);
        if (schema == null) {
            throw new UdoPersistException("Schema " + udoi + " does not exist.");
        }
        return schema;
    }

    public List<UdoSchema> findAllSchemas() {
        return udoSchemaRepository.findAllSchemas();
    }

    public UdoSchema findSchemaByUdo(Udo udo) throws UdroSchemaPersistException {
        return udoSchemaRepository.findSchemaById(udo.getSchemaId());
    }

    public List<UdoSchema> deleteSchemaById(String udoi) throws UdoPersistException, UdroSchemaPersistException {
        UdoSchema schema = udoSchemaRepository.findSchemaById(udoi);
        if (schema == null) {
            throw new UdoPersistException("Udo " + udoi + " does not exist.");
        }
        udoSchemaRepository.deleteSchemaById(udoi);
        return udoSchemaRepository.findAllSchemas();
    }

    public UdoSchema updateSchema(UdoSchema udoSchema, String udoi) throws UdoPersistException, UdroSchemaPersistException {

        UdoSchema schema = udoSchemaRepository.findSchemaById(udoi);
        if (schema == null) {
            throw new UdoPersistException("Udo " + udoi + " does not exist.");
        }
        return udoSchemaRepository.updateSchema(udoSchema, udoi);
    }
}