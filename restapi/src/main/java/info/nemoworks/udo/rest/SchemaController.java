package info.nemoworks.udo.rest;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import info.nemoworks.udo.exception.UdoPersistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.service.UdoSchemaService;

@RestController
public class SchemaController {

    @Autowired
    private UdoSchemaService schemaService;

    private static final Logger logger = LoggerFactory.getLogger(SchemaController.class);

    @GetMapping("/")
    public List<UdoSchema> allSchemas() {
        logger.info("find all schemas...");
        return schemaService.findAllSchemas();
    }

    @PostMapping("/new")
    public UdoSchema createSchema(@RequestBody JSONObject params, @RequestParam String udoi, @RequestParam String name) throws UdoPersistException {
        logger.info("now saving a new schema...");
        return schemaService.saveSchema(new UdoSchema(udoi, name, params));
    }

    @DeleteMapping("/{udoi}")
    public List<UdoSchema> deleteSchema(@PathVariable String udoi) {
        logger.info("now deleting schema " + udoi + "...");
        return schemaService.deleteSchemaById(udoi);
    }

    @PostMapping("/{udoi}")
    public UdoSchema getSchemaById(@PathVariable String udoi) throws UdoPersistException {
        logger.info("now finding schema by udoi...");
        return schemaService.findSchemaById(udoi);
    }

    @PostMapping("/update")
    public UdoSchema updateSchema(@RequestBody JSONObject params, @RequestParam String udoi, @RequestParam String name) throws UdoPersistException {
        logger.info("now updating schema " + udoi + "...");
        return schemaService.updateSchema(new UdoSchema(udoi, name, params), udoi);
    }
}
