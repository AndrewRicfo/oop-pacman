
import java.awt.event.KeyEvent;

public class Game {

    protected static final int CELL_SIZE_PX = 25;
    protected static final int SIZE_X = 32;
    protected static final int SIZE_Y = 24;
    protected static PacMan player1, player2;


    public static void main(String[] args) {
        StdDraw.setCanvasSize(SIZE_X * CELL_SIZE_PX, SIZE_Y * CELL_SIZE_PX);
        StdDraw.setXscale(-0.5, SIZE_X - 0.5);
        StdDraw.setYscale(-0.5, SIZE_Y - 0.5);
        Cell[][] board = new Cell[SIZE_X][SIZE_Y];
//        StdDraw.clear(StdDraw.BLACK);
        player1 = new PacMan(0, 23, 2);

        player2 = new PacMan(31, 0, 1);


        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                boolean isWall = (StdRandom.uniform() <= 0.035) && (i != player1.getX() || j != player1.getY()) && (i != player2.getX() || j != player2.getY());
                boolean hasPacDot = !isWall && (i != player1.getX() || j != player1.getY()) && (i != player2.getX() || j != player2.getY());

                board[i][j] = new Cell(i, j, isWall, hasPacDot);
            }
        }

        while (true) {
            StdDraw.clear(StdDraw.BLACK);
            player1.repaint();
            player2.repaint();
            for (int i = 0; i < SIZE_X; i++) {
                for (int j = 0; j < SIZE_Y; j++) {
                    if (board[i][j].hasPacDot()) board[i][j].drawPacDot();
                    if (board[i][j].isWall()) board[i][j].drawWall();
                }
            }

            if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
                if (player2.getX() >= 1)
                    if (!board[player2.getX() - 1][player2.getY()].isWall())
                        player2.moveLeft(1);
                    else continue;
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
                if (player2.getX() <= 30)
                    if (!board[player2.getX() + 1][player2.getY()].isWall())
                        player2.moveRight(1);
                    else continue;
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
                if (player2.getY() <= 22)
                    if (!board[player2.getX()][player2.getY() + 1].isWall())
                        player2.moveUp(1);
                    else continue;
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
                if (player2.getY() >= 1)
                    if (!board[player2.getX()][player2.getY() - 1].isWall())
                        player2.moveDown(1);
                    else continue;
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
                if (player1.getX() >= 1)
                    if (!board[player1.getX() - 1][player1.getY()].isWall())
                        player1.moveLeft(2);
                    else continue;
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
                if (player1.getY() >= 1)
                    if (!board[player1.getX()][player1.getY() - 1].isWall())
                        player1.moveDown(2);
                    else continue;
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
                if (player1.getX() <= 30)
                    if (!board[player1.getX() + 1][player1.getY()].isWall())
                        player1.moveRight(2);
                    else continue;
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_W)) {
                if (player1.getY() <= 22)
                    if (!board[player1.getX()][player1.getY() + 1].isWall())
                        player1.moveUp(2);
                    else continue;
            }

            try {
                board[player2.getX()][player2.getY()].eatPacDot();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                board[player1.getX()][player1.getY()].eatPacDot();
            } catch (Exception e) {
                e.printStackTrace();
            }

            StdDraw.show(50);
        }
    }

}