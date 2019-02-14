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

}

}
