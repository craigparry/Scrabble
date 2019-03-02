/**Craig Parry This class is used for the scrabble game and is the computer used
 * for the Scrabble game
 * Use with:
 * Board.java, BoardTile.java, Letters.java, Dictionary.java, Direction.java,
 * Human.java, LetterBag.java, Player.java, MainGameLoop.java, ScrabbleGUI.java,
 * and WordSolver.java
 */

package scrabble;
import java.util.*;

public class Computer extends Player {

    public Computer(LetterBag bag, Board board,Dictionary dictionary){
        super(bag,board,dictionary);
    }

    /** Method that plays the turn for the computer player
     * @return void
     */
    public void playTurn(){
        // search gameboard for locations on the board that have occupied spaces
        // check both vertical and horiztonal plays if there are spaces available
        // if there aren't spaces available then skip
        // when checking for legal words to play there are check the spaces occupied
        // next  to the location and put those letters into a list with the
        // characters from the tray to find words that could possibly be played
        // starting with the prefix

// PlayNode temp = findBestWord();
//        gameBoard.isLegal();
        System.out.println("computer board ");
        System.out.println(gameBoard.toString());

        PlayNode node =null;
        PlayNode highest =null;
        int size = gameBoard.getSize();
        int ro = 0;
        int co = 0;

        for(ro = 0; ro< size; ro++){
            List<Character> hold = new LinkedList<>();
            for(co = 0; co <size; co++){
//                hold.add(gameBoard.getGameBoard().get(ro).get(co).getPiece().getLetter());
                if(gameBoard.getGameBoard().get(ro).get(co).getPiece() != null){
                    hold.add(gameBoard.getGameBoard().get(ro).get(co).getPiece().getLetter());
                }

            }
            if(hold.isEmpty() && ro -1 > 7 && gameBoard.rowEmpty(ro-1)){
                break;
            }
            node =findBestWord(ro,0,hold,Direction.HORIZONTAL);
            if(node != null && highest ==null){
                highest =node;
            }
            if(node != null && node.getScore() > highest.getScore()){
                highest = node;
            }
            // set highest scoring play if higher

        }

        for(co = 0; co <size; co++){
            List<Character> hold = new LinkedList<>();
            for(ro = 0; ro< size; ro++){
                if(gameBoard.getGameBoard().get(ro).get(co).getPiece() != null) {
                    hold.add(gameBoard.getGameBoard().get(ro).get(co).getPiece().getLetter());
                }
            }
            if(hold.isEmpty() && co -1 > 7 && gameBoard.colEmpty(co-1)){
                break;
            }
            node =findBestWord(0, co, hold, Direction.VERTICAL);
            if(node != null && highest ==null){
                highest =node;
            }
            if(node != null && node.getScore() > highest.getScore()){
                highest = node;
            }
            // set highest scoring play if higher
        }

        if(highest != null){
            points += placeWord(highest);
        } else{
            //increment the turn
            // pass the computer turn
        }

    }
    /*this may need to be set up in each individually so that the
     * functionality differs between the computer and the human player
     * */

    /** finds the best word that can be played for the computer player
     *
     * @param ro
     * @param co
     * @param boardChar
     * @param direction
     * @return PlayNode
     */



    //todo I have a bug that is not caluclating the highest score correctly so I
    // need to look and fix that maybe I'm not setting or getting the bonuses correctly
    // or something


    public PlayNode findBestWord(int ro, int co,List<Character> boardChar, Direction direction){
        /*traverse board and test words at each location saving the highest scoring word
        * that is a legal move on the board and return a */
        List<Character> chars = new LinkedList<>();
        List<Character> trayHold = new LinkedList<>();
        List<String> holdWords;
        String word="";
        int score=0;
        int row =0;
        int col=0;
        // todo make it check every position on the board thank you very much

        for(Letters let: tray){
            chars.add(let.getLetter());
            trayHold.add(let.getLetter());
        }
        chars.addAll(boardChar);
//        int boardSize = gameBoard.getSize();
//        for(int i =0; i<boardSize; i++){
//            gameBoard.get()
//        }

        holdWords = dictionary.searchUnordered(new LinkedList<>(),chars,null);
        List<String> wordsRemove;
        if(!holdWords.isEmpty()){
            for(String s: holdWords){
                int tempScore =0;

                if(s.equals("lemoned")){
                    System.out.println("lemoned pos:" + " row "+ ro + " col "+ co);
                }
                int legalPos = gameBoard.isLegal(ro,co,s,trayHold,direction);

                // is legal returns -1 for false
                if(legalPos >=0){
                    if(direction == Direction.VERTICAL){
                        tempScore = gameBoard.scoreWord(legalPos, co,trayHold,s, direction);
                        if(tempScore > score ){
                            score =tempScore;
                            word =s;
                            row = legalPos;
                            col =co;

                        }
                    }else{
                        tempScore =gameBoard.scoreWord(ro, legalPos, trayHold,s, direction);
                        if(tempScore > score ){
                            score =tempScore;
                            word =s;
                            row = ro;
                            col =legalPos;
                        }
                    }


                    // todo: need to place the letters on a temporary board and keep
                    // track of the the score based on the placement of the word.
                    // to keep track of the multipliers


                    //        Board tempBoard = new Board(dictionary);
//        tempBoard.configBoard(size +"\n"+this.toString());
//                    tempScore = temp.scoreWord(boardChar, , word);

                }
            }
        }

        if(!word.equals("")){
            return new PlayNode(word, row, col ,score, direction);
        }
        else return null;

    }

    /** Protected class that is used to hold the word, direction, col, row and
     * score of the play.
     */
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
        public Direction getDirection() {
            return direction;
        }

        public void setDirection(Direction direction) {
            this.direction = direction;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }

}
