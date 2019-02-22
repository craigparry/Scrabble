package scrabble;
import java.io.*;
import java.util.*;
public class Board {

    private int size;
    private ArrayList<ArrayList<BoardTile>> gameBoard;
//    private BoardTile[][] gameBoard;
    private Dictionary dictionary;

    public Board(Dictionary dict){
        dictionary = dict;
        size =15;
        initBoard();
        //broke, do I have to initialize the size of arraylist?

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

        if(this.boardEmpty()){

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
//
//        for(int i =0; i< size; i++){
//            for(int k =0; k<size; k++){
//                if(gameBoard[i][k].isEmpty()){
//                    if(gameBoard[i][k].isBonus()){
//                        hold += gameBoard[i][k].getMultiplier();
//                    } else{
//                        hold +=". ";
//                    }
//
//                } else{
//                    hold += gameBoard[i][k].getPiece().getLetter();
//                }
//
//            }
//            hold += "\n";
//        }
//        return hold;



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

        System.out.print(test.toString());
    }

}
