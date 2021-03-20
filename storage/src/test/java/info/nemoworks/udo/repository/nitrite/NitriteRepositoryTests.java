package info.nemoworks.udo.repository.nitrite;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.dizitart.no2.Nitrite;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.repository.UdoSchemaRepository;

@SpringBootTest
public class NitriteRepositoryTests {

    public String loadFromFile() throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/resources/room.json")));
    }

    @Test
    public void udoSaveTest() throws IOException, UdoPersistException {
        String f = loadFromFile();
        assertNotNull(f);
        JSONObject jsonObject = JSON.parseObject(f);
        UdoSchema room = new UdoSchema("3-15-1", "room", jsonObject);
        UdoSchemaRepository udoSchemaRepository = new NitriteSchemaRepository(Nitrite.builder().openOrCreate());
        UdoSchema savedRoom = udoSchemaRepository.saveSchema(room);
        System.out.println(savedRoom.toJson());
        assertNotNull(savedRoom);
    }

}