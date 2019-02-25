package scrabble;

public class BoardTile {
    private boolean empty;
    private boolean bonus;
    private Letters piece;
    private int multiplier;

    public BoardTile() {
        empty = true;
        multiplier = 0;
        piece = null;
        bonus = false;


    }
    public BoardTile(Letters letter) {
        empty = false;
        piece = letter;
        multiplier =0;
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
    public String toString() {
        return piece.toString();

    }
}