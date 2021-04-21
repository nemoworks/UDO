//package info.nemoworks.udo;
//import com.google.gson.JsonObject;
//import info.nemoworks.udo.repository.h2.exception.UDROPersistException;
//import info.nemoworks.udo.repository.h2.model.UDRO;
//import info.nemoworks.udo.model.Udo;
//import info.nemoworks.udo.repository.h2.manager.UDROManager;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//public class TableController {
//    private static final Logger logger = LoggerFactory.getLogger(TableController.class);
//    @Autowired
//    private UDROManager UDROManager;
//
//    @PostMapping("/tables")
//    public UDRO createTableWithUdo(@RequestBody JsonObject params) throws UDROPersistException {
//        logger.info("now saving a new table...");
//        String udoi = params.get("udoi").getAsString();
////        String name = params.getString("name");
//        String schemaId = params.get("schemaId").getAsString();
//        JsonObject data = params.get("content").getAsJsonObject();
//        return UDROManager.saveUdo(new Udo(udoi, schemaId, data));
//    }
//
//    @GetMapping("/tables")
//    public List<UDRO> getTables() {
//        logger.info("now finding all tables...");
//        return UDROManager.findAll();
//    }
//
//    @DeleteMapping("/tables/{name}")
//    public void deleteTable(@PathVariable String name) throws UDROPersistException {
//        logger.info("now deleting table: " + name + " ...");
//        UDROManager.deleteByName(name);
//    }
//
//    @GetMapping("/tables/{name}")
//    public UDRO getTableByName(@PathVariable String name) throws UDROPersistException {
//        logger.info("now finding table named: " + name + " ...");
//        return UDROManager.findByName(name);
//    }
//}
