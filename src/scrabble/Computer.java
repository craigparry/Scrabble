package scrabble;

public class Computer extends Player {

    public Computer(LetterBag bag, Board board){
        super(bag,board);
    }


    public  void playTurn(){
        // search gameboard for locations on the board that have occupied spaces
        // check both vertical and horiztonal plays if there are spaces available
        // if there aren't spaces available then skip
        // when checking for legal words to play there are check the spaces occupied
        // next  to the location and put those letters into a list with the
        // characters from the tray to find words that could possibly be played
        // starting with the prefix


//        gameBoard.isLegal();
    }
    /*this may need to be set up in each individually so that the
     * functionality differs between the computer and the human player
     * */

    public String findBestWord(){
        /*traverse board and test words at each location saving the highest scoring word*/
        return "here";
    }


}
