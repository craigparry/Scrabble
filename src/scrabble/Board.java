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
    public void configBoard(String board){
        int row =0;
        int col =0;
        int position = 0;
        char c;
        while(position< board.length()){
            c = board.charAt(position);
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

        if(row >= size || row < 0) return false;
        if(col >= size || col < 0) return false;
        int length = word.length();
        if(this.boardEmpty() && row != size/2 && col != size/2){
            return false;
        }

        if(dir == Direction.VERTICAL){
            if(length+row >= size){
                return false;
            }
            // check for position minus one of the word for a lettered tile
            // check for position plus word length for a lettered tile
            if(gameBoard.get(row-1).get(col).isEmpty()
                    && gameBoard.get(row +length).get(col).isEmpty()){
                return false;
            }

            if(!gameBoard.get(row-1).get(col).isEmpty()){
                String hold = "";
                hold += Character.toString(gameBoard.get(row-1).get(col).getPiece().getLetter());
                hold += word;
                //gameBoard.get(row-1).get(col).getPiece().getLetter()
                // use this please dictionary.search(hold,null,0,hold.length());
            }

        } else{
            if(length+col >= size){
                return false;
            }
            // check for position minus one of the word for a lettered tile
            // check for position plus word length for a lettered tile
        }


        return false;
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
    }

    public static void main(String[] args){
        Board test = new Board(new Dictionary());

        test.setTile(7,7,new Letters('a'));
        if(test.isLegal(7,8,"arachnid",Direction.VERTICAL)){
            System.out.println("move one T");
        }else {
            System.out.println("move one F");
        }
        if(test.isLegal(7,8,"arachnid",Direction.HORIZONTAL)){
            System.out.println("move two T");
        } else{
            System.out.println("move two F");
        }

        System.out.print(test.toString());
    }

}