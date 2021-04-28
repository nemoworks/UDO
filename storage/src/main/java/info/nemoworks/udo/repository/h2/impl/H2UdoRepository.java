package info.nemoworks.udo.repository.h2.impl;

import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.repository.UdoRepository;
import info.nemoworks.udo.repository.h2.manager.Translate;
import info.nemoworks.udo.repository.h2.manager.UDROManager;
import info.nemoworks.udo.repository.h2.exception.UDROPersistException;
import info.nemoworks.udo.repository.h2.model.UDRO;
import info.nemoworks.udo.repository.h2.model.UTuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class H2UdoRepository implements UdoRepository {
    @Autowired
    private UDROManager udroManager;

    private Udo fromUdro2Udo(UDRO udro) {
        List<UTuple> uTuples = udro.getUTuples();
        Translate translate = new Translate(uTuples);
        translate.startBackTrans();
        return new Udo(udro.getFirstTableName(), udro.getSecondTableName(), translate.getJsonObject());
    }

    @Override
    public Udo saveUdo(Udo udo, String schemaId) throws UdoPersistException, UDROPersistException {
        UDRO table = udroManager.saveUdo(udo);
//        List<UTuple> uTuples = table.getUTuples();
//        Translate translate = new Translate(uTuples);
//        translate.startBackTrans();
        String firstTableName = udo.getUdoi();
        String secondTableName = udo.getSchemaId();
        UDRO udro = udroManager.findByName(firstTableName + "_" + secondTableName);
//        String jStr = JSON.toJSONString(translate.getJsonObject());
//        return JSONObject.parseObject(jStr, udo.getClass());
//        System.out.println("uTable got: " + udro);
        return this.fromUdro2Udo(udro);
    }

    @Override
    public Udo findUdo(String udoi, String schemaId) throws UDROPersistException {
        return this.fromUdro2Udo(udroManager.findByName(udoi + "_" + schemaId));
    }

    @Override
    public List<Udo> findAllUdos(String schemaId) {
        List<UDRO> udros = udroManager.findAll();
        List<Udo> udos = new ArrayList<>();
        for (UDRO udro : udros) {
            udos.add(this.fromUdro2Udo(udro));
        }
        return udos;
    }

    @Override
    public void deleteUdo(String udoi, String schemaId) throws UDROPersistException {
        udroManager.deleteByName(udoi + "_" + schemaId);
    }

    @Override
    public Udo updateUdo(Udo udo, String udoi, String schemaId) throws UDROPersistException {
        UDRO UDRO = udroManager.updateUdo(udo);
        return this.fromUdro2Udo(UDRO);
//        return null;
    }
}
