package info.nemoworks.udo.repository.h2;

import com.alibaba.fastjson.JSONObject;
import info.nemoworks.udo.exception.TablePersistException;
import info.nemoworks.udo.model.Udo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {
    @Autowired
    private UTableRepository uTableRepository;

    public TableService(UTableRepository uTableRepository) {
        this.uTableRepository = uTableRepository;
    }

    public List<UTable> findAllTables() {
        return uTableRepository.findAll();
    }

    public UTable findTableByName(String name) throws TablePersistException {
        UTable UTable = uTableRepository.findByTableName(name);
        if (UTable == null) {
            throw new TablePersistException("Table named: " + name + " is not existing.");
        }
        return UTable;
    }

    public void deleteTableByName(String name) throws TablePersistException {
        UTable UTable = uTableRepository.findByTableName(name);
        if (UTable == null) {
            throw new TablePersistException("Table named: " + name + " is not existing.");
        }
        uTableRepository.deleteByTableName(name);
    }

    public UTable saveUdoAsTable(Udo udo) throws TablePersistException{
//        JSONObject obj = udo.getContent();
        String firstTableName = udo.getUdoi();
        String secondTableName = udo.getSchemaId();
        Translate translate = new Translate(udo.getContent());
        translate.startTrans();
        UTable UTable = new UTable(translate.getUTuples(), firstTableName, secondTableName);
        if (uTableRepository.findByTableName(UTable.getTableName()) != null) {
            throw new TablePersistException("Table named: " + UTable.getTableName() + " already exists.");
        }
        return uTableRepository.save(UTable);
    }

    public UTable updateUdoAsTable(Udo udo) throws TablePersistException {
//        JSONObject obj = udo.getContent();
        String firstTableName = udo.getUdoi();
        String secondTableName = udo.getSchemaId();
        Translate translate = new Translate(udo.getContent());
        translate.startTrans();
        UTable UTable = new UTable(translate.getUTuples(), firstTableName, secondTableName);
        if (uTableRepository.findByTableName(UTable.getTableName()) == null) {
            throw new TablePersistException("Table named: " + UTable.getTableName() + " does not exist.");
        }
        return uTableRepository.save(UTable);
    }
}
