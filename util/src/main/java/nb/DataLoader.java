package nb;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class DataLoader {

    public static List<String> getLinesFromFileResource(String filePath) {

        List<String> lines = null;

        try {

            ClassLoader classLoader = DataLoader.class.getClassLoader();
            File file = new File(classLoader.getResource(filePath).getFile());
            lines = Files.readAllLines(file.toPath());

        } catch (Exception e) {
            throw new RuntimeException("Failed", e);
        }

        return lines;
    }

    public static char[][] parse2dCharArray(List<? extends CharSequence> lines) {
        if (lines.isEmpty()) {
            throw new IllegalArgumentException("Empty list");
        }

        int height = lines.size();
        int width = lines.get(0).length();
        char[][] result = new char[lines.get(0).length()][lines.size()];

        lines = lines.reversed();
        //System.out.println("width " + width + " height " + height);
        for (int y = 0; y < height ; y++) {
            //System.out.println(y);
            final CharSequence line = lines.get(y);

            for (int x = 0; x < width ; x++) {
                //System.out.println("setting " + x + ", " + y + " to " + line.charAt(x));
                result[x][y] = line.charAt(x);
            }
        }

        return result;
    }

}
