package info.nemoworks.udo.service;

import com.alibaba.fastjson.JSONObject;
import info.nemoworks.udo.exception.TablePersistException;
import info.nemoworks.udo.model.UTable;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.repository.h2.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {
    @Autowired
    private TableRepository tableRepository;

    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public List<UTable> findAllTables() {
        return tableRepository.findAll();
    }

    public UTable findTableByName(String name) throws TablePersistException {
        UTable UTable = tableRepository.findByTableName(name);
        if (UTable == null) {
            throw new TablePersistException("Table named: " + name + " is not existing.");
        }
        return UTable;
    }

    public void deleteTableByName(String name) throws TablePersistException {
        UTable UTable = tableRepository.findByTableName(name);
        if (UTable == null) {
            throw new TablePersistException("Table named: " + name + " is not existing.");
        }
        tableRepository.deleteByTableName(name);
    }

    public UTable saveUdoAsTable(Udo udo) throws TablePersistException{
        JSONObject obj = udo.getContent();
        String tableName = udo.getUdoi() + udo.getSchemaId();
        Translate translate = new Translate(obj);
        translate.startTrans();
        UTable UTable = new UTable(translate.getUTuples(), tableName);
        if (tableRepository.findByTableName(UTable.getTableName()) != null) {
            throw new TablePersistException("Table named: " + UTable.getTableName() + " already exists.");
        }
        return tableRepository.save(UTable);
    }
}
