package info.nemoworks.udo;

import com.google.gson.JsonObject;
import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.repository.h2.UDROPersistException;
import info.nemoworks.udo.service.UdoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DocController {
    @Autowired
    private UdoService udoService;

    private static final Logger logger = LoggerFactory.getLogger(DocController.class);

    @GetMapping("/documents/{collection}")
    public List<Udo> allDocs(@PathVariable String collection) {
        logger.info("find all docs...");
        return udoService.findAllUdos(collection);
    }

    @PostMapping("/documents")
    public Udo createDoc(@RequestBody JsonObject params) throws UdoPersistException, UDROPersistException {
        logger.info("now saving a new doc...");
        String udoi = params.get("udoi").getAsString();
//        String name = params.getString("name");
        String schemaId = params.get("schemaId").getAsString();
        JsonObject data = params.get("content").getAsJsonObject();
//        String collection = params.getString("collection");
        return udoService.saveUdo(new Udo(udoi, schemaId, data));
    }

    @DeleteMapping("/documents/{collection}/{udoi}")
    public List<Udo> deleteDoc(@PathVariable String collection, @PathVariable String udoi) throws UdoPersistException, UDROPersistException {
        logger.info("now deleting doc " + udoi + "...");
        return udoService.deleteUdoById(udoi, collection);
    }

    @GetMapping("/documents/{collection}/{udoi}")
    public Udo getDocById(@PathVariable String collection, @PathVariable String udoi) throws UdoPersistException, UDROPersistException {
        logger.info("now finding doc by udoi...");
        return udoService.findUdoById(udoi, collection);
    }

    @PutMapping("/documents/{udoi}")
    public Udo updateUdo(@RequestBody JsonObject params, @PathVariable String udoi) throws UdoPersistException, UDROPersistException {
//        String udoi = params.getString("udoi");
        logger.info("now updating doc " + udoi + "...");
//        String name = params.getString("name");
        String schemaId = params.get("schemaId").getAsString();
        JsonObject data = params.get("data").getAsJsonObject();
        return udoService.updateUdo(new Udo(udoi, schemaId, data), udoi);
    }
}
