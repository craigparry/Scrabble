/**Craig Parry This class is used for the scrabble game and is the BoardTiles
 *  used for the Scrabble game
 * Use with:
 * Board.java, Computer.java, Letters.java, Dictionary.java, Direction.java,
 * Human.java, LetterBag.java, Player.java, MainGame.java, ScrabbleGUI.java,
 * and WordSolver.java
 */

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

    /** This method gets the value of whether the tile is empty
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return empty;
    }

    /** This method sets the value of empty
     *
     * @param empty
     * @return void
     */
    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    /**Gets bonus boolean value
     *
     * @return boolean
     */
    public boolean isBonus() {
        return bonus;
    }

    /** Sets the bonus boolean value for the tile
     *
     * @param bonus
     * @return void
     */
    public void setBonus(boolean bonus) {
        this.bonus = bonus;
    }

    /** Gets the BoardTiles letter
     *
     * @return Letters
     */
    public Letters getPiece() {
        return piece;
    }

    /** Sets the boardtiles letter
     *
     * @param letter
     * @return void
     */
    public void setPiece(Letters letter) {
        this.piece = letter;
    }

    /**Gets the letter bonus value
     *
     * @return int
     */
    public int getLetterBonus() {
        return LetterBonus;
    }

    /**Sets the letter bonus value
     *
     * @param letterBonus
     * @return void
     */
    public void setLetterBonus(int letterBonus) {
        this.LetterBonus = letterBonus;
    }

    /** Gets the word bonus value
     *
     * @return int
     */
    public int getWordBonus() {
        return wordBonus;
    }

    /**Sets the word bonus value
     *
     * @param wordBonus
     * @return void
     */
    public void setWordBonus(int wordBonus) {
        this.wordBonus = wordBonus;
    }

    /** Gets the string representation of the BoardTile
     *
     * @return String
     */
    @Override
    public String toString() {
        return Character.toString(piece.getLetter());

    }
}