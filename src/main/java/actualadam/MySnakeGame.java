package actualadam;

import java.util.ArrayDeque;
import static actualadam.SnakeGame.Direction;

public class MySnakeGame implements SnakeGame {
  private int boardWidth;
  private int boardHeight;
  private ArrayDeque<Integer> snakeTiles = new ArrayDeque<Integer>();
  private final static String SNAKE_TILE_DISPLAY = "X";
  private final static String EMPTY_TILE_DISPLAY = ".";

  public MySnakeGame(int boardWidth, int boardHeight) {
      this.boardWidth = boardWidth;
      this.boardHeight = boardHeight;
      // start in the middle of the board ...ish
      snakeTiles.offerFirst(get1DIndex(boardWidth / 2, boardHeight / 2));
  }

  // I'm using a system where 2d grid positions are stored in a 1d array. This
  // is a  widely used technique. Imigine the nested array alternative where
  // each array representing a row of the game grid concatenated to the array
  // for the previous row. You can account for the row by multiplying the row
  // location by total number of columns, and you can account for the column
  // by adding the column location to that product.
  // Then from the 1D index you can determine the 2d location by dividing the
  // index number by the number of columns. The quotient is the row location and
  // the remainder is the column location.
  public int get1DIndex(int x, int y) {
    return boardWidth * y + x;
  }

  public int getXFrom1DIndex(int index) {
    return index % boardWidth;
  }

  public int getYFrom1DIndex(int index) {
    return index / boardWidth;
  }

  public String getGameBoard() {
    // initialize a baord
    boolean[] board = new boolean[boardWidth * 2 * boardHeight];
    // overlay the snake
    for (int tile : snakeTiles) {
      board[tile] = true;
    }
    // string it
    StringBuilder sb = new StringBuilder();
    for (int y = 0; y < boardHeight; y++) {
      for (int x = 0; x < boardWidth; x++) {
        sb.append(board[get1DIndex(x, y)] ? SNAKE_TILE_DISPLAY : EMPTY_TILE_DISPLAY);
      }
      sb.append("\n");
    }
    return sb.toString();
  }

  /**
   * My coordinate system has an origin in the NW corner of the board.
   * Y increases as it goes South. X increases as it goes East.
   */
  public void move(Direction direction, boolean grow) {
    // get the existing position of the snake head
    int snakeHead = snakeTiles.peekFirst();
    int x = getXFrom1DIndex(snakeHead);
    int y = getYFrom1DIndex(snakeHead);

    // determine the new position in coordinates
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

    // translate to an array index. wrap if necessary.
    int newHead = get1DIndex(x % boardWidth, y % boardHeight);

    // check to see if we have run into ourselves.
    // probably better to act on this outside this method, but no requirement
    // for the game ending case anyway. this is just for fun.
    if (snakeTiles.contains(newHead)) {
      System.out.println("Game over!");
      System.exit(0);
    }

    // actually add the new head location
    snakeTiles.addFirst(newHead);

    // remove the last snake tile if we're not growing.
    if (!grow) {
      snakeTiles.removeLast();
    }
  }
}
