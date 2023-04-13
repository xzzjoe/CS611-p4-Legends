import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class LegendsOfValorWorld extends World {

    public LegendsOfValorWorld(int rows, int cols) {
        super(rows, cols);
        board = new Space[rows][cols];
        generateBoard(rows, cols);
    }

    @Override
    public void generateBoard(int rows, int cols){
        //calculate num of each types of Space
        //20% for each types of special Spaces, 40% for Plains; fix Nexus & Inaccessible position
        // calculate the count of plain+special spaces
        int totalSpacesNum = rows * cols - 2*cols - 2*(rows-2);
        int koulous = (int) (totalSpacesNum * 0.2);
        int caves = (int) (totalSpacesNum * 0.2);
        int bushes = (int) (totalSpacesNum * 0.2);
        int plains = totalSpacesNum - koulous - caves - bushes;

        //put all Spaces into an ArrayList, and then shuffle it
        ArrayList<Space> tempSpaces = new ArrayList<>();

        for (int i = 0; i < koulous; i++) {
            tempSpaces.add(new Koulou());
        }
        for (int i = 0; i < caves; i++) {
            tempSpaces.add(new Cave());
        }
        for (int i = 0; i < bushes; i++) {
            tempSpaces.add(new Bush());
        }
        for (int i = 0; i < plains; i++) {
            tempSpaces.add(new Plain());
        }
        //shuffle list of tempSpace
        Collections.shuffle(tempSpaces, new Random());
        //assign all spaces to the board
        //index: row = 0 or row = 7, Nexus; col = 2 or 5, inaccessible

        //and then fill in the rest of the board
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(j==2 || j==5)
                    //first priority
                    board[i][j] = new Inaccessible();
                else if(i==0 || i==7)
                    board[i][j] = new Nexus();
                else{
                    board[i][j] = tempSpaces.get(index);
                    index++;
                }
            }
        }

    }

    @Override
    public void printBoard(){
        //print a line of horizontal border at the top
        System.out.print("+");
        for (int m = 0; m < board.length; m++) {
            System.out.print("---+");
        }
        System.out.println();
        //now print each line with another line of border
        for (int i = 0; i < board.length; i++) {
            //print a line of board
            System.out.print("|");
            for (int j = 0; j < board[i].length; j++) {
                //check if hero is on this Space
                System.out.print(board[i][j].toString());
                System.out.print("|");
            }
            System.out.println();
            //with another line of horizontal border
            System.out.print("+");
            for (int n = 0; n < board.length; n++) {
                System.out.print("---+");
            }
            System.out.println();
        }
    }
    // Other LVWorld specific methods here
    public boolean isValidMove(int row, int column, GameCharacter c){
        //Todo combine this part with checking other monster/hero position
        if(c instanceof Hero){
            //1. inside the board
            if (row >= 0 && row < board.length && column >= 0 && column < board[0].length) {
                //& not an Obstacle:
                if (board[row][column].getClass() == Inaccessible.class) {
                    System.out.println("This space is an Obstacle");
                    return false;
                }
                //2. cannot walk to a space with same type character
                else if(board[row][column].getH()!=null){
                    System.out.println("This space is occupied by another Hero");
                    return false;
                }
                //3. when moving forward, cannot go past a enemy type character
                else if(row<c.getR()) {
                    boolean foundMonster = false;
                    for (int iterC = -1; iterC < 2; iterC++) {
                        int checkC = c.getC() + iterC;
                        if (checkC >= this.board.length || checkC < 0) {
                            continue;
                        }
                        else{
                            if(board[c.getR()][checkC].getM()!=null)
                                foundMonster = true;
                        }
                    }
                    if(foundMonster) {
                        System.out.println("You cannot go past a Monster");
                        return false;
                    }
                    else
                        return true;
                }
                else {
                    return true;
                }
            }
            else{
                return false;
            }
        }
        //c is a Monster
        else{
            //1. inside the board
            if (row >= 0 && row < board.length && column >= 0 && column < board[0].length) {
                //& not an Obstacle:
                if (board[row][column].getClass() == Inaccessible.class) {
                    System.out.println("This space is an Obstacle");
                    return false;
                }
                //2. cannot walk to a space with same type character
                else if(board[row][column].getM()!=null){
                    return false;
                }
                //3. monsters always go forward, and cannot go past an enemy type character
                //however, if monster is trying to move, it means we checked no hero is in range
                else {
                    return true;
                }
            }
            else{
                return false;
            }
        }
    }

    public void checkMarket(Hero hero) {
        //check current position is a market
        if(board[hero.getR()][hero.getC()].getClass()!=Nexus.class){
            System.out.println("This space is not a market!");
        }
        else{
            board[hero.getR()][hero.getC()].addHero(hero);
        }
    }
    public boolean checkCommon(Hero hero){
        //check current position is a Common space
        if(board[hero.getR()][hero.getC()].getClass()!=Plain.class){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean teleport(Hero from, Hero to){
        Space oldSpace = board[from.getR()][from.getC()];
        if(Math.abs(from.getC() - to.getC()) > 1){
            System.out.println("Two heroes in the same lane, cannot teleport");
            return false;
        }
        int newC = to.getC() + 1;
        Space newSpace = board[to.getR()][newC];
        if(newC < board[0].length && !(newSpace instanceof Inaccessible)){
            if(newSpace.h != null){
                oldSpace.removeHero();
                from.makeMove(to.getR(), newC);
                newSpace.addHero(from);
                return true;
            }
        }
        newC = to.getC() - 1;
        newSpace = board[to.getR()][newC];
        if(newC >= 0 && !(newSpace instanceof Inaccessible)){
            if(newSpace.h != null){
                oldSpace.removeHero();
                from.makeMove(to.getR(), newC);
                newSpace.addHero(from);
                return true;
            }
        }
        int newR = to.getR() - 1;
        newSpace = board[newR][to.getC()];
        if(newR >= 0){
            if(newSpace.h != null){
                oldSpace.removeHero();
                from.makeMove(to.getR(), newC);
                newSpace.addHero(from);
                return true;
            }
        }
        System.out.println("All space near allied heroes are occupied.(This should not happen with 3 heroes)");
        return false;
    }
    public ArrayList<Fightable> inRange(GameCharacter c){
        ArrayList<Fightable> retList = new ArrayList<Fightable>();
        int currR = c.getR();
        int currC = c.getC();
        if(c instanceof Hero){
            for(int iterR = -1; iterR < 2; iterR ++){
                int newR = currR + iterR;
                if(newR  >= this.board.length || newR < 0){
                    continue;
                }
                for(int iterC = -1; iterC < 2; iterC ++){
                    int newC = currC + iterC;
                    if(newC  >= this.board[0].length || newC < 0){
                        continue;
                    }
                    Space currSpace = board[newR][newC];
                    if (currSpace instanceof Inaccessible){
                        continue;
                    }
                    if (currSpace.getM() != null){
                        retList.add(currSpace.getM());
                    }
                }
            }
        }
        else{
            for(int iterR = -1; iterR < 2; iterR ++){
                int newR = currR + iterR;
                if(newR  >= this.board.length || newR < 0){
                    continue;
                }
                for(int iterC = -1; iterC < 2; iterC ++){
                    int newC = currC + iterC;
                    if(newC  >= this.board[0].length || newC < 0){
                        continue;
                    }
                    Space currSpace = board[newR][newC];
                    if (currSpace instanceof Inaccessible){
                        continue;
                    }
                    if (currSpace.getH() != null){
                        retList.add(currSpace.getH());
                    }
                }
            }
        }
        return retList;

    }

}
