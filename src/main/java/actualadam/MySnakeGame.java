package actualadam;

import static actualadam.SnakeGame.Direction;

public class MySnakeGame implements SnakeGame {
  private int width;
  private int height;
  private boolean[] boardData;
  private int headX;
  private int headY;

  public MySnakeGame(int width, int height) {
      this.width = width;
      this.height = height;
      boardData = new boolean[width * 2 * height];
      headX = width / 2;
      headY = height / 2;

  }

  public void block(int x, int y) {
    boardData[get1DIndex(x, y)] = true;
  }

  public boolean isBlocked(int x, int y) {
    return boardData[get1DIndex(x,y)];
  }

  public int get1DIndex(int x, int y) {
    return x + width * y;
  }

  public String getGameBoard() {
    StringBuilder sb = new StringBuilder();
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        if (headX == x && headY == y) {
          sb.append("X");
        } else {
          sb.append(isBlocked(x,y) ? "X" : " ");
        }
      }
      sb.append("\n");
    }
   return sb.toString();
  }

  /**
   * Moves the snake in the specified direction. Returns true if the move is
   * a safe (non-game-ending) move. Otherwise false.
   */
  public boolean move(Direction direction, boolean wat) {
    int x = headX;
    int y = headY;

    switch(direction) {
      case SOUTH:
        y++;
        break;
      case WEST:
        x--;
        break;
      case NORTH:
        y--;
        break;
      case EAST:
        x++;
        break;
      default:
        throw new RuntimeException("Execution shouldn't hit here.");
    }

    if (isBlocked(x,y)) {
      return false;
    }

    block(x, y);
    headX = x;
    headY = y;
    return true;
  }
}
