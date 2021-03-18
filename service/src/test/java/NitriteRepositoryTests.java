import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.graphql.schemaParser.SchemaTree;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.service.UdoSchemaService;
import info.nemoworks.udo.service.UdoService;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.apache.commons.io.IOUtils;

@SpringBootTest
public class NitriteRepositoryTests {

    public String loadFromFile() throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/resources/room.json")));
    }

    @Test
    public void udoSaveTest() throws IOException, UdoPersistException {
        InputStream is = new FileInputStream("/Users/tangcong/Desktop/UDO/service/src/test/resources/purifier.json");
        String jsonTxt = IOUtils.toString(is, "UTF-8");
        // System.out.println(jsonTxt);
        JsonObject json = new Gson().fromJson(jsonTxt,JsonObject.class);
        SchemaTree schemaTree = new SchemaTree();
        schemaTree.traceSchemaTree(schemaTree.createSchemaTree(json));
//        myTypeRegistry.addSchema(schemaTree.createSchemaTree(json));
//        myRuntimeWiring.addNewSchemaDataFetcher(mongoTemplate,schemaTree.createSchemaTree(json),javers);
//        myTypeRegistry.toString();

    }

    @Test
    public void udoFindTest() throws IOException, UdoPersistException {

    }

}

