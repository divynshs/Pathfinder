import java.util.ArrayList;
import java.util.List;

public class ShelvesCollection {
    private List<List<Shelf>> allShelves;
    private static ShelvesCollection instance;

    private ShelvesCollection() {
        allShelves = new ArrayList<>();
        for(int j = 0; j < 5; j++){
            List<Shelf> shelves = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                Shelf s = new Shelf(i, 5);
                shelves.add(s);
            }
            allShelves.add(shelves);
        }
    }

    public static ShelvesCollection getInstance(){
        if(instance == null){
            instance = new ShelvesCollection();
        }
        return instance;
    }

    public List<List<Shelf>> getShelves() {
        return allShelves;
    }
}
