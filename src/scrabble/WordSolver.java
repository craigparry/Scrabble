/**Craig Parry This class is used for the scrabble game and is the WordSolver for
 * testing the Scrabble game
 * Use with:
 * Board.java, Computer.java, Letters.java, BoardTile.java, Direction.java,
 * Human.java, LetterBag.java, Player.java, MainGameLoop.java, ScrabbleGUI.java,
 * and Dictionary.java
 */

package scrabble;
import java.util.*;
public class WordSolver {

    private Player player;
    private Board board;
    private Dictionary dictionary;
    private LetterBag bag;



    public WordSolver(){
        bag =new LetterBag();
        dictionary = new Dictionary();
        board = new Board(dictionary);
        player = new Computer(bag,board,dictionary);
    }

    /** returns the board used for the wordsolver
     *
     * @return Board
     */
    public Board getBoard() {
        return board;
    }

    /** Returns the dictionary used for the worldSolver
     *
     * @return Dictionary
     */
    public Dictionary getDictionary() {
        return dictionary;
    }

    /** returns the player used for the wordsolver
     *
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    public static void main(String[] args){
        WordSolver test = new WordSolver();
        String config;
        String tray;
//        if(args.length > 0){
//            config =test.getBoard().readBoard(args[0]);
            config =test.getBoard().readBoard("test.txt");
            tray =test.getBoard().configBoard(config);
//            System.out.print(config);
//            System.out.print(tray);




            test.getPlayer().setTray(tray);
            test.getPlayer().printTray();
            List<Character> charList = new LinkedList<>();
            for(Letters let: test.getPlayer().getTray()){
                charList.add(let.getLetter());
            }
//        System.out.println(test.getBoard().isLegal(7,0,"tacnodes", charList, Direction.HORIZONTAL));
           // System.out.println(test.getBoard().isLegal(0,7,"goldiest",Direction.VERTICAL));
            test.getPlayer().playTurn();

            System.out.println("score godliest: "+ test.getBoard().scoreWord(7,0,charList,"godliest",Direction.VERTICAL));
            System.out.println("Move Scored: "+test.getPlayer().getPoints());
            System.out.print(test.getBoard().toString());
            //todo implement this completely
//            charList.add('t');
//            List<String> tempList = test.getDictionary().searchUnordered(new LinkedList<String>(),charList,null);
//            for(String s: tempList){
//                System.out.println(s);
//            }
//            test.getPlayer().playTurn();

//        }


    }


}
