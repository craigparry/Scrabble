/**Craig Parry This class is used to make an instance of the scrabble game
 * Use with:
 * Board.java, BoardTile.java, Letters.java, Dictionary.java, Direction.java,
 * Human.java, LetterBag.java, Player.java, Computer.java, ScrabbleGUI.java,
 * and WordSolver.java
 */
package scrabble;

public class MainGameLoop {
    private Board gameBoard;
    private LetterBag bag;
    private Dictionary dictionary;
    private Human human;
    private Computer computer;
    private int turn;
    // computer
    //human

    public MainGameLoop(){
        newGame();
    }

    /** This method instantiates all of the members of the maingameloop to
     * start a new game of scrabble
     *
     */
    public void newGame(){
        dictionary = new Dictionary();
        gameBoard = new Board(dictionary);
        bag = new LetterBag();

        human = new Human(bag,gameBoard,dictionary);
        computer = new Computer(bag,gameBoard,dictionary);
        turn = 0;
    }

    /** returns the board associated with the game
     *
     * @return Board
     */
    public Board getGameBoard() {
        return gameBoard;
    }

    /**Sets the gameBoard
     *
     * @param gameBoard
     */
    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**Gets the letterbag associated with the game
     *
     * @return
     */
    public LetterBag getBag() {
        return bag;
    }

    /**Sets teh letter bag of the game
     *
     * @param bag
     */
    public void setBag(LetterBag bag) {
        this.bag = bag;
    }

    /**gets the dictionary associated with the game
     *
     * @return
     */
    public Dictionary getDictionary() {
        return dictionary;
    }

    /**Sets the dicitonary associated with the game
     *
     * @param dictionary
     */
    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    /**Gets the human player associated with the game
     *
     * @return
     */
    public Player getHuman() {
        return human;
    }

    /**sets the human player associated with the game
     *
     * @param human
     */
    public void setHuman(Human human) {
        this.human = human;
    }

    /**gets the computer player associated with the game
     *
     * @return
     */
    public Player getComputer() {
        return computer;
    }

    /**Sets the computer player associated with the game
     *
     * @param computer
     */
    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    /**Gets the turn of the game starting from 0
     *
     * @return
     */
    public int getTurn() {
        return turn;
    }

    /**Sets the turn of the game
     *
     * @param turn
     */
    public void setTurn(int turn) {
        this.turn = turn;
    }


    public static void main(String[] args) {
	// write your code here
        MainGameLoop test = new MainGameLoop();
        test.getGameBoard().setTile(7,7,new Letters('a'));

    }
}
