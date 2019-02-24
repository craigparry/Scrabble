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
    public void configBoard(String boardStr){
        /*maybe use this board config to clone the game
        board and plug in the values of the word that we are
        testing for a legal move.
         */




        int row =0;
        int col =0;
        int position = 0;
        char c;
        while(position< boardStr.length()){
            c = boardStr.charAt(position);
            if(c == '.'){
                gameBoard.get(row).get(col).setEmpty(true);

                col++;
            }
            if(Character.isAlphabetic(c)){
                gameBoard.get(row).get(col).setEmpty(false);
                gameBoard.get(row).get(col).setPiece(new Letters(c));

                col++;
            }
            if(Character.isDigit(c)){
                gameBoard.get(row).get(col).setBonus(true);
                gameBoard.get(row).get(col).setMultiplier(Character.getNumericValue(c));
                gameBoard.get(row).get(col).setEmpty(true);
                col++;
            }
            if(c == '\n'){
                row++;
            }
            position++;
        }
    }

    public String readBoard(){
        String config = "";
        try(
                BufferedReader reader = new BufferedReader(new FileReader("boardconfig.txt"))){
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

//                System.out.println("row " + (row-1)+ " col " + col);
//                if(gameBoard.get(row-1).get(col).isEmpty()){
//                    System.out.println("true");
//                }
//
//                System.out.println("row " + (row+length)+ " col " + col);
//                if(gameBoard.get(row +length).get(col).isEmpty()){
//                    System.out.println("true");
//                }

                return false;
            }
            // row above the play is not empty
//            System.out.println("row"+ (row-1));
//            System.out.println("col"+ (col));

            if(!gameBoard.get(row-1).get(col).isEmpty()){
//                System.out.print("here");
                // check rows above the play
                // add letters to front of word
//                StringBuilder hold = new StringBuilder();
                String temp ="";
                int i  =1;
                while(!gameBoard.get(row).get(col).isEmpty() && row -i >=0){
//                    StringBuilder temp = new StringBuilder();
                    temp = gameBoard.get(row -i).get(col).getPiece().getLetter() +temp;
//                    temp.append(gameBoard.get(row -i).get(col).getPiece().getLetter());
//                    temp.append(hold);
//                    hold = temp;
                    i++;
                }
//                hold.append(temp);
                // add letters passed to the end of word
//                hold.append(word);
                // if found in the dictionary check the horizontal plays it might make
                if(dictionary.search(temp,null,0,temp.length())){
                    // if false then connection on word isn't legal
                    if(!verticalHelper(row,col, word,0,length)){
                        return false;
                    }
                }
            }
            return true;

        } else{
            if(length+col >= size){
                return false;
            }
            // check for position minus one of the word for a lettered tile
            // check for position plus word length for a lettered tile
        }


        return false;
    }

    public boolean horizontalHelper(int row, int col, String word, int position,
                                    int length){
        if(position > length){
            return true;
        }

        int i =1;
        StringBuilder hold = new StringBuilder();
        //append letter tiles to the left of the position to the front of the string
        while(!gameBoard.get(row-i).get(col).isEmpty() && row -i >=0){
            StringBuilder temp = new StringBuilder();

            temp.append(gameBoard.get(row-i).get(col).getPiece().getLetter());
            temp.append(hold);
            hold = temp;
            i++;
        }
        hold.append(word.charAt(position));
        // append letters to the right of the position to the end of the string
        i =1;
        while(!gameBoard.get(row+i).get(col).isEmpty() && row +i <size){
            hold.append(gameBoard.get(row+i).get(col).getPiece().getLetter());
            i++;
        }
        // should have 1 letter if no connections otherwise
        if(hold.length() > 1){
            if(!dictionary.search(hold.toString(),null,0, hold.length())){
                return false;
            }
        }

        return horizontalHelper(row,col+1,word, position+1,length);
    }

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
        if(position > length){
            return true;
        }

        int i =1;
//        StringBuilder hold = new StringBuilder();
        String temp = "";
        //append letter tiles to the left of the position to the front of the string
        while(!gameBoard.get(row).get(col-i).isEmpty() && col -i >=0){
//            StringBuilder temp = new StringBuilder();
            temp = gameBoard.get(row).get(col -i).getPiece().getLetter() +temp;
//            temp.append(gameBoard.get(row).get(col-i).getPiece().getLetter());
//            temp.append(hold);
//            hold = temp;
            i++;
        }
        temp += word.charAt(position);
//        hold.append(word.charAt(position));
        // append letters to the right of the position to the end of the string
        i =1;
        while(!gameBoard.get(row).get(col+i).isEmpty() && col +i <size){
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
                        hold += col.getMultiplier() +" ";
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

    public class BoardTile{
        private boolean empty;
        private boolean bonus;
        private Letters piece;
        private int multiplier;

        public BoardTile(){
            empty = true;
            multiplier =0;
            piece =null;
            bonus = false;


        }

        public boolean isEmpty() {
            return empty;
        }

        public void setEmpty(boolean empty) {
            this.empty = empty;
        }

        public boolean isBonus() {
            return bonus;
        }
        public void setBonus(boolean bonus) {
            this.bonus = bonus;
        }
        public Letters getPiece() {
            return piece;
        }

        public void setPiece(Letters letter) {
            this.piece = letter;
        }

        public int getMultiplier() {
            return multiplier;
        }

        public void setMultiplier(int multiplier) {
            this.multiplier = multiplier;
        }
        @Override
        public String toString(){
            return piece.toString();

        }
    }

    public static void main(String[] args){
        Board test = new Board(new Dictionary());

        test.setTile(7,7,new Letters('a'));
//        test.setTile( 8, 7, new Letters('d'));
        if(test.isLegal(8,7,"rachnid",Direction.VERTICAL)){
            System.out.println("move one T");
        }else {
            System.out.println("move one F");
        }
        if(test.isLegal(7,8,"rachnid",Direction.HORIZONTAL)){
            System.out.println("move two T");
        } else{
            System.out.println("move two F");
        }

        System.out.print(test.toString());
    }

}