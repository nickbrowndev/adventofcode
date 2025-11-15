package nb;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
/**
 * https://adventofcode.com/2024/day/3#part2
 */

public class Day3 {


    private List<String> lines;
    private static final String REGEX = "mul\\(\\d+,\\d+\\)";
    private static final Pattern pattern = Pattern.compile(REGEX);
    public record Multiplication(int value1, int value2) {

        public int total() {
            return value1() * value2();
        }
    };

    public Day3(String path) {
        lines = new DataLoader().getLinesFromFileResource(path);
    }

    /**
     * https://adventofcode.com/2024/day/3
     */
    public Integer doPartOne() {
      
        List<String> commands = findCommands(String.join("", lines));
        commands.forEach(System.out::println);
        List<Multiplication> mults = commands.stream().map(val -> parseCommand(val)).collect(Collectors.toList());

        int total = mults.stream().mapToInt(v -> v.total()).sum();
        return total;

        
    }

    public Multiplication parseCommand(String command) {
        String[] values = command.split("[(,)]");
        //Arrays.stream(values).forEach(System.out::println);
        return new Multiplication(Integer.valueOf(values[1]), Integer.valueOf(values[2]));
    }

    /**
     * https://adventofcode.com/2024/day/3#part2
     */
    public Integer doPartTwo() {

       return -1; 
    }
    
    private List<String> findCommands(String s) {
        List<String> allMatches = new ArrayList<String>();
         Matcher m = pattern.matcher(s);
         while (m.find()) {
               allMatches.add(m.group());
         }
        return allMatches;
    }


}
