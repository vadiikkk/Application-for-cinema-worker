package cinema.jsonDao;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class JsonDao<Entity> {
    private final Class<Entity> entityType;

    private final File JSON_FILE;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    public JsonDao(String fileName, Class<Entity> entityType) {
        Path filePath = Paths.get(System.getProperty("user.dir")).
                resolve("src").
                resolve("main").
                resolve("resources").
                resolve(fileName);
        JSON_FILE = new File(filePath.toString());

        this.entityType = entityType;

        OBJECT_MAPPER.registerModule(new JavaTimeModule());
    }

    public List<Entity> getAll() throws IOException {
        JavaType type = OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, entityType);
        return OBJECT_MAPPER.readValue(JSON_FILE, type);
    }

    public void add(Entity entity) throws IOException {
        List<Entity> entities = getAll();
        entities.add(entity);
        String entityInJson = OBJECT_MAPPER.writeValueAsString(entities);
        FileWriter writer = new FileWriter(JSON_FILE, false);
        writer.write(entityInJson);
        writer.close();
    }

    public void remove(int index) throws IOException {
        List<Entity> entities = getAll();
        entities.remove(index);
        String entityInJson = OBJECT_MAPPER.writeValueAsString(entities);
        FileWriter writer = new FileWriter(JSON_FILE, false);
        writer.write(entityInJson);
        writer.close();
    }
}
