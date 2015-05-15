public class Ghost extends BoardItem implements Character {

    public Ghost(int x, int y) {
        super(x, y);
    }

    public void moveLeft(int type) {
        x -= 1;
    }

    public void moveRight(int type) {
        x += 1;
    }

    public void moveUp(int type) {
        y += 1;
    }

    public void moveDown(int type) {
        y -= 1;
    }

    public void repaint() {

    }
}
