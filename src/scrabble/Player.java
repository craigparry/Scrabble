package scrabble;
import java.util.*;

public abstract class Player {
    private int points;
    private Collection<Letters> tray;
    private Board gameBoard;
    private LetterBag letterBag;


    public Player(LetterBag bag,Board board){
        points =0;
        tray =new LinkedList<>();
        letterBag =bag;
        gameBoard = board;
        drawTray(letterBag);
    }

    public void drawTray(LetterBag bag){
        while(tray.size()<= 7){
            tray.add(bag.draw());
        }
    }

    public int getPoints() {
        return points;
    }

    public Collection<Letters> getTray() {
        return tray;
    }

    /*shared methods*/
    public boolean placeWord(String word, Board board){
        /*maybe a boolean where the word selected is tried at this location
        and if the move is legal then removes pieces from the tray and scores
        the word for the player.
        *
         */

        //ask the board if the turn is legal

        // then place the word on the board if true else return false
        return false;
    }



    public abstract void playTurn();
        /*this may need to be set up in each individually so that the
        * functionality differs between the computer and the human player
        * */



    /* human methods */






    /*computer methods?  */






}
