package scrabble;

public class MainGameLoop {
    private Board gameBoard;
    private LetterBag bag;
    private Dictionary dictionary;
    private int turn;
    // computer
    //human

    public MainGameLoop(){
        newGame();
    }

    public void newGame(){
        gameBoard = new Board();
        bag = new LetterBag();
        dictionary = new Dictionary();
        turn = 0;
    }


    public void startGame(){
        if(turn % 2 == 0){

        } else {

        }
    }

    public static void main(String[] args) {
	// write your code here
    }
}
