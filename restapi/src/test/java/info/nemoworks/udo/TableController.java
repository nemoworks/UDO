package info.nemoworks.udo;

import com.alibaba.fastjson.JSONObject;
import info.nemoworks.udo.repository.h2.TablePersistException;
import info.nemoworks.udo.repository.h2.UTable;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.repository.h2.TableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TableController {
    private static final Logger logger = LoggerFactory.getLogger(TableController.class);
    @Autowired
    private TableService tableService;

    @PostMapping("/tables")
    public UTable createTableWithUdo(@RequestBody JSONObject params) throws TablePersistException {
        logger.info("now saving a new table...");
        String udoi = params.getString("udoi");
//        String name = params.getString("name");
        String schemaId = params.getString("schemaId");
        JSONObject data = params.getJSONObject("content");
        return tableService.saveUdoAsTable(new Udo(udoi, schemaId, data));
    }

    @GetMapping("/tables")
    public List<UTable> getTables() {
        logger.info("now finding all tables...");
        return tableService.findAllTables();
    }

    @DeleteMapping("/tables/{name}")
    public void deleteTable(@PathVariable String name) throws TablePersistException {
        logger.info("now deleting table: " + name + " ...");
        tableService.deleteTableByName(name);
    }

    @GetMapping("/tables/{name}")
    public UTable getTableByName(@PathVariable String name) throws TablePersistException {
        logger.info("now finding table named: " + name + " ...");
        return tableService.findTableByName(name);
    }
}
