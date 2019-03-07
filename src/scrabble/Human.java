/**Craig Parry This class is used for the scrabble game and is the human player
 *  used for the Scrabble game
 * Use with:
 * Board.java, BoardTile.java, Letters.java, Dictionary.java, Direction.java,
 * Computer.java, LetterBag.java, Player.java, MainGameLoop.java, ScrabbleGUI.java,
 * and WordSolver.java
 */
package scrabble;

public class Human extends Player {

    public Human(LetterBag bag, Board board,Dictionary dictionary){
        super(bag,board,dictionary);
    }

    public void playTurn(){

    }
    /*this may need to be set up in each individually so that the
     * functionality differs between the computer and the human player
     * */
}
