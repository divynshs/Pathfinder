public class Shelf {
    private int number;                         // shelf number
    private int blocks;                         // number of blocks available in the shelf
    private int x;                              // x coordinate initialized by canvas
    private int y;                              // y coordinate initialized by canvas
    private boolean isObstacle;
    private boolean isCrossed;

    public Shelf(int number, int size){
        this.number = number;
        this.blocks = size;
    }

    public int getBlocks() {
        return this.blocks;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number){
        this.number = number;
    }

    public void setBlocks(int resize){
        this.blocks = resize;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isObstacle() {
        return this.isObstacle;
    }

    public void setObstacle(boolean obstacle) {
        this.isObstacle = obstacle;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean isCrossed() {
        return this.isCrossed;
    }

    public void setCrossed(boolean crossed) {
        this.isCrossed = crossed;
    }
}
