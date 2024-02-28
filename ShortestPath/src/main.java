import exceptions.NoPathE;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException, NoPathE {
        Grid grid = Grid.fromFile("grid1.txt");

        grid.clearCache();
        Car car = new Car(new Cell(0, 0), 0, 0);
        PList<Cell> path = grid.computePathH(car);
        System.out.println(path);
        grid.printPath(path);
    }
}
