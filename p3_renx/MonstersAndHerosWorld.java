import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class MonstersAndHerosWorld extends World {

    public MonstersAndHerosWorld(int rows, int cols) {
        super(rows, cols);
        grid = new Space[rows][cols];
        generateGrid(rows, cols);
    }

    @Override
    public void generateGrid(int rows, int cols){
        //calculate num of each types of Space
        int totalSpacesNum = rows * cols;
        int obstacles = (int) (totalSpacesNum * 0.2);
        int markets = (int) (totalSpacesNum * 0.3);
        int commons = totalSpacesNum - obstacles - markets;

        //put all Spaces into an ArrayList, and then shuffle it
        ArrayList<Space> tempSpaces = new ArrayList<>();

        for (int i = 0; i < obstacles; i++) {
            tempSpaces.add(new Obstacle());
        }
        for (int i = 0; i < markets; i++) {
            tempSpaces.add(new Market());
        }
        for (int i = 0; i < commons; i++) {
            tempSpaces.add(new Common());
        }

        //prevent grid[0][0] to be a Obstacle Space:
        do{
            Collections.shuffle(tempSpaces, new Random());
        }while(tempSpaces.get(0).getClass()==Obstacle.class);
        //assign all spaces to the grid
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = tempSpaces.get(index);
                index++;
            }
        }

    }

    public void printGrid(int heroRow, int heroCol){
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
                //check if hero is on this Space
                if(i==heroRow && j==heroCol)
                    System.out.print('H');
                else
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
    // Other MHWorld specific methods here
    public boolean isValidMove(int row, int column){
        //inside the grid
        if (row >= 0 && row < grid.length && column >= 0 && column < grid[0].length)
            //& not an Obstacle:
            if (grid[row][column].getClass() == Obstacle.class) {
                System.out.println("This space is an Obstacle");
                return false;
            }
            else
                return true;
        else
            return false;
    }

    public void checkMarket(HeroTeam heroTeam) {
        //check current position is a market
        if(grid[heroTeam.getRow()][heroTeam.getCol()].getClass()!=Market.class){
            System.out.println("This space is not a market!");
        }
        else{
            grid[heroTeam.getRow()][heroTeam.getCol()].greetHeroTeam(heroTeam);
        }
    }
    public boolean checkCommon(HeroTeam heroTeam){
        //check current position is a Common space
        if(grid[heroTeam.getRow()][heroTeam.getCol()].getClass()!=Common.class){
            return false;
        }
        else{
            return true;
        }
    }
}
