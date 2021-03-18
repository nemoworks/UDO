package info.nemoworks.udo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.service.UdoSchemaService;

@RestController
public class SchemaController {

    @Autowired
    private UdoSchemaService schemaService;

    @GetMapping("/")
    public List<UdoSchema> allSchemas() {
        return schemaService.findAllSchemas();
    }

}
