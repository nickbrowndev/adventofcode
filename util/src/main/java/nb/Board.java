package nb;

import java.util.List;

    public class Board {
        private final char[][] board ;
        private final int width;
        private final int height;

        public Board(List<? extends CharSequence> lines) {
            board = DataLoader.parse2dCharArray(lines);
            width = board[0].length;
            height = board.length;
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



