package info.nemoworks.udo.rest;

import com.alibaba.fastjson.JSONObject;
import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.service.UdoSchemaService;
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
    public Udo createDoc(@RequestBody JSONObject params) throws UdoPersistException {
        logger.info("now saving a new doc...");
        String udoi = params.getString("udoi");
        String name = params.getString("name");
        String schema = params.getString("schema");
        JSONObject data = params.getJSONObject("data");
        String collection = params.getString("collection");
        return udoService.saveUdo(new Udo(udoi, name, schema, data, collection));
    }

    @DeleteMapping("/documents/{collection}/{udoi}")
    public List<Udo> deleteDoc(@PathVariable String collection, @PathVariable String udoi) throws UdoPersistException {
        logger.info("now deleting doc " + udoi + "...");
        return udoService.deleteUdoById(udoi, collection);
    }

    @GetMapping("/documents/{collection}/{udoi}")
    public Udo getDocById(@PathVariable String collection, @PathVariable String udoi) throws UdoPersistException {
        logger.info("now finding doc by udoi...");
        return udoService.findUdoById(udoi, collection);
    }

    @PutMapping("/documents/{collection}/{udoi}")
    public Udo updateUdo(@RequestBody JSONObject params, @PathVariable String collection, @PathVariable String udoi) throws UdoPersistException {
//        String udoi = params.getString("udoi");
        logger.info("now updating doc " + udoi + "...");
        String name = params.getString("name");
        String schema = params.getString("schema");
        JSONObject data = params.getJSONObject("data");
        return udoService.updateUdo(new Udo(udoi, name, schema, data, collection), udoi);
    }
}
