import javax.swing.*;
import java.awt.*;

public class DisplayClasses {

    public interface Display {
        void draw(GameOfLife game);
    }

    public static Display createDisplay(final String displayMethod) throws Exception {
        return switch (displayMethod) {
            case "console" -> new ConsoleDisplay();
            case "window" -> new WindowDisplay();
            default -> throw new Exception("Unknown display method: " + displayMethod);
        };
    }

    public static class ConsoleDisplay implements Display {
        @Override public void draw(GameOfLife game) {
            clearTerminal();
            System.out.println(game);
        }

        private static void clearTerminal() {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    public static class WindowDisplay extends JPanel implements Display {

        private static final int windowSize = 600;

        public WindowDisplay() {
            final var window = new JFrame("Game of Life");
            window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            window.setSize(windowSize, windowSize);
            window.setResizable(false);
            window.add(this);
            window.setVisible(true);
        }

        @Override public void draw(GameOfLife game) {
            final var graphics = getGraphics();
            final var board = game.getBoard();
            final var size = board.length;
            final var cell = windowSize / size;
            graphics.setColor(Color.BLACK);
            graphics.fillRect(0, 0, windowSize, windowSize);
            for (var row = 0; row < size; row++) {
                for (var col = 0; col < size; col++) {
                    final var color = board[row][col]
                            ? Color.MAGENTA
                            : Color.BLACK;
                    graphics.setColor(color);
                    graphics.fillRect(col * cell, row * cell, cell, cell);
                }
            }
        }
    }

}
