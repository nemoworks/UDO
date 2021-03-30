import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.graphql.schema.SchemaTree;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.repository.UdoRepository;
import info.nemoworks.udo.service.Translate;
import info.nemoworks.udo.service.UdoSchemaService;
import info.nemoworks.udo.service.UdoService;
import kotlin.reflect.jvm.internal.impl.util.collectionUtils.ScopeUtilsKt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.apache.commons.io.IOUtils;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
//@RunWith(SpringRunner.class)
public class NitriteServiceTests {

//    @Autowired
//    private UdoService udoService;

    public String loadFromFile() throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/resources/test.json")));
    }

    @Test
    public void udoSaveTest() throws IOException, UdoPersistException {
        InputStream is = new FileInputStream("/Users/tangcong/Desktop/UDO/service/src/test/resources/light.json");
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
    public void udoServiceTest() throws IOException, UdoPersistException {
        JSONObject content = new JSONObject();
        content.put("exam", "test data");
    }

    @Test
    public void translatingTest() throws IOException {
        JSONObject obj = JSON.parseObject(this.loadFromFile());
//        obj.put("arr", "{[{val: 1}, {val: 2}]}");
//        obj.put("obj", "{a: {b: c}}");
//        for (Map.Entry entry: obj.entrySet()) {
//            System.out.println(entry.getValue().getClass());
//            System.out.println(entry.getValue() instanceof JSONArray);
//            System.out.println(entry.getValue() instanceof JSONObject);
//        }
        Translate translate = new Translate(obj);
        translate.startTrans();
        System.out.println(translate.getTuples());
        translate.printTuples();
    }

    @Test
    public void graphqlTest() throws IOException {
        String light = new String(Files.readAllBytes(Paths.get("src/test/resources/light.json")));
        System.out.println(light);
        JSONObject jsonObject = JSON.parseObject(light);
        UdoSchema schema = new UdoSchema("udo1","purifier", jsonObject);
        System.out.println(schema.toJson());
    }

}

