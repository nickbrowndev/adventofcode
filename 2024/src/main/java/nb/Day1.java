package nb;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day1 {


    private List<String> lines;
    private List<Integer> lefts;
    private List<Integer> rights;

    public Day1(String path) {
        lines = getLinesFromFileResource(path);

        lefts = new ArrayList<>(lines.size());
        rights = new ArrayList<>(lines.size());
        for (String line : lines) {
            String[] values = line.split("\\s+");

            lefts.add(Integer.valueOf(values[0]));
            rights.add(Integer.valueOf(values[1]));
        }
    }

    public Integer doPartOne() {
        
        Comparator<Integer> comparator = Integer::compare;
        lefts.sort(comparator);
        rights.sort(comparator);

        int diff = 0;
        for (int i = 0; i < lines.size(); i++) {
            
            diff += Math.abs(lefts.get(i) - rights.get(i));
            //System.out.println("Comparing " + lefts.get(i) + " " + rights.get(i) + " " + diff);
        }

        return diff;
       // :JdtShowLogs to show LSP logs 
    }

    public Integer doPartTwo() {

        Map<Integer, Long> counts = rights.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        int similarity = 0;

        for (int left : lefts) {
            similarity += left * counts.getOrDefault(left, 0L);
        }

        return similarity;
    }

    private List<String> getLinesFromFileResource(String filePath) {

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


