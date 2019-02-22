package actualadam;

public class App {
  public static void main(String[] args) {

    SnakeGame game = new MySnakeGame(10, 10);
    System.out.println(game.getGameBoard());

    System.out.println("----> MOVE EAST (true)");
    game.move(SnakeGame.Direction.EAST, true);
    System.out.println(game.getGameBoard());

    System.out.println("----> MOVE EAST (false)");
    game.move(SnakeGame.Direction.EAST, false);
    System.out.println(game.getGameBoard());

    System.out.println("----> MOVE EAST (false)");
    game.move(SnakeGame.Direction.EAST, false);
    System.out.println(game.getGameBoard());

    System.out.println("\\/\\/  MOVE SOUTH (true)");
    game.move(SnakeGame.Direction.SOUTH, true);
    System.out.println(game.getGameBoard());

  }
}
