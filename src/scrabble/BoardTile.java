package scrabble;

public class BoardTile {
    private boolean empty;
    private boolean bonus;
    private Letters piece;
    private int LetterBonus;
    private int wordBonus;

    public BoardTile() {
        empty = true;
        LetterBonus = 0;
        piece = null;
        bonus = false;


    }
    public BoardTile(Letters letter) {
        empty = false;
        piece = letter;
        LetterBonus =0;
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

    public int getLetterBonus() {
        return LetterBonus;
    }

    public void setLetterBonus(int letterBonus) {
        this.LetterBonus = letterBonus;
    }

    public int getWordBonus() {
        return wordBonus;
    }

    public void setWordBonus(int wordBonus) {
        this.wordBonus = wordBonus;
    }
    @Override
    public String toString() {
        return piece.toString();

    }
}