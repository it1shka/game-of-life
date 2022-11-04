import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameLoader {

    private final Scanner scanner;

    public GameLoader(final String filepath) throws FileNotFoundException {
        final var file = new File(filepath);
        scanner = new Scanner(file);
    }

    public GameOfLife load() throws Exception {
        final var boardSize = Integer.parseInt(scanner.nextLine());
        final var board = new boolean[boardSize][boardSize];
        var line = 0;
        while (scanner.hasNextLine() && line < boardSize) {
            final var currentLine = scanner.nextLine();
            if (currentLine.length() != boardSize) {
                throw new Exception(String.format("Line %d contains %d cells, expected %d",
                        line, currentLine.length(), boardSize));
            }
            for (var i = 0; i < currentLine.length(); i++) {
                final var current = currentLine.charAt(i);
//                if (current != '@' && current != ' ') {
//                    throw new Exception("Unknown character: " + current);
//                }
                board[line][i] = current == '@';
            }
            line++;
        }
        if (line < boardSize) {
            throw new Exception(String.format("Expected %d lines, found %d", boardSize, line));
        }
        if (scanner.hasNextLine()) {
            throw new Exception("File contains some extra lines");
        }
        return new GameOfLife(board);
    }

}
