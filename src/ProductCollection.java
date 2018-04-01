import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductCollection {
    private List<Product> listOfProducts;
    private final List<String> names = new ArrayList<String>();
    private static ProductCollection instance;

    private ProductCollection() {
        listOfProducts = new ArrayList<Product>();
        names.add("logitech mouse");
        names.add("xbox one");
        names.add("hp omen");
        names.add("pendrive");
        names.add("camera");
        putProducts();
    }

    public static ProductCollection getInstance(){
        if(instance == null){
            instance = new ProductCollection();
        }
        return instance;
    }

    public void putProducts(){
        for (int i = 0; i < this.names.size(); i++){
            Random random = new Random();
            int blockno = random.nextInt(5) + 3;
            Product newP = new Product(this.names.get(i), i, i+1, blockno);
            newP.setStock(true);
            listOfProducts.add(newP);
        }
    }

    public boolean contains(String name){
        boolean ret = false;
        for (Product p : listOfProducts){
            if (name.equals(p.getName()))
                ret = true;
        }
        return ret;
    }

    public List<Product> getListOfProducts() {
        return listOfProducts;
    }
}
