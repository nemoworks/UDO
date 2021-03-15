import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NitriteRepositoryTests {

    public String loadFromFile() throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/resources/room.json")));
    }

    @Test
    public void udoSaveTest() throws IOException {

        String f = loadFromFile();
        System.out.print(f);
        assertNotNull(f);

    }

}

