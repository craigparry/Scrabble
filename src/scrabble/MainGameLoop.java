package scrabble;

public class MainGameLoop {
    private Board gameBoard;
    private LetterBag bag;
    private Dictionary dictionary;
    private Player human;
    private Player computer;
    private int turn;
    // computer
    //human

    public MainGameLoop(){
        newGame();
    }

    public void newGame(){
        dictionary = new Dictionary();
        gameBoard = new Board(dictionary);
        bag = new LetterBag();

        human = new Human(bag,gameBoard,dictionary);
        computer = new Computer(bag,gameBoard,dictionary);
        turn = 0;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public LetterBag getBag() {
        return bag;
    }

    public void setBag(LetterBag bag) {
        this.bag = bag;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public Player getHuman() {
        return human;
    }

    public void setHuman(Player human) {
        this.human = human;
    }

    public Player getComputer() {
        return computer;
    }

    public void setComputer(Player computer) {
        this.computer = computer;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void startGame(){
        if(turn % 2 == 0){

        } else {

        }
    }

    public static void main(String[] args) {
	// write your code here
        MainGameLoop test = new MainGameLoop();
        test.getGameBoard().setTile(7,7,new Letters('a'));

    }
}
