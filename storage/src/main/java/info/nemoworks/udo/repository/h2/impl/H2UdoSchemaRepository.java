package info.nemoworks.udo.repository.h2.impl;

import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.repository.UdoSchemaRepository;
import info.nemoworks.udo.repository.h2.manager.Translate;
import info.nemoworks.udo.repository.h2.manager.UDROSchemaManager;
import info.nemoworks.udo.repository.h2.exception.UDROSchemaPersistException;
import info.nemoworks.udo.repository.h2.model.UDROSchema;
import info.nemoworks.udo.repository.h2.model.UTuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class H2UdoSchemaRepository implements UdoSchemaRepository {
    @Autowired
    UDROSchemaManager udroSchemaManager;

    private UdoSchema fromUdro2Udo(UDROSchema udroSchema) {
        List<UTuple> uTuples = udroSchema.getUTuples();
        Translate translate = new Translate(uTuples);
        translate.startBackTrans();
        return new UdoSchema(udroSchema.getTableName(), translate.getJsonObject());
    }

    @Override
    public List<UdoSchema> findAllSchemas() {
        List<UDROSchema> schemas = udroSchemaManager.findAll();
        List<UdoSchema> udoSchemas = new ArrayList<>();
        for(UDROSchema udroSchema : schemas) {
            udoSchemas.add(this.fromUdro2Udo(udroSchema));
        }
        return udoSchemas;
    }

    @Override
    public UdoSchema findSchemaById(String udoi) throws UDROSchemaPersistException {
        return this.fromUdro2Udo(udroSchemaManager.findByName(udoi));
    }

    @Override
    public UdoSchema saveSchema(UdoSchema udoSchema) throws UDROSchemaPersistException {
        UDROSchema sav = udroSchemaManager.saveUdoSchema(udoSchema);
        String tableName = udoSchema.getUdoi();
        UDROSchema udroSchema = udroSchemaManager.findByName(tableName);
        return fromUdro2Udo(udroSchema);
    }

    @Override
    public void deleteSchemaById(String udoi) throws UDROSchemaPersistException {
        udroSchemaManager.deleteByName(udoi);
    }

    @Override
    public UdoSchema updateSchema(UdoSchema udoSchema, String udoi) throws UDROSchemaPersistException {
        UDROSchema udroSchema = udroSchemaManager.updateUdoSchema(udoSchema);
        return this.fromUdro2Udo(udroSchema);
    }
}
