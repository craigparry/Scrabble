package scrabble;
import java.io.*;
import java.util.*;
public class Board {

    public int getSize() {
        return size;
    }

    private int size;
    private ArrayList<ArrayList<BoardTile>> gameBoard;
    private Dictionary dictionary;

    public Board(Dictionary dict){
        dictionary = dict;
        size =15;
        initBoard();
    }

    public ArrayList<ArrayList<BoardTile>> getGameBoard() {
        return gameBoard;
    }

    public Board(int x, Dictionary dict){
        dictionary = dict;
        size =x;
        initBoard();
    }
    public void configBoard(String boardStr){
        /*maybe use this board config to clone the game
        board and plug in the values of the word that we are
        testing for a legal move.
         */



        //todo: debug the issues here, the board is reading in, need to configure correct
        int row =0;
        int col =0;
        int position = 0;
        char c;
        String temp = "";
        c = boardStr.charAt(position);
        if(Character.isDigit(c)){

            while(c != '\n'){

                temp += c;
                position++;
                c = boardStr.charAt(position);
            }
            System.out.println(temp);

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
    }

    public String readBoard(){

        //todo make it read generically when there is a file from command line
        String config = "";


        try(
                BufferedReader reader = new BufferedReader(new FileReader("scrabble_board.txt"))){
            String line;

            while ((line = reader.readLine()) != null){
                config += line + "\n";
            }
        } catch(IOException ex){
            System.out.println(ex.toString());
        }
        return config;
    }


    public void initBoard(){
        gameBoard = new ArrayList<>(size);
        for(int i = 0; i < size; i++){
            gameBoard.add(new ArrayList<>(size));
            for(int k = 0; k< size; k++){
                if(gameBoard.isEmpty()){
                    System.out.print("Empty");
                }
                gameBoard.get(i).add(new BoardTile());

            }
        }
    }

    public void setTile(int row, int col, Letters let){
        gameBoard.get(row).get(col).setPiece(let);
        gameBoard.get(row).get(col).setEmpty(false);

    }

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

    public boolean isLegal(int row, int col, String word, Direction dir){
        //from the starting position
        // check for the length of the string
        // to see if there are available spaces and if the word matches a
        //word in the dictionary

        /* todo: need to check for the case of a horizontal word played under a horizontal word and a vertical played next to a vertical*/
        /*need to use the position that is passed into the function then
        * we check if that position is occupied. If it is then not legal
        * we want to check for positions one off of an already played tile
        * because thats where we want the play to start from for easier conversion
        * then we want to have the play to check in each direction if the position is
        * occupied to the left or above then we can check for a legal word attached to those positons*/

        if(row >= size || row < 0) return false;
        if(col >= size || col < 0) return false;
        int length = word.length();
        if(this.boardEmpty() && row != size/2 && col != size/2){
            return false;
        }

        if(dir == Direction.VERTICAL){
            if(length+row > size){
                return false;
            }
            // check for position minus one of the word for a lettered tile
            // check for position plus word length for a lettered tile
            if(gameBoard.get(row-1).get(col).isEmpty()
                    && gameBoard.get(row +length).get(col).isEmpty()){

                return false;
            }
            if(!gameBoard.get(row-1).get(col).isEmpty()){
                // check rows above the play
                // add letters to front of word

                String temp ="";
                int i  =1;
                while(row -i >=0 && !gameBoard.get(row-i).get(col).isEmpty()) {
                    temp = gameBoard.get(row -i).get(col).getPiece().getLetter() +temp;
//                    System.out.println("temp = " +temp);
                    i++;
                }
//                System.out.println("here");
                temp += word;
//                System.out.println(word);
//                System.out.println(temp);
                // if found in the dictionary check the horizontal plays it might make
                if(dictionary.search(temp,null,0,temp.length())){
                    // if false then connection on word isn't legal
                    if(!verticalHelper(row,col, word,0,length)){
                        return false;
                    }
                    return true;
                }
            }


        } else{
            if(length+col > size){
                int sum = length+ col;

                return false;
            }

            // check for position minus one of the word for a lettered tile
            // check for position plus word length for a lettered tile
            if(gameBoard.get(row).get(col-1).isEmpty()
                    && gameBoard.get(row).get(col+length).isEmpty()){
                return false;
            }
            if(!gameBoard.get(row).get(col-1).isEmpty()){
                // check rows above the play
                // add letters to front of word

                String temp =getHorPrefix(row,col);
//                int i  =1;
//                while(col - i >= 0 && !gameBoard.get(row).get(col-i).isEmpty()){
//                    temp = gameBoard.get(row).get(col-i).getPiece().getLetter() +temp;
//                    i++;
//                }
                temp += word;

                // if found in the dictionary check the horizontal plays it might make
                if(dictionary.search(temp,null,0,temp.length())){
                    // if false then connection on word isn't legal
                    if(!horizontalHelper(row,col, word,0,length)){
                        return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }


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
            temp += gameBoard.get(row+i).get(col).getPiece().getLetter();

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


    public String getVertPrefix(int row, int col){
        int i =1;

        String temp  ="";
        //append letter tiles to the left of the position to the front of the string
        while( row -i >=0 && !gameBoard.get(row-i).get(col).isEmpty() ){

            temp = gameBoard.get(row-i).get(col).getPiece().getLetter() +temp;
            i++;
        }
        return temp;

    }
    public String getHorPrefix(int row, int col){
        int i =1;
//        StringBuilder hold = new StringBuilder();
        String temp = "";
        //append letter tiles to the left of the position to the front of the string
        while(col-i >= 0 && !gameBoard.get(row).get(col-i).isEmpty() ){
            temp = gameBoard.get(row).get(col-i).getPiece().getLetter() +temp;
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
            temp += gameBoard.get(row).get(col+1).getPiece().getLetter();
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

    public void scorePlay(){

    }

    /**
     *
     * @return
     */
    @Override
    public String toString(){
        String hold = "";

        for(ArrayList<BoardTile> row: gameBoard){
            for(BoardTile col: row){
                if(col.isEmpty()){
                    if(col.isBonus()){

                        if(col.getLetterBonus() >0){
                            hold += col.getLetterBonus() +" ";
                        } else {
                            hold += col.getWordBonus() +" ";
                        }

                    } else{
                        hold +=". ";
                    }

                } else{
                    hold += col.getPiece().getLetter()+" ";
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
            config = board.readBoard();
          System.out.println(config);
            board.configBoard(config);

//        }
        System.out.print(board.toString());
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