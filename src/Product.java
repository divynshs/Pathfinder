public class Product {
    private final String name;              // product name
    private boolean inStock;                // flag if the product is in stock
    private int shelf;                      // the shelf at which the product is kept
    private int blockno;                    // block number on the shelf
    private int x;                          // x coordinate  will be initialized by canvas
    private int y;                          // y coordinate  will be initialized by canvas
    private int id;                         // product id

    public Product(String name, int id, int s, int blockno){
        this.name = name;
        this.id = id;
        this.shelf = s;
        this.blockno = blockno;
    }

    public void setStock(boolean inStock){
        this.inStock = inStock;
    }

    public void setShelf(int shelf) {
        this.shelf = shelf;
    }

    public String getName(){
        return this.name;
    }

    public boolean isInStock(){
        return this.inStock;
    }

    public int getId() {
        return this.id;
    }

    public int getBlockno() {
        return this.blockno;
    }

    public int getShelf() {
        return this.shelf;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}


