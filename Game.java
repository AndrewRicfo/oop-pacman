
import java.awt.event.KeyEvent;

public class Game {

    protected static final int CELL_SIZE_PX = 25;
    protected static final int SIZE_X = 32;
    protected static final int SIZE_Y = 24;
    //    BoardItem[][] board = new BoardItem[SIZE_X][SIZE_Y];
    protected static PacMan pacm, pacm1;


    public static void main(String[] args) {
        StdDraw.setCanvasSize(SIZE_X * CELL_SIZE_PX, SIZE_Y * CELL_SIZE_PX);
        StdDraw.setXscale(-0.5, SIZE_X - 0.5);
        StdDraw.setYscale(-0.5, SIZE_Y - 0.5);
        StdDraw.clear(StdDraw.BLACK);
        pacm = new PacMan(31, 0, 1);
        pacm1 = new PacMan(0, 23, 2);
        Cell[][] board = new Cell[SIZE_X][SIZE_Y];

        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                boolean isWall = (StdRandom.uniform() <= 0.035);
                boolean hasPacDot = !isWall;

                board[i][j] = new Cell(i, j, isWall, hasPacDot);
                if (isWall) {
                    if ((i == pacm.getX() && j == pacm.getY()) || (i == pacm1.getX() && j == pacm1.getY())) continue;
                    else
                        board[i][j].drawWall();
                }
                if (hasPacDot) {
//                    if (i != 0 || j != 23) {
                    if ((i == pacm.getX() && j == pacm.getY()) || (i == pacm1.getX() && j == pacm1.getY())) continue;
                    else
                        board[i][j].drawPacDot();
//                    }
                }
            }
        }

        while (true) {
            if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
                pacm.moveLeft(1);
            } if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
                pacm.moveRight(1);
            } if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
                pacm.moveUp(1);
            } if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
                pacm.moveDown(1);
            } if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
                pacm1.moveLeft(2);
            } if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
                pacm1.moveDown(2);
            }if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
                pacm1.moveRight(2);
            }if (StdDraw.isKeyPressed(KeyEvent.VK_W)) {
                pacm1.moveUp(2);
            }

            StdDraw.show(100);
        }
    }

}
