import java.util.Random;

public class GameOfLife {
    private boolean[][] board;
    private final int size;

    public GameOfLife(final int boardSize) {
        size = boardSize;
        board = new boolean[size][size];
    }

    public GameOfLife(final boolean[][] initialBoard) {
        board = initialBoard;
        size = board.length;
    }

    private static final Random random = new Random();

    public void randomizeBoard(int probability) {
        for (var row = 0; row < size; row++) {
            for (var col = 0; col < size; col++) {
                final var rnd = random.nextInt(100);
                board[row][col] = rnd < probability;
            }
        }
    }

    public void nextStep() {
        final var nextBoard = new boolean[size][size];
        for (var row = 0; row < size; row++) {
            for (var col = 0; col < size; col++) {
                final var alive = board[row][col];
                final var neighbours = countLiveNeighbours(row, col);
                nextBoard[row][col] = (!alive && neighbours == 3) || (alive && (neighbours == 2 || neighbours == 3));
            }
        }
        board = nextBoard;
    }

    public boolean hasAliveCells() {
        for (var row = 0; row < size; row++) {
            for (var col = 0; col < size; col++) {
                if (board[row][col]) return true;
            }
        }
        return false;
    }

    private int countLiveNeighbours(final int row, final int col) {
        var liveNeighbours = 0;
        for (var i = Math.max(0, row - 1); i <= Math.min(row + 1, size - 1); i++) {
            for (var j = Math.max(0, col - 1); j <= Math.min(col + 1, size - 1); j++) {
                if (i == row && j == col) continue;
                if (board[i][j]) liveNeighbours++;
            }
        }
        return liveNeighbours;
    }

    private static final char livingCell = '@';
    private static final char emptyCell = ' ';

    @Override
    public String toString() {
        final var builder = new StringBuilder();
        for (var row = 0; row < size; row++) {
            for (var col = 0; col < size; col++) {
                final var cell = board[row][col]
                        ? livingCell
                        : emptyCell;
                builder.append(cell);
            }
            builder.append('\n');
        }
        return builder.toString();
    }

}
