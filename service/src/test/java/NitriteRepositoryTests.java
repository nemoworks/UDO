//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertThrows;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import info.nemoworks.udo.exception.UdoPersistException;
//import info.nemoworks.udo.model.Udo;
//import info.nemoworks.udo.model.UdoSchema;
//import info.nemoworks.udo.service.UdoSchemaService;
//import info.nemoworks.udo.service.UdoService;
//import org.junit.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class NitriteRepositoryTests {
//
//    public String loadFromFile() throws IOException {
//        return new String(Files.readAllBytes(Paths.get("src/test/resources/room.json")));
//    }
//
//    @Test
//    public void udoSaveTest() throws IOException, UdoPersistException {
//
//        String f = loadFromFile();
//        System.out.print(f);
//        assertNotNull(f);
//        JSONObject jsonObject = JSON.parseObject(f);
//        UdoSchema room = new UdoSchema("3-15-1", jsonObject);
//        UdoSchemaService udoSchemaService = new UdoSchemaService();
//        udoSchemaService.insertSchema(room);
//        UdoSchema example = udoSchemaService.findSchemaByI("3-15-1");
//        System.out.println(example);
//        Udo doc = new Udo("3-15-1", room, JSON.parseObject("{content: null}"));
//        UdoService udoService = new UdoService();
//        udoService.insertDocument(doc);
//        System.out.println(doc);
//    }
//
//    @Test
//    public void udoFindTest() throws IOException, UdoPersistException {
//        String f = loadFromFile();
//        JSONObject jsonObject = JSON.parseObject(f);
//        UdoSchema room = new UdoSchema("3-15-1", jsonObject);
//        UdoSchemaService udoSchemaService = new UdoSchemaService();
//        udoSchemaService.insertSchema(room);
//        UdoSchema example = udoSchemaService.findSchemaByI("3-15-1");
//        Udo doc = new Udo("3-15-1", room, JSON.parseObject("{content: null}"));
//        UdoService udoService = new UdoService();
//        udoService.insertDocument(doc);
//        UdoSchema findByI = udoSchemaService.findSchemaByI("3-15-1");
//        System.out.println(findByI);
//        Udo findDoc = udoService.findDocument("3-15-1");
//        System.out.println(findDoc);
//        UdoSchema findOfDoc = udoService.findSchemaOfDoc(findDoc);
//        System.out.println(findOfDoc);
//    }
//
//}
//
