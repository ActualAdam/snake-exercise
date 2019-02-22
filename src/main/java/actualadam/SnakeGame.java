package actualadam;

public interface SnakeGame {
  enum Direction {
    NORTH, EAST, SOUTH, WEST;
  }

  /**
   * Creates a string that represents the current game board. Empty spaces should appear as a '.',
   * spaces containing the snake should appear as an 'X'.
   */
  public String getGameBoard();

  /**
   * Move the snake one pixel on the board.
   *
   * @param direction Which direction should we move?
   * @param grow Should the snake become one pixel longer after this move?
   */
  void move(SnakeGame.Direction direction, boolean wat);


}
