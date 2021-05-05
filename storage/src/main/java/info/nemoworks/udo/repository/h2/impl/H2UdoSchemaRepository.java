package info.nemoworks.udo.repository.h2.impl;

import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.repository.UdoSchemaRepository;
import info.nemoworks.udo.repository.h2.manager.Translate;
import info.nemoworks.udo.repository.h2.manager.UdroSchemaManager;
import info.nemoworks.udo.repository.h2.exception.UdroSchemaPersistException;
import info.nemoworks.udo.repository.h2.model.UdroSchema;
import info.nemoworks.udo.repository.h2.model.UTuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class H2UdoSchemaRepository implements UdoSchemaRepository {
    @Autowired
    UdroSchemaManager udroSchemaManager;

    private UdoSchema fromUdro2Udo(UdroSchema udroSchema) {
        List<UTuple> uTuples = udroSchema.getUTuples();
//        System.out.println("translating tuples: ");
//        for(UTuple uTuple: udroSchema.getUTuples()) uTuple.printTuple();
        Translate translate = new Translate(uTuples);
        translate.startBackTrans();
//        System.out.println("2Udo: " + translate.getJsonObject().toString());
        return new UdoSchema(udroSchema.getTableName(), translate.getJsonObject());
    }

    @Override
    public List<UdoSchema> findAllSchemas() {
        List<UdroSchema> schemas = udroSchemaManager.findAll();
        List<UdoSchema> udoSchemas = new ArrayList<>();
        for (UdroSchema udroSchema : schemas) {
            udoSchemas.add(this.fromUdro2Udo(udroSchema));
        }
        return udoSchemas;
    }

    @Override
    public UdoSchema findSchemaById(String udoi) throws UdroSchemaPersistException {
        return this.fromUdro2Udo(udroSchemaManager.findByName(udoi));
    }

    @Override
    public UdoSchema saveSchema(UdoSchema udoSchema) throws UdroSchemaPersistException {
        UdroSchema sav = udroSchemaManager.saveUdoSchema(udoSchema);
        String tableName = udoSchema.getUdoi();
        UdroSchema udroSchema = udroSchemaManager.findByName(tableName);
//        System.out.println("find udroschema: ");
//        for (UTuple uTuple: udroSchema.getUTuples()) uTuple.printTuple();
        return fromUdro2Udo(udroSchema);
    }

    @Override
    public void deleteSchemaById(String udoi) throws UdroSchemaPersistException {
        udroSchemaManager.deleteByName(udoi);
    }

    @Override
    public UdoSchema updateSchema(UdoSchema udoSchema, String udoi) throws UdroSchemaPersistException {
        UdroSchema udroSchema = udroSchemaManager.updateUdoSchema(udoSchema);
        return this.fromUdro2Udo(udroSchema);
    }
}
