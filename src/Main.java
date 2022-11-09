public class Main {

    private static ArgumentReader arguments;
    private static DisplayClasses.Display display;
    private static final int tick = 100;

    public static void main(String[] args) {
        try {
            arguments = new ArgumentReader(args);
            final var displayMethod = arguments.next();
            display = DisplayClasses.createDisplay(displayMethod);
            final var mode = arguments.next();
            switch (mode) {
                case "random" -> runRandomGame();
                case "file" -> runFromFile();
                default -> System.out.println("Unknown mode: " + mode);
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void runRandomGame() throws Exception {
        final var boardSize = Integer.parseInt(arguments.next());
        final var probability = Integer.parseInt(arguments.next());
        final var game = new GameOfLife(boardSize);
        game.randomizeBoard(probability);
        gameLoop(game);
    }

    private static void runFromFile() throws Exception {
        final var filepath = arguments.next();
        final var loader = new GameLoader(filepath);
        final var game = loader.load();
        gameLoop(game);
    }

    private static void gameLoop(final GameOfLife game) {
        while (game.hasAliveCells()) {
            display.draw(game);
            game.nextStep();
            try {
                Thread.sleep(tick);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("No living cells remaining");
    }

}