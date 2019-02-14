package scrabble;
import java.util.*;
public class Board {

    private int size;
    private List<List<BoardTile>> gameBoard;
    public Board(int x){
        size = x;
        gameBoard = new ArrayList<List<BoardTile>>();
        for(int i = 0; i< size; i++){
            gameBoard.set(i,new ArrayList<>());
            for(int k = 0; k< size; k++){
                gameBoard.get(i).set(k,new BoardTile());

            }
        }
    }

    public class BoardTile{
        private boolean empty;
        private boolean bonus;
        private Letters letter;
        private int multiplier;

        public BoardTile(){
            empty = true;

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

        public Letters getLetter() {
            return letter;
        }

        public void setLetter(Letters letter) {
            this.letter = letter;
        }

        public int getMultiplier() {
            return multiplier;
        }

        public void setMultiplier(int multiplier) {
            this.multiplier = multiplier;
        }
    }

}
