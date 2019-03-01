/**Craig Parry This class is used for the scrabble game and is the WordSolver for
 * testing the Scrabble game
 * Use with:
 * WordSolver.java, Computer.java, Letters.java, BoardTile.java, Direction.java,
 * Human.java, LetterBag.java, Player.java, MainGameLoop.java, ScrabbleGUI.java,
 * and Dictionary.java
 */

package scrabble;
import java.io.*;
import java.util.*;
public class Board {

    private int size;
    private ArrayList<ArrayList<BoardTile>> gameBoard;
    private Dictionary dictionary;

    public Board(Dictionary dict){
        dictionary = dict;
        size =15;
        initBoard();
    }
    public Board(int x, Dictionary dict){
        dictionary = dict;
        size =x;
        initBoard();
    }
    /** Gets the size of the board
     *
     * @return int
     */
    public int getSize() {
        return size;
    }

    /**Gets the representation of the board
     *
     * @return ArrayList<ArrayList<BoardTile>></BoardTile>
     */
    public ArrayList<ArrayList<BoardTile>> getGameBoard() {
        return gameBoard;
    }

    /** This method configures the board from a String representation that is
     * passed into the function
     *
     * @param boardStr
     * @return String
     */
    public String configBoard(String boardStr){
        /*maybe use this board config to clone the game
        board and plug in the values of the word that we are
        testing for a legal move.
         */
        int row =0;
        int col =0;
        int position = 0;
        char c;
        String temp = "";
        c = boardStr.charAt(position);
        while(c == '\n'){
            position++;
            c=boardStr.charAt(position);
        }
        if(Character.isDigit(c)){
            while(c != '\n'){
                temp += c;
                position++;
                c = boardStr.charAt(position);
            }
//            System.out.println(temp);
            this.size = Integer.parseInt(temp);
            initBoard();
        }
        position++;

        while(position< boardStr.length() && row <size){
//            System.out.println(c);
//            System.out.println("row" +row);
//            System.out.println("col" +col);
            c = boardStr.charAt(position);


            if(c == '.'){
                position++;

                c = boardStr.charAt(position);
                //right side is letter Bonus
                if(Character.isDigit(c)){
                    gameBoard.get(row).get(col).setBonus(true);

                    gameBoard.get(row).get(col).setLetterBonus(Character.getNumericValue(c));
                    gameBoard.get(row).get(col).setEmpty(true);
                } else {

                    c = boardStr.charAt(position);
                    if(c=='.'){
                        gameBoard.get(row).get(col).setEmpty(true);
                    }
                }
                col++;
            } else if(Character.isAlphabetic(c)){
                gameBoard.get(row).get(col).setEmpty(false);
                gameBoard.get(row).get(col).setPiece(new Letters(c));

                col++;
            } else if(Character.isDigit(c)){
                char hold = c;
                position++;
                c = boardStr.charAt(position);
                if(c == '.'){
//                    System.out.print(c);
                    gameBoard.get(row).get(col).setBonus(true);
                    gameBoard.get(row).get(col).setWordBonus(Character.getNumericValue(hold));
                    gameBoard.get(row).get(col).setEmpty(true);
                }

                col++;
            }
            else if (c == '\n'){
//                System.out.println("new");
                row++;
                col =0;
            } else{

            }
            position++;
        }

        String tray = "";
//        System.out.print("ahh "+ c);
        if(position <boardStr.length()){
            c = boardStr.charAt(position);
            while(c == '\n'){
                position++;
                c= boardStr.charAt(position);
            }
            if(c != '\n'){
                while(c != '\n'){
//                System.out.println(c);
                    tray += c;
                    position++;
                    c = boardStr.charAt(position);
                }
            }
        }
//        System.out.print("here " + tray);
        return tray;
    }
    /** Reads in a string representation of the board from standard input
     * @param file
     * @return String
     * */
    public String readBoard(String file){

        //todo: make it read generically when there is a file from command line
        // specifically using a scanner, so without the buffered reader

        String config = "";

        if(file!= null){
            try(

                    BufferedReader reader = new BufferedReader(new FileReader(file))){
                String line;

                while ((line = reader.readLine()) != null){
                    config += line + "\n";
                }
            } catch(IOException ex){
                System.out.println(ex.toString());
            }
            return config;
        }
        else {
            try (

                    BufferedReader reader = new BufferedReader(new FileReader("scrabble_board.txt"))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    config += line + "\n";
                }
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
            return config;
        }
    }

