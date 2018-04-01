public class Customer {
    private int id;                         // customer id
    private int x;                          // x coordinate
    private int y;                          // y coordinate
    private Product product;                // the product that the customer is looking for

    public Customer(int id, int x, int y, Product p){
        this.id = id;
        this.y = y;
        this.x = x;
        this.product = p;
    }

    public void setProduct(Product p){
        this.product = p;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getId() {
        return id;
    }

    public Product getProduct() {
        return this.product;
    }
}
