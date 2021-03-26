package info.nemoworks.udo.rest;

import com.alibaba.fastjson.JSONObject;
import info.nemoworks.udo.exception.UdoException;
import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.service.UdoDocService;
import org.dizitart.no2.Document;
import org.dizitart.no2.mapper.JacksonFacade;
import org.dizitart.no2.mapper.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/collection")
public class CollectionController {

    private static final Logger logger = LoggerFactory.getLogger(SchemaController.class);

    @Autowired
    private UdoDocService udoDocService;

    @GetMapping("/{collection}")
    public List<Document> allDocs(@PathVariable String collection) {
        logger.info("find all" + collection +"...");
        return udoDocService.findAllDocs(collection);
    }

    @PostMapping("/{collection}")
    public Document createDocument(@RequestBody JSONObject params, @PathVariable String collection) throws UdoException, UdoPersistException {
        logger.info("now saving a new doc...");
        //String udoi = params.getString("udoi");
        switch (collection) {
            case "Schema":
                String name = params.getString("schemaName");
                JSONObject content = params.getJSONObject("schemaContent");
                UdoSchema udoSchema = new UdoSchema("udoi" + name , name , content);
                return udoDocService.saveDoc(udoSchema);
            case "Document":
                String name1 = params.getString("name");
                String schema = params.getString("schema");
                JSONObject data = params.getJSONObject("data");
                return udoDocService.saveDoc(new Udo("udoi" + name1 , name1, schema, data));
            default: throw new UdoException("Illegal Collection Name");
        }
    }

    @DeleteMapping("/{collection}/{udoi}")
    public List<Document> deleteDocument(@PathVariable String collection, @PathVariable String udoi) throws UdoPersistException {
        logger.info("now deleting doc " + udoi + "...");
        return udoDocService.deleteDocById(udoi, collection);
    }

    @GetMapping("/{collection}/{udoi}")
    public Document getDocById(@PathVariable String collection, @PathVariable String udoi) throws UdoPersistException {
        logger.info("now finding doc by udoi...");
        return udoDocService.findDocById(udoi, collection);
    }

    @PutMapping("/{collection}/{udoi}")
    public Document updateDocument(@RequestBody JSONObject params, @PathVariable String udoi, @PathVariable String collection) throws UdoException {
//        String udoi = params.getString("udoi");
        logger.info("now updating doc " + udoi + "...");
        Document document;
        MapperFacade mapperFacade = new JacksonFacade();
        switch (collection) {
            case "Schema":
                String name = params.getString("schemaName");
                JSONObject content = params.getJSONObject("schemaContent");
                document = mapperFacade.parse(JSONObject.toJSONString(new UdoSchema(udoi, name, content)));
                return udoDocService.updateDocument(document, udoi, collection);
            case "Document":
                String name1 = params.getString("name");
                String schema = params.getString("schema");
                JSONObject data = params.getJSONObject("data");
                document = mapperFacade.parse(JSONObject.toJSONString(new Udo(udoi, name1, schema, data)));
                return udoDocService.updateDocument(document, udoi, collection);
            default: throw new UdoException("Illegal Collection Name");
        }
    }
}
