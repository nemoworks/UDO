package info.nemoworks.udo.repository.nitrite;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.UdoSchema;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NitriteRepositoryTests {

   // private RepositoryFactory repositoryFactory = new RepositoryFactory();
    public String loadFromFile() throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/resources/room.json")));
    }

    @Test
    public void udoSaveTest() throws IOException, UdoPersistException {
        String f = loadFromFile();
        assertNotNull(f);
        JSONObject jsonObject = JSON.parseObject(f);
        UdoSchema room = new UdoSchema("3-15-1", jsonObject);
        NitriteSchemaRepository nitriteSchemaRepository = new NitriteSchemaRepository();
        UdoSchema res = nitriteSchemaRepository.createSchema(room);
        System.out.println(res.toString());
//        UdoSchemaRepository udoSchemaRepository = repositoryFactory.getRepository(DBType.NitruteRepository);
//        System.out.println(udoSchemaRepository.createSchema(room));
        //System.out.println(udoSchemaRepository.findSchema(room.getUdoi()));
//        Udo doc = new Udo("3-15-1", room, JSON.parseObject("{content: null}"));
//        UDONitriteRepository udoNitriteRepository = new UDONitriteRepository();
//        doc = udoNitriteRepository.saveUdo(doc);
//        System.out.println("a");
    }

}
