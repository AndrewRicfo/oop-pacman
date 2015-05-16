public class Cell extends BoardItem {
    protected boolean isWall = false;
    protected boolean hasPacDot = false;

    public Cell(int x, int y, boolean isWall, boolean hasPacDot) {
        super(x, y);

        this.isWall = isWall;
        this.hasPacDot = hasPacDot;
    }

    public boolean isWall() {
        return isWall;
    }

    public boolean hasPacDot() {
        return hasPacDot;
    }

    public void eatPacDot() throws Exception {
        if (!hasPacDot) {
            throw new Exception("There is no PacDot in this Cell");

        }

        hasPacDot = false;
    }

    public void repaint() {
        if (isWall) {
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledRectangle(x, y, 0.5, 0.5);
        } else if (hasPacDot){
            StdDraw.setPenColor(StdDraw.MAGENTA);
            StdDraw.filledCircle(x, y, 0.1);
        }
    }
}
