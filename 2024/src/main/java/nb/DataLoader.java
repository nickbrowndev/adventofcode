package nb;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class DataLoader {

    List<String> getLinesFromFileResource(String filePath) {

        List<String> lines = null;

        try {

            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(filePath).getFile());
            lines = Files.readAllLines(file.toPath());

        } catch (Exception e) {
            throw new RuntimeException("Failed", e);
        }

        return lines;
    }
}
