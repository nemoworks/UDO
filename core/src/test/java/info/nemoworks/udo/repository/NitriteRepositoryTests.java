package info.nemoworks.udo.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NitriteRepositoryTests {

    public String loadFromFile() throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/resources/test0.json")));
    }

    @Test
    public void udoSaveTest() throws IOException {

        String f = loadFromFile();
        System.out.print(f);
        assertNotNull(f);

    }

    @Test
    public void gsonTest() throws IOException{
        JsonObject obj = JsonParser.parseString(this.loadFromFile()).getAsJsonObject();
        System.out.println(obj.get("h").getAsString());
        System.out.println(obj.get("a").getAsJsonArray());
        System.out.println(obj.get("d").getAsJsonObject());
        System.out.println(obj.toString());
    }

}
