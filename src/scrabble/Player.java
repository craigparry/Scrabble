package scrabble;
import java.util.*;

public abstract class Player {
    protected int points;
    protected Collection<Letters> tray;
    protected Board gameBoard;
    protected LetterBag letterBag;
    protected Dictionary dictionary;


    public Player(LetterBag bag,Board board,Dictionary dictionary){
        points =0;
        tray =new LinkedList<>();
        letterBag =bag;
        gameBoard = board;
        drawTray(letterBag);
        this.dictionary = dictionary;
    }

    public void drawTray(LetterBag bag){
        while(tray.size()<= 7){
            tray.add(bag.draw());
        }
    }


    public void setTray(String letters) {
        tray.clear();

        for(int i =0; i<letters.length();i++){
            tray.add(new Letters(letters.charAt(i)));
        }

    }
    public void printTray(){
        for(Letters l: tray){
            System.out.print(l.toString());
        }
        System.out.println();
    }
    public int getPoints() {
        return points;
    }

    public Collection<Letters> getTray() {
        return tray;
    }

    /*shared methods*/
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
        if(gameBoard.isLegal(row,col,word,direction)){
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



    public abstract void playTurn();
        /*this may need to be set up in each individually so that the
        * functionality differs between the computer and the human player
        * */



    /* human methods */






    /*computer methods?  */






}
