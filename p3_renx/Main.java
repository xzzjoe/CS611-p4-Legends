public class Main {
    // TODO remind graders to update this filepath
    static String SRC_FILEPATH = "C:\\Users\\16124\\Desktop\\cs611\\cs611A2\\src";
    //static values for colorful print
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static void main(String[] args) {
        MonstersAndHeroesGame MHGame = new MonstersAndHeroesGame();
        MHGame.startGame();
    }
}