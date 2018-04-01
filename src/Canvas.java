import java.util.List;
import java.util.Random;

public class Canvas {
    //    private static final int width = 400;
//    private static final int height = 400;
    private Object canvas[][];
    private List<List<Shelf>> allShelves;
    private List<Product> products;
    private Customer customer;

    // private boolean occupied[][] = new boolean[width][height];
//    private List<Product> allProducts;
//    private Customer customer;

    public Canvas() {
        canvas = new Object[10][10];
        products = ProductCollection.getInstance().getListOfProducts();
        allShelves = ShelvesCollection.getInstance().getShelves();
        placeCustomer();
        placeShelves();
        placeProducts();
    }

    public void placeShelves() {
        for (List<Shelf> shelves : allShelves) {
            for (int i = 1; i < canvas.length; i += 2) {
                for (int j = 3, l = 0; j < shelves.get(0).getBlocks() + 3 && l < shelves.size(); l++, j++) {
                    canvas[i][j] = shelves.get(l);
                    shelves.get(l).setX(j);
                }
                for (int k = 0, n = 1; n < canvas.length && k < allShelves.size(); n += 2, k++) {
                    for (int m = 0; m < shelves.size(); m++) {
                        allShelves.get(k).get(m).setY(n);
                        allShelves.get(k).get(m).setObstacle(false);
                    }
                }
            }
        }
    }

    public void placeProducts() {
        for (int i = 1, j = 0; i < canvas.length && j < products.size(); j++, i += 2) {
            canvas[products.get(j).getShelf()][products.get(j).getBlockno()] = products.get(j);
            products.get(j).setY(i);
            products.get(j).setX(products.get(j).getBlockno());
        }
    }

    public void placeCustomer() {
        Random random = new Random();
        int idx = (int) random.nextInt(5);
        Random ran = new Random();
        int y = (int) ran.nextInt(451) + 150;
        customer = new Customer(565, 120, y, products.get(idx));
        canvas[1][1] = customer;
    }

    public void printCanvas() {
        for (int i = 0; i < canvas.length; i++) {
            String buffer = "";
            for (int j = 0; j < canvas[i].length; j++) {

                if (canvas[i][j] != null && canvas[i][j].getClass().getName().equals("Customer")) {
                    buffer = buffer + "C" + " ";
                } else if (canvas[i][j] != null && canvas[i][j].getClass().getName().equals("Shelf")) {
                    buffer = buffer + "S" + " ";
                } else if (canvas[i][j] != null && canvas[i][j].getClass().getName().equals("Product")) {
                    buffer = buffer + "P" + " ";
                } else if (canvas[i][j] == null) {
                    buffer = buffer + " ";
                }
            }
            System.out.println(buffer);
        }
        System.out.println(getCustomersProductX());
        System.out.println(getCustomersProductY());
    }

    public int getCustomersProductY() {
        Product toFind = customer.getProduct();
        return toFind.getY();
    }

    public int getCustomersProductX() {
        Product toFind = customer.getProduct();
        return toFind.getX();
    }

    public List<List<Shelf>> getShelves() {
        return this.allShelves;
    }

    public int getCustomerX() {
        return customer.getX();
    }

    public int getCustomerY() {
        return customer.getY();
    }

    public List<Product> getProducts() {
        return this.products;
    }
}
