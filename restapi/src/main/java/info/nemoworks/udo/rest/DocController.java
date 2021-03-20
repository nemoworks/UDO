//package info.nemoworks.udo.rest;

import com.alibaba.fastjson.JSONObject;
import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.service.UdoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//
//@RestController
//@RequestMapping("/api/documents")
//public class DocController {
//    @Autowired
//    private UdoService udoService;
//
//    private static final Logger logger = LoggerFactory.getLogger(DocController.class);

//    @GetMapping("/")
//    public List<Udo> allDocs() {
//        logger.info("find all docs...");
//        return udoService.findAllUdos();
//    }

//    @PostMapping("/new")
//    public UdoSchema createSchema(@RequestBody JSONObject params, @RequestParam String udoi, @RequestParam String name) throws UdoPersistException {
//        logger.info("now saving a new schema...");
//        return schemaService.saveSchema(new UdoSchema(udoi, name, params));
//    }
//
//    @DeleteMapping("/{udoi}")
//    public List<UdoSchema> deleteSchema(@PathVariable String udoi) {
//        logger.info("now deleting schema " + udoi + "...");
//        return schemaService.deleteSchemaById(udoi);
//    }
//
//    @PostMapping("/{udoi}")
//    public UdoSchema getSchemaById(@PathVariable String udoi) throws UdoPersistException {
//        logger.info("now finding schema by udoi...");
//        return schemaService.findSchemaById(udoi);
//    }
//}
