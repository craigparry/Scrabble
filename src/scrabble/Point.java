package scrabble;

public class Point{
    private final int row;
    private final int col;
    protected final Letters letter;

    public Point(int r, int c, Letters let){
        row =r;
        col = c;
        letter = let;
    }


    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Letters getLetter() {
        return letter;
    }


}
