import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Game {

    protected static final int CELL_SIZE_PX = 25;
    protected static final int SIZE_X = 32;
    protected static final int SIZE_Y = 24;
    protected static PacMan player1, player2;
    protected Cell[][] board = new Cell[SIZE_X][SIZE_Y];
    protected static int pacDotCounter = 0;

    public static void main(String[] args) {
        if (args.length != 2) {
            StdOut.println("Game expects 2 player names as arguments");
            System.exit(-1);
        }
        Game game = new Game();
        StdDraw.show();
        game.start(args[0], args[1]);
    }

    public void start(String name1, String name2) {
        init(name1, name2);

        int c = 0;

        while (true) {
            handleKeys();
            if (c % 6 == 0) {
                movePlayers();
                eatPacDots();
                repaintAll();
            }
            StdDraw.show(20);
            c += 1;
            if (endOfGame()) break;
        }
        saveScores(player1);
        saveScores(player2);
        showScore();
    }

    private void saveScores(PacMan player) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection c = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/pacmandb?user=ricfo&password=case2607ricfo");
            Statement s = (Statement) c.createStatement();
            String query = "insert into pacman_table(nickname, score) values (?, ?)";
            PreparedStatement preparedStmt = c.prepareStatement(query);
            preparedStmt.setString(1, player.getNickname());
            preparedStmt.setInt(2, player.score);
            preparedStmt.execute();
            c.close();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showScore() {
        StdDraw.clear(StdDraw.WHITE);
        StdDraw.setPenColor(StdDraw.BLACK);
        if (player1.score == 0) StdDraw.text(13, 12, player2.getNickname() + " is an absolute winner!");
        StdDraw.show();


        try {
            if (player2.score == 0)
                throw new customException();
        } catch (customException e) {
            StdDraw.text(13, 12, player1.getNickname() + " is an absolute winner!");
            e.printStackTrace();
            StdDraw.show();
        }
        if ((player1.score > 0) && (player2.score > 0)) {
            StdDraw.text(5, 15, player1.getNickname() + " ate " + player1.score + " pac dots");
            StdDraw.text(26, 15, player2.getNickname() + " ate " + player2.score + " pac dots");
            StdDraw.text(15, 12, player1.getNickname() + "/" + player2.getNickname() + " ratio: " + (double) player1.score / player2.score);
            StdDraw.show();
        }
    }

    private void repaintAll() {
        StdDraw.clear(StdDraw.BLACK);
        player1.repaint();
        player2.repaint();
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                board[i][j].repaint();
            }
        }
    }

    private void eatPacDots() {
        try {
            board[player2.getX()][player2.getY()].eatPacDot();
            pacDotCounter -= 1;
            player2.increaseScore();
//            StdAudio.play("src/sounds/eatpacmandot.wav");           // dobavit' vtoromu kentu zvuk i nastroit' chastotu
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            board[player1.getX()][player1.getY()].eatPacDot();
            pacDotCounter -= 1;
            player1.increaseScore();
//            StdAudio.play("src/sounds/eatpacmandot.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void movePlayers() {
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
    }

    private void handleKeys() {
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
    }

    private void init(String name1, String name2) {
        StdDraw.setCanvasSize(SIZE_X * CELL_SIZE_PX, SIZE_Y * CELL_SIZE_PX + 2);
        StdDraw.setXscale(-0.5, SIZE_X - 0.5);
        StdDraw.setYscale(-0.5, SIZE_Y - 0.5);
        player1 = new PacMan(0, 23, 2, name1);
        player2 = new PacMan(31, 0, 1, name2);

        createCells();
//        StdAudio.play("src/sounds/start.wav");
    }

    private void createCells() {
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                boolean isWall = (StdRandom.uniform() <= 0.04) && (i != player1.getX() || j != player1.getY()) && (i != player2.getX() || j != player2.getY());
                boolean hasPacDot = !isWall && (i != player1.getX() || j != player1.getY()) && (i != player2.getX() || j != player2.getY());
                board[i][j] = new Cell(i, j, isWall, hasPacDot);
                if (board[i][j].hasPacDot()) pacDotCounter++;
            }
        }
    }


    private static boolean endOfGame() {
        return (!player1.alive && !player2.alive) || pacDotCounter == 0;
    }
}
