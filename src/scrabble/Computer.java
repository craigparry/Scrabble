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
            List<Character> hold = new LinkedList<>();
            for(co = 0; co <size; co++){
                hold.add(gameBoard.getGameBoard().get(ro).get(co).getPiece().getLetter());
            }
            node =findBestWord(ro,0,hold,Direction.HORIZONTAL);
            // set highest scoring play if higher

        }

        for(co = 0; co <size; co++){
            List<Character> hold = new LinkedList<>();
            for(ro = 0; ro< size; ro++){
                hold.add(gameBoard.getGameBoard().get(ro).get(co).getPiece().getLetter());
            }
            node =findBestWord(0, co, hold, Direction.VERTICAL);
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

    public PlayNode findBestWord(int ro, int co,List<Character> boardChar, Direction direction ){
        /*traverse board and test words at each location saving the highest scoring word
        * that is a legal move on the board and return a */
        List<Character> chars = new LinkedList<>();
        List<String> holdWords;
        String word="";
        int score=0;
        // todo make it check every position on the board thank you very much

        for(Letters let: tray){
            chars.add(let.getLetter());
        }
        chars.addAll(boardChar);
//        int boardSize = gameBoard.getSize();
//        for(int i =0; i<boardSize; i++){
//            gameBoard.get()
//        }

        holdWords = dictionary.searchUnordered(new LinkedList<>(),chars,null);

        if(!holdWords.isEmpty()){
            for(String s: holdWords){
                int tempScore =0;
                if(gameBoard.isLegal(ro,co,s,direction)){
                    Letters temp = new Letters();

                    // todo: need to place the letters on a temporary board and keep
                    // track of the the score based on the placement of the word.
                    // to keep track of the multipliers


                    //        Board tempBoard = new Board(dictionary);
//        tempBoard.configBoard(size +"\n"+this.toString());
//                    tempScore = temp.scoreWord(boardChar, , word);
                    if(tempScore >= score ){

                    }
                }
            }
        }


        return new PlayNode(word, ro, co ,score, direction);
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
