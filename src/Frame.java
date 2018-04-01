import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Frame extends JFrame {
    ImageIcon customerIcon = new ImageIcon("images/Customer.png");
    ImageIcon productIcon = new ImageIcon("images/Product.png");
    ImageIcon obstacleIcon = new ImageIcon("images/Obstacle.png");
    ImageIcon otherIcon = new ImageIcon("images/Other.png");
    ImageIcon bestBuyIcon = new ImageIcon("images/WindowsLaptop.png");
    ImageIcon searchIcon = new ImageIcon(("images/List.png"));
    static final int SCALE = 40;
    Canvas canvas = new Canvas();
    int custX = canvas.getCustomerX();
    int custY = canvas.getCustomerY();
    int prodX = canvas.getCustomersProductX()*SCALE + 140;
    int prodY = canvas.getCustomersProductY()*SCALE + 175;
    List<List<Shelf>> allShelves;
    List<Shelf> shelves;
    List<Product> products;
    int obstX;
    int obstY;
    boolean obstacle = true;
    boolean right = false;

    public Frame() {
        super("Product Pathfinder");
        allShelves = canvas.getShelves();
        shelves = new ArrayList<>();
        products = canvas.getProducts();
        for (List<Shelf> l: allShelves) {
            for (int j = 0; j < l.size(); j++) {
                Shelf shelf = l.get(j);
                shelves.add(shelf);
            }
        }
        scaleShelves();
        scaleProducts();
        findNearest();

    }

    public void scaleShelves() {
        for (Shelf s: shelves) {
            s.setX(s.getX()*SCALE + 140);
            s.setY(s.getY()*SCALE + 175);
        }
    }

    public void scaleProducts() {
        for (Product p: products) {
            p.setX(p.getX()*SCALE + 140);
            p.setY(p.getY()*SCALE + 175);
        }
    }

    public void findNearest() {
        double min = 10000;
        for(Shelf s: shelves) {
            double dist = distance(custX, custY, s.getX(), s.getY());
            if (min > dist) {
                min = dist;
                obstX = s.getX();
                obstY = s.getY();
            }
        }
    }

    public void findNextObstacle(int custX, int custY, int prodX, int prodY) {
        for (Shelf e : shelves) {
            e.setObstacle(obstaclePresent(custX, custY, prodX, prodY, e.getX(), e.getY()));
        }

        Shelf nearest = shelves.get(0);
        double min = 1000000000;

        for (int i = 0; i < shelves.size(); i++) {
            Shelf s = shelves.get(i);
            if (s.isObstacle() && !s.isCrossed()) {
                double dist = distance(custX, custY, s.getX(), s.getY());
                if (dist < min) {
                    s.setCrossed(true);
                    nearest = s;
                    obstacle = s.isObstacle();
                    s.setObstacle(false);
                }
            }
        }
        obstX = nearest.getX();
        obstY = nearest.getY();
    }

    public void pathfinder(Graphics g, int custX, int custY, int prodX, int prodY) {
        int midX = pathfindX(custX, prodX);
        int midY = pathfindY(custY, prodY);

        if (custY == prodY)
            findNextObstacle(custX, custY, prodX, prodY);
        else findNextObstacle(custX, custY, midX, midY);

        // Case 1: Horizontally on the same line
        // Case 2: Horizontally on the same line and with obstacle
        if (custY == prodY) {
            obstacle = obstaclePresent(custX, custY, prodX, prodY, obstX, obstY);
            if (!obstacle) {
                g.drawLine(custX, custY, prodX, prodY);
            } else {
                g.drawLine(custX, custY, obstX, obstY);
                g.drawLine(obstX, obstY, obstX, obstY + 25);
                obstacle = false;
                pathfinder(g, obstX, obstY + 25, prodX, prodY);
            }
        }

        // Case 3: Vertically on the same line
        // Case 4: Vertically on the same line and with obstacle

        else if (custX == prodX) {
            obstacle = obstaclePresent(custX, custY, prodX, prodY, obstX, obstY);
            if (!obstacle) {
                g.drawLine(custX, custY, prodX, prodY);
            } else {
                g.drawLine(custX, custY, obstX, obstY);
                g.drawLine(obstX, obstY, obstX + 25, obstY);
                obstacle = !obstacle;
                right = obstX > prodX;
                pathfinder(g, obstX + 25, obstY, prodX, prodY);
            }
        }

        // Case 5: Diagonal without obstacle
        // Case 6: Diagonal with obstacle

        else {
            if (!obstacle) {
                right = !right;
                midX = pathfindX(custX, prodX);
                midY = pathfindY(custY, prodY);
                g.drawLine(custX, custY, midX, midY);
                pathfinder(g, midX, midY, prodX, prodY);
            } else {
                if (custY == obstY && custX == obstX) {
                    g.drawLine(obstX, obstY, obstX + 25, obstY);
                    obstacle = false;
                    pathfinder(g, obstX + 25, obstY, prodX, prodY);
                } else if (custY == obstY) {
                    g.drawLine(custX, custY, obstX, obstY);
                    obstacle = false;
                    right = (obstY > prodY);
                    pathfinder(g, obstX, obstY, prodX, prodY);
                } else if (custX == obstX) {
                    g.drawLine(custX, custY, obstX, obstY);
                    right = !right;
                    obstacle = false;
                    pathfinder(g, obstX, obstY, prodX, prodY);
                } else {
                    g.drawLine(custX, custY, midX, midY);
                    obstacle = false;
                    pathfinder(g, midX, midY, prodX, prodY);
                }
            }
        }
    }

    public int pathfindX(int custX, int prodX) {
        if (right)
            return prodX;
        return custX;
    }

    public int pathfindY(int custY, int prodY) {
        if (right)
            return custY;
        return prodY;
    }


    public void paint(Graphics g) {
        for (Shelf e: shelves) {
            obstacleIcon.paintIcon(this, g, e.getX(), e.getY());
        }
        customerIcon.paintIcon(this, g, custX, custY);
        for (Product e: products) {
            otherIcon.paintIcon(this, g, e.getX(), e.getY());
        }
        bestBuyIcon.paintIcon(this, g, 0, 30);
        productIcon.paintIcon(this, g, prodX, prodY);
        searchIcon.paintIcon(this, g, 580, 150);
        pathfinder(g, custX+18, custY+36, prodX+18, prodY+36);
        //        String path = "Go to Shelf Number: " + Integer.toString(forDirectionY) + "and the Block Number: " + Integer.toString(forDirectionX);
    }

    public boolean obstaclePresent(int custX, int custY, int prodX, int prodY, int obstX, int obstY) {
        if (parallel(custX, custY, prodX, prodY, obstX, obstY))
            if (distance_calc(custX, custY, prodX, prodY, obstX, obstY))
                return true;
        return false;
    }

    private boolean parallel(int x1, int y1, int x2, int y2, int x3, int y3) {
        if ((y2 - y1) * (x3 - x1) == (y3 - y1) * (x2 - x1))
            return true;
        else
            return false;
    }

    private boolean distance_calc(int x1, int y1, int x2, int y2, int x3, int y3) {
        double dist1, dist2;
        dist1 = distance(x1, y1, x2, y2); // Math.sqrt ((x2-x1)^2+(y2-y1)^2);
        dist2 = distance(x1, y1, x3, y3); // Math.sqrt ((x3-x1)^2+(y3-y1)^2);
        if (dist1 > dist2)
            return true;
        else
            return false;
    }

    private double distance(int x1, int y1, int x2, int y2) {
        double dist;
        int num1 = x2 - x1;
        int num2 = y2 - y1;
        dist = Math.sqrt(num1 * num1 + num2 * num2);
        return dist;
    }
}