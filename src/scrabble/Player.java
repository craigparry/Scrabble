package scrabble;
import java.util.*;

public abstract class Player {
    private int points;
    private Collection<Letters> tray;


    public Player(){
        points =0;
        tray =new LinkedList<>();
    }

    public int getPoints() {
        return points;
    }

    public Collection<Letters> getTray() {
        return tray;
    }

    /*shared methods*/
    public void placeWord(){
        /*maybe a boolean where the word selected is tried at this location
        and if the move is legal then removes pieces from the tray and scores
        the word for the player.
        *
         */
    }

    public void playTurn(){
        /*this may need to be set up in each individually so that the
        * functionality differs between the computer and the human player
        * */
    }


    /* human methods */






    /*computer methods?  */



    public String findBestWord(){
        /*traverse board and test words at each location saving the highest scoring word*/
        return "here";
    }


}
