/**Craig Parry This class is used as the letter bag for the scrabble game, that
 * holds all tiles currently not in play or on a players tray
 * Use with:
 * Board.java, BoardTile.java, Letters.java, Dictionary.java, Direction.java,
 * Human.java, Computer.java, Player.java, MainGame.java, ScrabbleGUI.java,
 * and WordSolver.java
 */

package scrabble;
import java.util.*;

public class LetterBag {
    public List<Letters> getBag(){
        return bag;
    }

    private List<Letters> bag;
    private Random rand;
    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public LetterBag(){
        bag = new LinkedList<>();
//        freqMap = new HashMap<>();
        rand = new Random();
        fillBag();
    }

    /** checks if the bag is empty
     *
     * @return boolean
     */
    public boolean empty(){
        if(bag.isEmpty()){
            return true;
        }
        return false;
    }

    /**This method fills the LetterBag with all of the tiles needed to play the
     * game with the standard scrabble frequencies and values
     *
     */
    public void fillBag(){
        bag.clear();
        //constructs tiles according to the letter frequencies for the letters
        for(int i = 0; i< alphabet.length(); i++){

            int fre = frequency(alphabet.charAt(i));

            for(int j = 0; j < fre; j++){
                bag.add(new Letters(alphabet.charAt(i)));
            }
        }
        //constructs two default blank tiles
        for(int b = 0; b<=1; b++){
            bag.add(new Letters());
        }
    }

    /**This method draws a Letter from the bag
     *
     * @return Letters
     */
    public Letters draw(){
        int hold = rand.nextInt(bag.size());
        return bag.remove(hold);
    }

    /**This method holds the values for the frequencies used for the letter bag
     * and returns the frequency associated with that letter
     *
     * @param letter
     * @return int
     */
    protected int frequency(char letter){
        int freq;

        switch(letter){
            case 'z':
            case 'x':
            case 'j':
            case 'k':
            case 'q': freq = 1;
            break;

            case 'b':
            case 'c':
            case 'f':
            case 'h':
            case 'm':
            case 'p':
            case 'v':
            case 'w':
            case 'y': freq = 2;
            break;

            case 'g': freq = 3;
            break;

            case 's':
            case 'd':
            case 'l':
            case 'u': freq = 4;
            break;

            case 'n':
            case 'r':
            case 't': freq = 6;
            break;

            case 'o': freq = 8;
            break;

            case 'a':
            case 'i': freq = 9;
            break;

            case 'e': freq = 12;
            break;

            default: freq =2;

        }

        return freq;
    }
}