        public int scoreWord(int row, int col, List<Character> tray, String word, Direction direction){
        int length = word.length();
        int score =0;
        int trayChars =0;
        int wordBonus =0;

        for(int i = 0; i< length; i++){

            if(direction == Direction.VERTICAL){
                if(!gameBoard.get(row+i).get(col).isEmpty()){
                    score += gameBoard.get(row+i).get(col).getPiece().getValue();


                } else{

                    int letterBonus = gameBoard.get(row+i).get(col).getLetterBonus();
                    int wBonus = gameBoard.get(row+i).get(col).getWordBonus();
                    if (wordBonus > 0){
                        wordBonus = wordBonus * wBonus;
                    }else{
                        wordBonus =wBonus;
                    }
                    Letters temp = new Letters();
                    char let = word.charAt(i);
                    int letValue = temp.letterValue(let);
                    if(letterBonus >0){
                        score += letterBonus * letValue;
                    } else{
                        score += letValue;
                    }
                    if(tray.contains(let)){
                        trayChars++;
                    }
                    // todo check for horiztontal scoring words created
                }
            }else{
                if(!gameBoard.get(row+i).get(col).isEmpty()){
                    score += gameBoard.get(row+i).get(col).getPiece().getValue();


                } else{

                    int letterBonus = gameBoard.get(row).get(col+i).getLetterBonus();
                    int wBonus = gameBoard.get(row).get(col+i).getWordBonus();
                    if (wordBonus > 0){
                        wordBonus = wordBonus * wBonus;
                    }else{
                        wordBonus =wBonus;
                    }
                    Letters temp = new Letters();
                    char let = word.charAt(i);
                    int letValue = temp.letterValue(let);
                    if(letterBonus >0){
                        score += letterBonus * letValue;
                    } else{
                        score += letValue;
                    }
                    if(tray.contains(let)){
                        trayChars++;
                    }

                    //todo check for vertical scoring words created
                }
            }

        }
        if(trayChars == 7){
            score +=50;
        }

        return score;
    }
    /** Initializes the gameBoard based on the specified size
     * @return void
     * */
    public void initBoard(){
        gameBoard = new ArrayList<>(size);
        for(int i = 0; i < size; i++){
            gameBoard.add(new ArrayList<>(size));
            for(int k = 0; k< size; k++){
                if(gameBoard.isEmpty()){
//                    System.out.print("Empty");
                }
                gameBoard.get(i).add(new BoardTile());
            }
        }
    }
    /** Sets a tile on the board
     * @param row
     * @param col
     * @param let
     * @return void
     * */
    public void setTile(int row, int col, Letters let){
        gameBoard.get(row).get(col).setPiece(let);
        gameBoard.get(row).get(col).setEmpty(false);

    }
    /** checks if the board is empty
     * @return boolean
     */
    public boolean boardEmpty(){
        for(ArrayList<BoardTile> row: gameBoard){
            for(BoardTile tile: row){
                if(!tile.isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }

//    public Board copyBoard(Board board){
//        Board temp = new Board(board.dictionary);
//
//        temp.gameBoard = new ArrayList<>(board.size);
//        for(int i = 0; i < size; i++){
//            temp.gameBoard.add(new ArrayList<>(board.size));
//            for(int k = 0; k< size; k++){
//                char hold = board.gameBoard.get(i).get(k).getPiece().getLetter();
//
//                temp.gameBoard.get(i).add(new BoardTile(new Letters(hold)));
//
//            }
//        }
//    }

    //        // todo: need to check for the case of a horizontal word played under
//         //a horizontal word and a vertical played next to a vertical
//
//        //todo this needs to be updated because it wont catch words that are played
//        // around the words that are already placed
    // assuming the word passed is from the dictionary

    /** Checks if the word played a certain row and collum and in a certain
     * direction is a legal word, returns negative value for false and returns
     * row or collumn value for legal words
     * @param row
     * @param col
     * @param word
     * @param dir
     * @return int
     */
    public int isLegal(int row, int col, String word, Direction dir){

        if(boardEmpty()){
            if(word.length()+size/2 <= size && size/2 -word.length()>=0){
                // maybe just place the word
               return size/2;
            }
        }

//        Board tempBoard = new Board(dictionary);
//        tempBoard.configBoard(size +"\n"+this.toString());
        //System.out.println(tempBoard.toString());
        //todo need to test this new version it starts at the beginning of the row and checks
        // the word in every location down vertically trying to match the word with a tiles already
        // played or finding connecting tiles to the left or the right. if it connects then calls the
        // vertical helper otherwise it will check the vertical helper for connections that would make it false
        // otherwise its true
        boolean legal =false;
        if(dir == Direction.VERTICAL){
            int wordLen = word.length();
            // i is position in the rows of characters
            for(int i =0;i<size; i++){
               // j is string position
                boolean connects = false;
                for(int j = 0; j < wordLen; j++){

                    // checks size of positions
                    if(i +j >=0 && i+j < size){
                        // holds letter at that position on the board

                        if(!gameBoard.get(i+j).get(col).isEmpty()){
                            char hold = gameBoard.get(i+j).get(col).getPiece().getLetter();
                            hold = Character.toLowerCase(hold);
                            if(hold != word.charAt(j)){
                                break;
                            }
                            connects =true;
                        }


                        if(col + 1 >=0 && col +1 < size &&!gameBoard.get(i+j).get(col+1).isEmpty()){
                            connects =true;
                        }
                        if(col-1 >=0 && col -1<size && !gameBoard.get(i+j).get(col-1).isEmpty()){
                            connects = true;
                        }

                    } else break;

                    if(j == wordLen-1 && connects){
                        legal = verticalHelper(i,col,word,0,wordLen);
                        if(legal){
                            return i;
                        }
                    }
                }

            }
        }else{
            // make the horizontal move
            int wordLen = word.length();
            // i is position in the rows of characters
            for(int i =0;i<size; i++){
                // j is string position
                boolean connects = false;
                for(int j = 0; j < wordLen; j++){

                    // checks size of positions
                    if(i +j >=0 && i+j < size){
                        // holds letter at that position on the board

                        if(!gameBoard.get(row).get(i+j).isEmpty()){
                            char hold = gameBoard.get(row).get(i+j).getPiece().getLetter();
                            hold =Character.toLowerCase(hold);
                            if(hold != word.charAt(j)){
                                break;
                            }
                            connects =true;
                        }


                        if(row + 1 >=0 && row +1 < size &&!gameBoard.get(row+1).get(i+j).isEmpty()){
                            connects =true;
                        }
                        if(row-1 >=0 && row -1<size && !gameBoard.get(row-1).get(i+j).isEmpty()){
                            connects = true;
                        }

                    } else break;

                    if(j == wordLen-1 && connects){
                        legal = horizontalHelper(row,i,word,0,wordLen);
                        if(legal){
                            return i;
                        }
                    }
                }

            }

        }
        return -1;
    }


    // trying to refactor this into something different and simpler. something that isn't so specified

//    public boolean isLegal(int row, int col, String word, Direction dir){
//        //from the starting position
//        // check for the length of the string
//        // to see if there are available spaces and if the word matches a
//        //word in the dictionary
//
//

//
//        //maybe I should read in the entire collum or row and try to make words that
//        // include every letter in the row since they have to connect and if
//
//        /*need to use the position that is passed into the function then
//        * we check if that position is occupied. If it is then not legal
//        * we want to check for positions one off of an already played tile
//        * because thats where we want the play to start from for easier conversion
//        * then we want to have the play to check in each direction if the position is
//        * occupied to the left or above then we can check for a legal word attached to those positons*/
//
//        if(row >= size || row < 0) return false;
//        if(col >= size || col < 0) return false;
//        int length = word.length();
//        if(this.boardEmpty() && row != size/2 && col != size/2){
//            return false;
//        }
//
//        if(dir == Direction.VERTICAL){
//            if(length+row > size){
//                return false;
//            }
//            // check for position minus one of the word for a lettered tile
//            // check for position plus word length for a lettered tile
//
////            if(gameBoard.get(row-1).get(col).isEmpty()
////                    && gameBoard.get(row +length).get(col).isEmpty()){
////
////                return false;
////            }
//            if(!gameBoard.get(row-1).get(col).isEmpty()){
//                // check rows above the play
//                // add letters to front of word
//
//                String temp ="";
//                int i  =1;
//                while(row -i >=0 && !gameBoard.get(row-i).get(col).isEmpty()) {
//                    temp = gameBoard.get(row -i).get(col).getPiece().getLetter() +temp;
////                    System.out.println("temp = " +temp);
//                    i++;
//                }
////                System.out.println("here");
//                temp += word;
////                System.out.println(word);
////                System.out.println(temp);
//                // if found in the dictionary check the horizontal plays it might make
//                if(dictionary.search(temp,null,0,temp.length())){
//                    // if false then connection on word isn't legal
//                    if(!verticalHelper(row,col, word,0,length)){
//                        return false;
//                    }
//                    return true;
//                }
//            }
//
//
//        } else{
//            if(length+col > size){
//                int sum = length+ col;
//
//                return false;
//            }
//
//            // check for position minus one of the word for a lettered tile
//            // check for position plus word length for a lettered tile
//            if(gameBoard.get(row).get(col-1).isEmpty()
//                    && gameBoard.get(row).get(col+length).isEmpty()){
//                return false;
//            }
//            if(!gameBoard.get(row).get(col-1).isEmpty()){
//                // check rows above the play
//                // add letters to front of word
//
//                String temp =getHorPrefix(row,col);
////                int i  =1;
////                while(col - i >= 0 && !gameBoard.get(row).get(col-i).isEmpty()){
////                    temp = gameBoard.get(row).get(col-i).getPiece().getLetter() +temp;
////                    i++;
////                }
//                temp += word;
//
//                // if found in the dictionary check the horizontal plays it might make
//                if(dictionary.search(temp,null,0,temp.length())){
//                    // if false then connection on word isn't legal
//                    if(!horizontalHelper(row,col, word,0,length)){
//                        return false;
//                    }
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    /** Helper function for is legal that checks the vertical connections of
     * horizontal plays on the board
     *
     * @param row
     * @param col
     * @param word
     * @param position
     * @param length
     * @return boolean
     */
    public boolean horizontalHelper(int row, int col, String word, int position,
                                    int length){
        if(position >= length){
            return true;
        }

        String temp  =getVertPrefix(row,col);
//        //append letter tiles to the left of the position to the front of the string
//        while( row -i >=0 && !gameBoard.get(row-i).get(col).isEmpty() ){
//
//            temp = gameBoard.get(row-i).get(col).getPiece().getLetter() +temp;
//            i++;
//        }
        temp += word.charAt(position);

        // append letters to the right of the position to the end of the string
        int i =1;
        while(row + i < size && !gameBoard.get(row+i).get(col).isEmpty()){
            char hold =gameBoard.get(row+i).get(col).getPiece().getLetter();
            hold = Character.toLowerCase(hold);
            temp += hold;

            i++;
        }
        // should have 1 letter if no connections otherwise
        if(temp.length() > 1){
            if(!dictionary.search(temp,null,0, temp.length())){
                return false;
            }
        }

        return horizontalHelper(row,col+1,word, position+1,length);
    }

    /** Gets the prefix of vertical connections to a location on the board
     *
     * @param row
     * @param col
     * @return String
     */
    public String getVertPrefix(int row, int col){
        int i =1;

        String temp  ="";
        //append letter tiles to the left of the position to the front of the string
        while( row -i >=0 && !gameBoard.get(row-i).get(col).isEmpty() ){
            char hold =gameBoard.get(row-i).get(col).getPiece().getLetter();
            hold = Character.toLowerCase(hold);
            temp = hold +temp;
            i++;
        }
        return temp;

    }

    /** Gets the prefix of a horizontal locations at the position on the board
     *
     * @param row
     * @param col
     * @return String
     */
    public String getHorPrefix(int row, int col){
        int i =1;
//        StringBuilder hold = new StringBuilder();
        String temp = "";
        //append letter tiles to the left of the position to the front of the string
        while(col-i >= 0 && !gameBoard.get(row).get(col-i).isEmpty() ){
            char hold =gameBoard.get(row).get(col-i).getPiece().getLetter();
            hold = Character.toLowerCase(hold);
            temp = hold +temp;
            i++;
        }
        return temp;}

    /** This function searches the horizontal connections of a vertical play
     * on the board. Starting at positoin(row,col) and moving down along the
     * board it will check each row for a new word created by the play and return
     * false if there is a new connection that does not create a word.
     *
     * @param row
     * @param col
     * @param word
     * @param position
     * @param length
     * @return boolean
     */
    public boolean verticalHelper(int row, int col, String word, int position,
                                  int length){
        if(position >= length){
            return true;
        }
        String temp = getHorPrefix(row,col);
//        int i =1;
////        StringBuilder hold = new StringBuilder();
//        String temp = "";
//        //append letter tiles to the left of the position to the front of the string
//        while(col-i >= 0 && !gameBoard.get(row).get(col-i).isEmpty() ){
////            StringBuilder temp = new StringBuilder();
//
////            temp.append(gameBoard.get(row).get(col-i).getPiece().getLetter());
////            temp.append(hold);
////            hold = temp;
//            temp = gameBoard.get(row).get(col-i).getPiece().getLetter() +temp;
//            i++;
//        }
        temp += word.charAt(position);
//        hold.append(word.charAt(position));
        // append letters to the right of the position to the end of the string
        int i =1;
        while(col +i <size && !gameBoard.get(row).get(col+i).isEmpty() ){
            char hold =gameBoard.get(row).get(col+1).getPiece().getLetter();
            hold = Character.toLowerCase(hold);
            temp += hold;
//            hold.append(gameBoard.get(row).get(col+i).getPiece().getLetter());
            i++;
        }
        // should have 1 letter if no connections otherwise
        if(temp.length() > 1){
            if(!dictionary.search(temp,null,0, temp.length())){
                return false;
            }
        }
        return verticalHelper(row+1,col,word,position+1,length);
    }



    /** Returns the String representation of the board
     *
     * @return String
     */
    @Override
    public String toString(){
        String hold = "";

        for(ArrayList<BoardTile> row: gameBoard){
            for(BoardTile col: row){
                if(col.isEmpty()){
                    if(col.isBonus()){

                        if(col.getLetterBonus() >0){
                            hold += "."+col.getLetterBonus() +" ";
                        } else {
                            hold += col.getWordBonus() +". ";
                        }

                    } else{
                        hold +=".. ";
                    }

                } else{
                    hold += " "+col.getPiece().getLetter()+" ";
                }

            }
            hold += "\n";
        }
        return hold;
    }

    public static void main(String[] args){

        int length = args.length;
        Dictionary dict = new Dictionary();
        Board board = new Board(dict);
        String config;
//        if(length>0){
//            String temp = args[0];
            config = board.readBoard("test.txt");
          System.out.println(config);
            board.configBoard(config);

//        }
        System.out.print(board.toString());

        System.out.println("is legal ");
        int legal = board.isLegal(8,7,"to", Direction.HORIZONTAL);
        if(legal>0){
            System.out.println("yay");
        }







//        Board test = new Board(new Dictionary());
//
//        test.setTile(7,7,new Letters('a'));
////        test.setTile( 8, 7, new Letters('d'));
//        if(test.isLegal(8,7,"rachnid",Direction.VERTICAL)){
//            System.out.println("move one T");
//            // play word
//
//            System.out.print(test.toString());
//        }else {
//            System.out.println("move one F");
//        }
//        if(test.isLegal(7,8,"rachnid",Direction.HORIZONTAL)){
//            System.out.println("move two T");
//            // play word
//            System.out.print(test.toString());
//        } else{
//            System.out.println("move two F");
//        }


    }

}