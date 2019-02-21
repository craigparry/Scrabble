package scrabble;

public class Computer extends Player {

    public Computer(LetterBag bag){
        super(bag);
    }


    public  void playTurn(){

    }
    /*this may need to be set up in each individually so that the
     * functionality differs between the computer and the human player
     * */

    public String findBestWord(){
        /*traverse board and test words at each location saving the highest scoring word*/
        return "here";
    }


}
