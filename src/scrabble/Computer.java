package scrabble;
import java.util.*;

public class Computer extends Player {

    public Computer(LetterBag bag, Board board,Dictionary dictionary){
        super(bag,board,dictionary);
    }


    public  void playTurn(){
        // search gameboard for locations on the board that have occupied spaces
        // check both vertical and horiztonal plays if there are spaces available
        // if there aren't spaces available then skip
        // when checking for legal words to play there are check the spaces occupied
        // next  to the location and put those letters into a list with the
        // characters from the tray to find words that could possibly be played
        // starting with the prefix

// PlayNode temp = findBestWord();
//        gameBoard.isLegal();

        PlayNode node =null;
        PlayNode highest =null;
        int size = gameBoard.getSize();
        int ro = 0;
        int co = 0;
        for(ro = 0; ro< size; ro++){
            node =findBestWord(ro,co,Direction.VERTICAL);
            // set highest scoring play if higher

        }
        ro =0;
        for(co = 0; co <size; co++){
            node =findBestWord(ro, co, Direction.HORIZONTAL);
            // set highest scoring play if higher
        }



//        for(int ro = 0; ro< size; ro++){
//
//            for(int co = 0; co <size; co++){
//                //find vertical word
//                if(gameBoard.getGameBoard().get(ro).get(co).isEmpty()&&
//                        !gameBoard.getGameBoard().get(ro-1).get(co).isEmpty()){
//
//                    node =findBestWord(gameBoard.getVertPrefix(ro,co),ro,co,Direction.VERTICAL);
//                    //todo returns playNode if the score is higher than the previous play node
//                    // score then replace.
//                    // if the play placed all possible letters from the tray then
//                    // return that play
//
//
//
//
//                }
//                //find horizontal word
//                if(gameBoard.getGameBoard().get(ro).get(co).isEmpty()&&
//                        !gameBoard.getGameBoard().get(ro).get(co-1).isEmpty()) {
//                    node =findBestWord(gameBoard.getHorPrefix(ro,co),ro, co, Direction.HORIZONTAL);
//                    //todo
//                    // returns playNode if the score is higher than the previous play node
//                    // score then replace.
//                    // if the play placed all possible letters from the tray then
//                    // return that play
//                }
//
//                if(gameBoard.getGameBoard().get(ro).get(co).isEmpty()&&
//                        gameBoard.getGameBoard().get(ro).get(co-1).isEmpty() &&
//                        gameBoard.getGameBoard().get(ro-1).get(co).isEmpty()){
//                    //todo check the tray for possible combinatoin of words with no
//                    // prefix so that we can check for possible board plays that make
//                    // connnections by playing vertically or horizonatlly adjacent to
//                    // the words already played on the board.
//
//
//                }
//            }
//        }
        if(highest == null){
            //increment the turn
            // pass the computer turn
        }
    }
    /*this may need to be set up in each individually so that the
     * functionality differs between the computer and the human player
     * */

    public PlayNode findBestWord(int ro, int co, Direction direction ){
        /*traverse board and test words at each location saving the highest scoring word
        * that is a legal move on the board and return a */
        List<Character> chars = new LinkedList<>();
        List<String> holdWords;
        String word="";
        int row=0;
        int col=0;
        int score=0;
        // todo make it check every position on the board thank you very much

        for(Letters let: tray){
            chars.add(let.getLetter());
        }


        holdWords = dictionary.searchUnordered(new LinkedList<>(),chars,null);

        if(!holdWords.isEmpty()){
            //todo here test this
            // check if the word is legal on the board at the position
//            Letters tempLet = new Letters();
//            String best="";
//            int highest =0;
////            int preLength = prefix.length();
//            for(String s: holdWords){
//                int holdScore=0;
//                int length= s.length();
//                int prePos =0;
//
//                for(int i =0; i< length; i++){
//                    if(prePos < preLength){
//                        holdScore += tempLet.letterValue(s.charAt(i));
//                        prePos++;
//                    }
//                    if(chars.contains(s.charAt(i))){
//                        holdScore += tempLet.letterValue(s.charAt(i));
//                    }
//                }
//                if(holdScore>highest){
//                    best = s;
//                }

//            }
            //todo
            // for each letter in the string, find the corresponding character in the tray and add score
            // if that letter does not exist after the prefix then that will be the wildcard and the points wont count.

            //find the highest scoring word and set score
            // remove prefix and check if is a legal word
            // if too slow maybe check for the legal word
            // as we search the dictionary
            // do
            // findHighScore()
            // scoreWord()





        }




        return new PlayNode(word, row, col ,score, direction);
    }
    protected class PlayNode{
        private Direction direction;
        private String word;
        private int col;
        private int row;
        private int score;

        public PlayNode(String word, int row, int col,int score,Direction direction){
            this.word = word;
            this.row = row;
            this.col =col;
            this.score =score;
            this.direction = direction;
        }
    }

}
