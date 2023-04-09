abstract public class World {
    protected Space[][] grid;

    public World(int rows, int cols) {
        grid = new Space[rows][cols];
        generateGrid(rows, cols);
    }

    public void generateGrid(int rows, int cols){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Common();
            }
        }
    }

    public void printGrid() {
        //print a line of horizontal border at the top
        System.out.print("+");
        for (int m = 0; m < grid.length; m++) {
            System.out.print("-+");
        }
        System.out.println();
        //now print each line with another line of border
        for (int i = 0; i < grid.length; i++) {
            //print a line of board
            System.out.print("|");
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j].mark);
                System.out.print("|");
            }
            System.out.println();
            //with another line of horizontal border
            System.out.print("+");
            for (int n = 0; n < grid.length; n++) {
                System.out.print("-+");
            }
            System.out.println();
        }
    }
}