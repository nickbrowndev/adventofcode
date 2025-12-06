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

    
    private class Board {
        private final char[][] board ;
        private final int width;
        private final int height;

        public Board(List<? extends CharSequence> lines) {
            if (lines.isEmpty()) {
                throw new IllegalArgumentException("Empty list");
            }

            height = lines.size();
            width = lines.get(0).length();
            board = new char[lines.get(0).length()][lines.size()];

            lines = lines.reversed();
            //System.out.println("width " + width + " height " + height);
            for (int y = 0; y < height ; y++) {
                //System.out.println(y);
                final CharSequence line = lines.get(y);

                for (int x = 0; x < width ; x++) {
                    //System.out.println("setting " + x + ", " + y + " to " + line.charAt(x));
                    board[x][y] = line.charAt(x);
                }
            }
        }

        private Board(char[][] data) {
            this.board = data;
            this.height = data[0].length;
            this.width = data.length;
        }

        public char charAt(int x, int y) {
            if (x < 0 || x > width-1) {
                throw new IllegalArgumentException("X out of range (0 - " + (width-1) + "): " + x);
            }
            if (y < 0 || y > height -1) {
                throw new IllegalArgumentException("Y out of range (0 - " + (height-1) + "): " + y);
            }

            return board[x][y];
        }

        public char charAt(Point point) {
            return charAt(point.x(), point.y());
        }

        public int getWidth() {
            return this.width;
        }

        public int getHeight() {
            return this.height;
        }

        public boolean inBounds(Point point) {
            return point.x() >= 0 && point.x() < width && point.y() >= 0 && point.y() < height;
        }

        public Board subBoard(Point start, Point end) {
            int width = end.x() - start.x();
            int height = end.y() - start.y();

            char[][] result = new char[width][height];

            for (int x = 0; x < width; x++) { 
                for (int y = 0; y < height; y++) {

                    result[x][y] = this.charAt(new Point(start.x() + x, start.y() + y));
                }
            }

            return new Board(result);
        }
    }

    private record Point(int x,int y) {
        public Point move(Direction direction) {
            return new Point(this.x + direction.getX(), this.y + direction.getY());
        }
    }

    private enum Direction {
        N(0, 1), NE(1,1), E(1,0), SE(1,-1),S(-1,0),SW(-1,-1),W(-1,0),NW(-1,1);

        private Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private final int x;
        private final int y;

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }
    
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
