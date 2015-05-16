import java.awt.event.KeyEvent;

public class Game {

    protected static final int CELL_SIZE_PX = 25;
    protected static final int SIZE_X = 32;
    protected static final int SIZE_Y = 24;
    public static PacMan player1, player2;
    public static int pacDotCounter = 0;


    public static void main(String[] args) {

        StdDraw.setCanvasSize(SIZE_X * CELL_SIZE_PX, SIZE_Y * CELL_SIZE_PX);
        StdDraw.setXscale(-0.5, SIZE_X - 0.5);
        StdDraw.setYscale(-0.5, SIZE_Y - 0.5);
        Cell[][] board = new Cell[SIZE_X][SIZE_Y];
        player1 = new PacMan(0, 23, 2);
        player2 = new PacMan(31, 0, 1);


        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                boolean isWall = (StdRandom.uniform() <= 0.04) && (i != player1.getX() || j != player1.getY()) && (i != player2.getX() || j != player2.getY());
                boolean hasPacDot = !isWall && (i != player1.getX() || j != player1.getY()) && (i != player2.getX() || j != player2.getY());
                board[i][j] = new Cell(i, j, isWall, hasPacDot);
                if (board[i][j].hasPacDot()) pacDotCounter++;
            }
        }

        while (true) {
            StdDraw.clear(StdDraw.BLACK);
            player1.repaint();
            player2.repaint();
            for (int i = 0; i < SIZE_X; i++) {
                for (int j = 0; j < SIZE_Y; j++) {
                    board[i][j].repaint();
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
                pacDotCounter -= 1;
                System.out.println(pacDotCounter);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                board[player1.getX()][player1.getY()].eatPacDot();
                pacDotCounter -= 1;
                System.out.println(pacDotCounter);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (player1.dir == 0) {
                if (player1.getX() >= 1)
                    if (!board[player1.getX() - 1][player1.getY()].isWall())
                        player1.x -= 1;
            } else if (player1.dir == 1) {
                if (player1.getY() <= 22)
                    if (!board[player1.getX()][player1.getY() + 1].isWall())
                        player1.y += 1;
            } else if (player1.dir == 2) {
                if (player1.getX() <= 30)
                    if (!board[player1.getX() + 1][player1.getY()].isWall())
                        player1.x += 1;
            } else if (player1.dir == 3) {
                if (player1.getY() >= 1)
                    if (!board[player1.getX()][player1.getY() - 1].isWall())
                        player1.y -= 1;
            }

            if (player2.dir == 0) {
                if (player2.getX() >= 1)
                    if (!board[player2.getX() - 1][player2.getY()].isWall())
                        player2.x -= 1;
            } else if (player2.dir == 1) {
                if (player2.getY() <= 22)
                    if (!board[player2.getX()][player2.getY() + 1].isWall())
                        player2.y += 1;
            } else if (player2.dir == 2) {
                if (player2.getX() <= 30)
                    if (!board[player2.getX() + 1][player2.getY()].isWall())
                        player2.x += 1;
            } else if (player2.dir == 3) {
                if (player2.getY() >= 1)
                    if (!board[player2.getX()][player2.getY() - 1].isWall())
                        player2.y -= 1;
            }
            if (endOfGame()) break;
            StdDraw.show(60);
        }
        System.out.println("THA END!");
    }

    private static boolean endOfGame() {
        return (!player1.alive && !player2.alive) || pacDotCounter == 0;
    }
}