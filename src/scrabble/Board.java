package scrabble;
import java.util.*;
public class Board {

    private int size;
//    private List<List<BoardTile>> gameBoard;
    private BoardTile[][] gameBoard;

    public Board(int x){
        size =x;
        gameBoard = new BoardTile[size][size];


        for(int i =0; i< size; i++){
            for(int k =0; k<size; k++) {
            gameBoard[i][k] = new BoardTile();
            }
        }

        //broke, do I have to initialize the size of arraylist?
//        size = x;
//        gameBoard = new ArrayList<List<BoardTile>>(size);
//        for(int i = 0; i < size; i++){
//            gameBoard.add(new ArrayList<>(size));
//            for(int k = 0; k< size; k++){
//                gameBoard.get(i).set(k,new BoardTile());
//
//            }
//        }


    }

    /**
     *
     * @return
     */
    @Override
    public String toString(){
        String hold = "";

        for(int i =0; i< size; i++){
            for(int k =0; k<size; k++){
                if(gameBoard[i][k].getEmpty()){
                    if(gameBoard[i][k].isBonus()){
                        hold += gameBoard[i][k].getMultiplier();
                    } else{
                        hold +="* ";
                    }

                } else{
                    hold += gameBoard[i][k].getPiece().getLetter();
                }

            }
            hold += "\n";
        }
        return hold;



//        for(List<BoardTile> row: gameBoard){
//            for(BoardTile col: row){
//                if(col.getEmpty()){
//                    if(col.isBonus()){
//                        hold += col.getMultiplier();
//                    } else{
//                        hold +="*";
//                    }
//
//                } else{
//                    hold += col.getPiece().getLetter();
//                }
//
//            }
//            hold += "\n";
//        }
//        return hold;
    }

    public class BoardTile{
        private boolean empty;
        private boolean bonus;
        private Letters piece;
        private int multiplier;

        public BoardTile(){
            empty = true;

        }

        public boolean getEmpty() {
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
        Board test = new Board(15);
        System.out.print(test.toString());
    }

}
