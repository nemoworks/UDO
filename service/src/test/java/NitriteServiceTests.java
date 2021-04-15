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
import info.nemoworks.udo.graphql.schema.SchemaTree;
import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.repository.h2.Translate;
//import javafx.util.Pair;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.apache.commons.io.IOUtils;

@SpringBootTest
//@RunWith(SpringRunner.class)
public class NitriteServiceTests {

//    @Autowired
//    private UdoService udoService;

    public String loadFromFile() throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/resources/test0.json")));
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
        System.out.println(translate.getUTuples());
        translate.printTuples();
        translate.startBackTrans();
        System.out.println(translate.getJsonObject());
    }

    @Test
    public void traverseTest() throws IOException {
        String prefix = "a.b.c";
        StringBuffer sb = new StringBuffer(prefix);

        String reverse = sb.reverse().toString();
        String objName = prefix.substring(prefix.length() - reverse.indexOf("."));
        System.out.println(objName);
        System.out.println(prefix.substring(0, prefix.length() - reverse.indexOf(".") - 1));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("1", 1);
        jsonObject.put("1", 2);
        System.out.println(jsonObject);
    }

    @Test
    public void traverseTest2() throws IOException {
        String prefix = "a[0].c";
        StringBuffer sb = new StringBuffer(prefix);
        String reverse = sb.reverse().toString();
        int indexArr = reverse.indexOf("]");
        int indexLeftArr = reverse.indexOf("[");
        int indexDot = reverse.indexOf(".");
        if (indexArr < indexDot) {
            int endIndex = prefix.length() - indexDot;
            String ArrName = prefix.substring(endIndex, prefix.length() - indexLeftArr - 1);
            prefix = prefix.substring(0, endIndex - 1);
            System.out.println(ArrName + " " + prefix);
        } else {
            int endIndex = prefix.length() - indexArr + 1;
            String ObjName = prefix.substring(endIndex);
            prefix = prefix.substring(0, endIndex - 1);
            System.out.println(ObjName + " " + prefix);
            System.out.println("a[0]".substring(0, "a[0]".indexOf("[")));
        }
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

