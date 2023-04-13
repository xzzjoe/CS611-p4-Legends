public class Main {
    // TODO! use relative file path

    //static values for colorful print
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static AudioPlayer slash;

    public static void main(String[] args) {
        getAudio();
        LegendsOfValorGame lvGame = new LegendsOfValorGame();
        lvGame.startGame();
    }

    private static void getAudio(){
        try{
            Main.slash = new AudioPlayer("configs/slash.wav");
        }catch (Exception e){
            System.out.println("Exception");
        }
    }
}