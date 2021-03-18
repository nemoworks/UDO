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

    public void saveSchema(UdoSchema schema) throws UdoPersistException {
        if (findSchemaById(schema.getUdoi()) != null) {
            throw new UdoPersistException("A schema with a same id already exists.");
        }
        if (findSchemaById(schema.getUdoi()).getSchemaName().equals(schema.getSchemaName())) {
            throw new UdoPersistException("A schema with a same name already exists.");
        }
        udoSchemaRepository.saveSchema(schema);
    }

    public UdoSchema findSchemaById(String udoi) throws UdoPersistException {
        UdoSchema schema = udoSchemaRepository.findSchemaById(udoi);
        if (schema == null) {
            throw new UdoPersistException("Udo " + udoi + " does not exist.");
        }
        return schema;
    }

    public List<UdoSchema> findAllSchemas() {
        return udoSchemaRepository.findAllSchemas();
    }

    public UdoSchema findSchemaByUdo(Udo udo) {
        return udoSchemaRepository.findSchemaById(udo.getSchema().getUdoi());
    }
}
