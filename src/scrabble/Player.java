/**Craig Parry This class is used for the scrabble game and is the parent of the
 * Human and Computer classes that are used in the game
 * Use with:
 * Board.java, BoardTile.java,Computer.java, Dictionary.java, Direction.java,
 * Human.java, LetterBag.java, Letters.java, MainGameLoop.java, ScrabbleGUI.java,
 * and WordSolver.java
 *
 */


package scrabble;
import java.util.*;

public abstract class Player {


    protected int points;
    protected Collection<Letters> tray;
    protected Board gameBoard;
    protected LetterBag letterBag;
    protected Dictionary dictionary;

    /*constructor for the Player class*/
    public Player(LetterBag bag,Board board,Dictionary dictionary){
        points =0;
        tray =new LinkedList<>();
        letterBag =bag;
        gameBoard = board;
        drawTray(letterBag);
        this.dictionary = dictionary;
    }

    /**Clears the score of player
     *
     */
    public void clearPoints() {
        this.points = 0;
    }
    /**This method draws from the letter bag adding them to the players tray
     * until there are 7 letters in the tray
     * @param  bag
     * @return void
     * */
    public void drawTray(LetterBag bag){
        while(tray.size()<= 7){
            tray.add(bag.draw());
        }
    }

    /** Sets the players tray with a new list of letters made from the string
     * that was passed in
     *
     * @param letters
     * @return void
     */
    public void setTray(String letters) {
        tray.clear();

        for(int i =0; i<letters.length();i++){
            tray.add(new Letters(letters.charAt(i)));
        }

    }

    /** prints the players tray to standard out
     * @return void
     */
    public void printTray(){
        for(Letters l: tray){
            System.out.print(l.toString());
        }
        System.out.println();
    }

    /** Gets the players score total
     *
     * @return int
     */
    public int getPoints() {
        return points;
    }

    /**gets the players tray
     *
     * @return Colleciton<Letters >
     */
    public Collection<Letters> getTray() {
        return tray;
    }

    /**removes letter specified from the players tray
     *
     * @param let
     * @return Letters
     */
    public Letters removeLetter(char let){
        Letters temp = null;
        for(Letters l: tray){
            if(l.getLetter() == let){
                temp = l;
            }
        }
            tray.remove(temp);
        return temp;
    }

    /** Places word on the board from the players tray
     *
     * @param node
     * @return
     */
    public int placeWord(Computer.PlayNode node){
        String word = node.getWord();
        int row = node.getRow();
        int col = node.getCol();
        Direction dir = node.getDirection();

        if(dir ==Direction.VERTICAL){
            for(int i =0; i< word.length(); i++){
                if(gameBoard.getGameBoard().get(row+i).get(col).isEmpty()){
                    Letters temp =null;
                    temp = removeLetter(word.charAt(i));
                    if(temp != null){
                        gameBoard.setTile(row+i,col,temp);
                    } else{
                        temp = removeLetter('*');
                        // todo figure out why this is getting a null pointer
                        // has something to do with when i should be removing the wildcard
                        temp.setBlankValue(word.charAt(i));

                        gameBoard.setTile(row+i,col,temp);
                    }

                }

            }
        }else{
            for(int i =0; i< word.length(); i++){
                if(gameBoard.getGameBoard().get(row).get(col+i).isEmpty()){
                    Letters temp =null;
                    temp = removeLetter(word.charAt(i));
                    if(temp != null){
                        gameBoard.setTile(row,col+i,temp);
                    } else{
                        temp = removeLetter('*');
                        temp.setBlankValue(word.charAt(i));
                        gameBoard.setTile(row, col+i,temp);
                    }

                }

            }
        }
        drawTray(letterBag);
        return node.getScore();
    }

    /**Places a word on the board from the players tray
     *
     * @param word
     * @param row
     * @param col
     * @param direction
     * @return boolean
     */
    public boolean placeWord(String word, int row, int col, Direction direction){
        /*maybe a boolean where the word selected is tried at this location
        and if the move is legal then removes pieces from the tray and scores
        the word for the player.
        *
         */

        //ask the board if the turn is legal

        // then place the word on the board if true else return false
        int colOff;
        int rowOff;
        if(direction == Direction.VERTICAL){
            colOff = 0;
            rowOff = 1;
        }else{
            colOff =1;
            rowOff =0;
        }

        int length = word.length();
        LinkedList<Letters> temp = new LinkedList<>();
        List<Character> trayHold = new LinkedList<>();
        for(Letters let: tray){
//            chars.add(let.getLetter());
            trayHold.add(let.getLetter());
        }
        if(gameBoard.isLegal(row,col,word,trayHold,direction)>0){
            for(int i = 0; i < length; i++){
                for(Letters letter: getTray()){
                    if(letter.getLetter() == word.charAt(i)){
                        temp.add(letter);
                        gameBoard.getGameBoard().get(row+rowOff).get(col+colOff).setPiece(letter);
                        if(direction == Direction.VERTICAL){
                            colOff = 0;
                            rowOff += 1;
                        }else{
                            colOff +=1;
                            rowOff =0;
                        }
                        break;
                    }
                }

            }
            tray.removeAll(temp);
            return true;
        }
        return false;
    }


    /**abstract method plays the players turn
     *  @return void
     */
    public abstract boolean playTurn();
        /*this may need to be set up in each individually so that the
        * functionality differs between the computer and the human player
        * */

    public abstract boolean playMove(List<Point> list);

    /* human methods */






    /*computer methods?  */






}
