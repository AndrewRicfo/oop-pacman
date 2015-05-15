
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
        ;
        player2 = new PacMan(31, 0, 1);


        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                boolean isWall = (StdRandom.uniform() <= 0.035);
                boolean hasPacDot = !isWall;

                board[i][j] = new Cell(i, j, isWall, hasPacDot);
            }
        }

        while (true) {
            StdDraw.clear(StdDraw.BLACK);
            player1.repaint();
            player2.repaint();
            for (int i = 0; i < SIZE_X; i++) {
                for (int j = 0; j < SIZE_Y; j++) {
                    if (board[i][j].isWall()) {
                        if ((i == player2.getX() && j == player2.getY()) || (i == player1.getX() && j == player1.getY()))
                            continue;
                        else
                            board[i][j].drawWall();
                    }
                    if (board[i][j].hasPacDot()) {
                        if ((i == player2.getX() && j == player2.getY()) || (i == player1.getX() && j == player1.getY()))
                            continue;
                        else
                            board[i][j].drawPacDot();
                    }
                }
            }

            if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
                player2.moveLeft(1);
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
                player2.moveRight(1);
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
                player2.moveUp(1);
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
                player2.moveDown(1);
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
                player1.moveLeft(2);
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
                player1.moveDown(2);
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
                player1.moveRight(2);
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_W)) {
                player1.moveUp(2);
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
            StdDraw.show(20);
        }
    }

}
