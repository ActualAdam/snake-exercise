package actualadam;

public interface SnakeGame {
  public boolean move(SnakeGame.Direction direction, boolean wat);
  public String getGameBoard();

  public static enum Direction {
    NORTH, EAST, SOUTH, WEST;
  }
}
