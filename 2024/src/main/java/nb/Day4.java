package nb;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * https://adventofcode.com/2024/day/4
 * https://adventofcode.com/2024/day/4#part2
 */

public class Day4 {


    private List<String> lines;
    private final String TEST_STRING = "XMAS";


    public Day4(String path) {
        lines = new DataLoader().getLinesFromFileResource(path);
    }

    /**
     * https://adventofcode.com/2024/day/4
     */
    public Integer doPartOne() {


        Board board = new Board(lines);

        int count = 0;

        Walker walker = new Walker(board);
        
        Tester tester = new Tester(TEST_STRING);

        for (int x = 0; x < board.getWidth(); x++) {
            String line = walker.walk(new Point(x, 0), Direction.N);

            int finds = tester.findIn(line);

            count += finds;
            //System.out.println("Searching " + line + " NS. Finds " + finds + " count " + count);
        }


        for (int y = 0; y < board.getHeight(); y++) {
            String line = walker.walk(new Point(0, y), Direction.E);

            int finds = tester.findIn(line);
            count += finds;
            //System.out.println("Searching " + line + " EW. Finds " + finds + " count " + count);
        }

        
        for (int x = 0; x < board.getWidth(); x++) {
            String line = walker.walk(new Point(x, 0), Direction.NE);

            int finds = tester.findIn(line);
            count += finds;

            //System.out.println("Searching " + line + " NESW. Finds " + finds + " count " + count);
            String line2 = walker.walk(new Point(x, 0), Direction.NW);

            int finds2 = tester.findIn(line2);
            count += finds2;
            //System.out.println("Searching " + line2 + " NWSE. Finds " + finds2 + " count " + count);
        } 

        for (int y = 1; y < board.getHeight(); y++) {
            String line = walker.walk(new Point(0, y), Direction.NE);

            int finds = tester.findIn(line);
            count += finds;

            //System.out.println("Searching " + line + " NESW. Finds " + finds + " count " + count);

        }

        for (int x = 1; x < board.getWidth(); x++) {
            String line2 = walker.walk(new Point(x, board.getHeight() -1), Direction.SE);
            
            int finds2 = tester.findIn(line2);
            count += finds2;
            //System.out.println("Searching " + line2 + " NWSE. Finds " + finds2 + " count " + count);
        }

        //System.out.println("Result " + count);
        return count;

    }


    /**
     * https://adventofcode.com/2024/day/4#part2
     */
    public Integer doPartTwo() {
        List<Board> subBoards = new ArrayList<>();

        final String word = "MAS";

        Board board = new Board(lines);

        for (int x = 0; x < board.getWidth() - word.length() + 1; x++) {
            for (int y = 0; y < board.getHeight() - word.length() + 1; y++) {
                subBoards.add(board.subBoard(new Point(x, y), new Point(x + word.length(), y + word.length())));
            }
        }

        System.out.println(subBoards.size() + " subBoards found");

        int count = 0; 

        for (Board subBoard : subBoards) {
            char sw = subBoard.charAt(new Point(0,0));
            char nw = subBoard.charAt(new Point(2,0));
            char se = subBoard.charAt(new Point(0,2));
            char ne = subBoard.charAt(new Point(2,2));
            char m = subBoard.charAt(new Point(1,1));

            System.out.println(nw + " " + ne);
            System.out.println(" " + m + " ");
            System.out.println(sw + " " + se);
        
            if (m == word.charAt(1)) {
                
                if (nw == word.charAt(0) && se == word.charAt(2) || 
                    se == word.charAt(0) && nw == word.charAt(2)) {

                    if (ne == word.charAt(0) && sw == word.charAt(2) || 
                        sw == word.charAt(0) && ne == word.charAt(2)) {
                        System.out.println("found");
                        count++;
                    }
                }
            }
        }

        return count;
    }

    

    public class Walker {

        private final Board board;
        public Walker(Board board) {
            this.board = board;
        }

        public String walk(Point start, Direction direction) {


            StringBuilder sb = new StringBuilder();

            if (board.inBounds(start)) {
                Point current = start;

                while (board.inBounds(current)) {

                    sb.append(board.charAt(current));
                    current = current.move(direction);
                }
            }

            return sb.toString();
        }
    }


    public class Tester {

        private final String target;
        private final Pattern pattern;

        public Tester(String search) {
            this.target = search;
            pattern = Pattern.compile(target + "|" + new StringBuilder(target).reverse().toString());
        }

        public int findIn(String string) {
            
            Matcher matcher = pattern.matcher(string);
            int count = 0;
            int pos = 0;
            while (matcher.find(pos))
            {
                count++;
                //System.out.println("Found in " + string + " at pos " + matcher.start());
                pos = matcher.start() + 1;
            }

            return count;

        }

        public int findIn(List<String> strings) {
            return strings.stream().map(s -> findIn(s)).mapToInt(Integer::intValue).sum();
        }
    }




}
