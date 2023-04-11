abstract public class World {
    protected Space[][] board;

    public World(int rows, int cols) {
        board = new Space[rows][cols];
        generateBoard(rows, cols);
    }

    public void generateBoard(int rows, int cols){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = new Plain();
            }
        }
    }

    public void printBoard() {
        //print a line of horizontal border at the top
        System.out.print("+");
        for (int m = 0; m < board.length; m++) {
            System.out.print("-+");
        }
        System.out.println();
        //now print each line with another line of border
        for (int i = 0; i < board.length; i++) {
            //print a line of board
            System.out.print("|");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j].mark);
                System.out.print("|");
            }
            System.out.println();
            //with another line of horizontal border
            System.out.print("+");
            for (int n = 0; n < board.length; n++) {
                System.out.print("-+");
            }
            System.out.println();
        }
    }


}