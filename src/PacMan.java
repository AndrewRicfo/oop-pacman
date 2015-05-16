public class PacMan extends BoardItem implements Character {

    //    boolean alive = true;
    protected String pic;
    public int dir = -1;

    public PacMan(int x, int y, int type) {
        super(x, y);
        if (type == 1) {
            pic = "images/left1.png";
        } else {
            pic = "images/right2.png";
        }
        repaint();
    }

    public void moveLeft(int type) {
//        x -= 1;
        if (type == 1) {
            pic = "images/left1.png";
        } else {
            pic = "images/left2.png";
        }
        dir = 0;
        repaint();
    }

    public void moveRight(int type) {
//        x += 1;
        if (type == 1) {
            pic = "images/right1.png";
        } else {
            pic = "images/right2.png";
        }
        dir = 2;
        repaint();
    }

    public void moveUp(int type) {
//        y += 1;
        if (type == 1) {
            pic = "images/up1.png";
        } else {
            pic = "images/up2.png";
        }
        dir = 1;
        repaint();
    }

    public void moveDown(int type) {
//        y -= 1;
        if (type == 1) {
            pic = "images/down1.png";
        } else {
            pic = "images/down2.png";
        }
        dir = 3;
        repaint();
    }

    public void repaint() {
        StdDraw.picture(x, y, pic);
    }
}