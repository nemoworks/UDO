package info.nemoworks.udo.repository.h2;

import info.nemoworks.udo.model.UdoSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UDROSchemaManager {
    @Autowired
    UDROSchemaRepository UDROSchemaRepository;
    public UDROSchemaManager(UDROSchemaRepository UDROSchemaRepository) {
        this.UDROSchemaRepository = UDROSchemaRepository;
    }

    public List<UDROSchema> findAll() {
        return UDROSchemaRepository.findAll();
    }

    public UDROSchema findByName(String name) throws UDROSchemaPersistException {
        UDROSchema UDROSchema = UDROSchemaRepository.findByTableName(name);
        if (UDROSchema == null) {
            throw new UDROSchemaPersistException("UDROSchema " + name + " does not exist.");
        }
        return UDROSchema;
    }

    public void deleteByName(String name) throws UDROSchemaPersistException {
        UDROSchema UDROSchema = UDROSchemaRepository.findByTableName(name);
        if (UDROSchema == null) {
            throw new UDROSchemaPersistException("UDROSchema " + name + " does not exist.");
        }
        UDROSchemaRepository.deleteByTableName(name);
    }

    public UDROSchema saveUdoSchema(UdoSchema udoSchema) throws UDROSchemaPersistException {
        // JSONObject obj = udo.getContent();
        String tableName = udoSchema.getUdoi();
        Translate translate = new Translate(udoSchema.getSchemaContent());
        translate.startTrans();
        UDROSchema UDROSchema = new UDROSchema(tableName, translate.getUTuples());
        if (UDROSchemaRepository.findByTableName(UDROSchema.getTableName()) != null) {
            throw new UDROSchemaPersistException("UDROSchema: " + UDROSchema.getTableName() + " already exists.");
        }
        System.out.println("save UDROSchema: " + UDROSchema);
        return UDROSchemaRepository.save(UDROSchema);
    }

    public UDROSchema updateUdoSchema(UdoSchema udoSchema) throws UDROSchemaPersistException {
        // JSONObject obj = udo.getContent();
        String tableName = udoSchema.getUdoi();
        Translate translate = new Translate(udoSchema.getSchemaContent());
        translate.startTrans();
        UDROSchema UDROSchema = new UDROSchema(tableName, translate.getUTuples());
        if (UDROSchemaRepository.findByTableName(UDROSchema.getTableName()) == null) {
            throw new UDROSchemaPersistException("UDROSchema " + UDROSchema.getTableName() + " does not exist.");
        }
        UDROSchemaRepository.deleteByTableName(tableName);
        return UDROSchemaRepository.save(UDROSchema);
    }
}
