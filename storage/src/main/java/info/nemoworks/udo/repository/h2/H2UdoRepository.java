package info.nemoworks.udo.repository.h2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import info.nemoworks.udo.exception.TablePersistException;
import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.repository.UdoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;

public class H2UdoRepository implements UdoRepository {
    @Autowired
    private TableService tableService;

    private Udo fromUTable2Udo(UTable uTable) {
        List<UTuple> uTuples = uTable.getUTuples();
        Translate translate = new Translate(uTuples);
        translate.startBackTrans();
        return new Udo(uTable.getFirstTableName(), uTable.getSecondTableName(), translate.getJsonObject());
    }

    @Override
    public Udo saveUdo(Udo udo, String schemaId) throws UdoPersistException, TablePersistException {
        UTable table = tableService.saveUdoAsTable(udo);
//        List<UTuple> uTuples = table.getUTuples();
//        Translate translate = new Translate(uTuples);
//        translate.startBackTrans();
        String firstTableName = udo.getUdoi();
        String secondTableName = udo.getSchemaId();
        UTable uTable = tableService.findTableByName(firstTableName + "_" + secondTableName);
//        String jStr = JSON.toJSONString(translate.getJsonObject());
//        return JSONObject.parseObject(jStr, udo.getClass());
        return this.fromUTable2Udo(uTable);
    }

    @Override
    public Udo findUdo(String udoi, String schemaId) throws TablePersistException {
        return this.fromUTable2Udo(tableService.findTableByName(udoi + "_" + schemaId));
    }

    @Override
    public List<Udo> findAllUdos(String schemaId) {
        List<UTable> uTables = tableService.findAllTables();
        List<Udo> udos = new ArrayList<>();
        for(UTable uTable: uTables) {
            udos.add(this.fromUTable2Udo(uTable));
        }
        return udos;
    }

    @Override
    public void deleteUdo(String udoi, String schemaId) throws TablePersistException {
        tableService.deleteTableByName(udoi + "_" + schemaId);
    }

    @Override
    public Udo updateUdo(Udo udo, String udoi, String schemaId) throws TablePersistException {
        UTable uTable = tableService.saveUdoAsTable(udo);
        return this.fromUTable2Udo(uTable);
//        return null;
    }
}
