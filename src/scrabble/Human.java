/**Craig Parry This class is used for the scrabble game and is the human player
 *  used for the Scrabble game
 * Use with:
 * Board.java, BoardTile.java, Letters.java, Dictionary.java, Direction.java,
 * Computer.java, LetterBag.java, Player.java, MainGameLoop.java, ScrabbleGUI.java,
 * and WordSolver.java
 */
package scrabble;
import java.util.*;

public class Human extends Player {

    public Human(LetterBag bag, Board board,Dictionary dictionary){
        super(bag,board,dictionary);
    }

    public boolean playMove(List<Point> list){
        int x = -1;
        int y = -1;
        System.out.println(gameBoard.toString());


        boolean hor =false;
        boolean vert = false;
        Board boardCopy = gameBoard.copyBoard(gameBoard);

        for(Point p: list){
            boardCopy.setTile(p.getRow(),p.getCol(),p.getLetter());
            if(x == -1){
                x = p.getCol();
            }
            if(y == -1){
                y=p.getRow();
            }

            if(x != -1 && x != p.getCol()){
                hor =true;
            }
            if(y != -1 && y != p.getRow()){
                vert =true;
            }
        }

        if(hor == true && vert == true && list.size()>1){

            System.out.print("broke here2");
            return false;
        }
        String word="";

        Point temp =list.get(0);
        int legal= -1;
        List<Character> charList =new LinkedList<>();
        for(Letters l : tray){
            charList.add(l.getLetter());
        }

//        if(list.size()==1){
//            word +=temp.getLetter();
//            legal =boardCopy.isLegal(temp.getRow(),temp.getCol(),word,charList,Direction.HORIZONTAL);
//            if(legal >=0){
//                for(Point p: list){
//                    gameBoard.setTile(p.getRow(),p.getCol(),p.getLetter());
//                    removeLetter(p.getLetter().getLetter());
//                    points += gameBoard.scoreWord(temp.getRow(),temp.getCol(),charList,word,Direction.HORIZONTAL);
//                }
//                return true;
//            }
//            legal =boardCopy.isLegal(temp.getRow(),temp.getCol(),word,charList,Direction.VERTICAL);
//            if(legal >=0){
//                for(Point p: list){
//                    gameBoard.setTile(p.getRow(),p.getCol(),p.getLetter());
//                    removeLetter(p.getLetter().getLetter());
//                    points += gameBoard.scoreWord(temp.getRow(),temp.getCol(),charList,word,Direction.VERTICAL);
//                }
//
//                return true;
//            }
//        }
//        int size = boardCopy.getSize();




//        if(hor){
        int prefixLen;
           word += boardCopy.getHorPrefix(temp.getRow(),temp.getCol());
           prefixLen =word.length();
           word += temp.getLetter().getLetter();
           word += boardCopy.getHorSufix(temp.getRow(),temp.getCol());
           for(int i =0; i< word.length();i++){
               if(!charList.contains(word.charAt(i))&& !charList.contains('*')){
                   charList.add(word.charAt(i));
               }
           }

           legal =gameBoard.isLegal(temp.getRow(),temp.getCol()-prefixLen,word,charList,Direction.HORIZONTAL);
            if(legal >=0){
                for(Point p: list){
                    gameBoard.setTile(p.getRow(),p.getCol(),p.getLetter());
                    removeLetter(p.getLetter().getLetter());
                    points += gameBoard.scoreWord(temp.getRow(),temp.getCol(),charList,word,Direction.HORIZONTAL);
                }

                return true;
            }
//        }
        word ="";
//        if(vert){

            word += boardCopy.getVertPrefix(temp.getRow(),temp.getCol());
            prefixLen = word.length();
            word += temp.getLetter().getLetter();
            word += boardCopy.getVertSufix(temp.getRow(),temp.getCol());
            System.out.println("Vertical: " + word);
        charList.clear();
        for(Letters l : tray){
            charList.add(l.getLetter());
        }
        for(int i =0; i< word.length();i++){
            if(!charList.contains(word.charAt(i))&& !charList.contains('*')){
                charList.add(word.charAt(i));
            }
        }
            legal =gameBoard.isLegal(temp.getRow()-prefixLen,temp.getCol(),word,charList,Direction.VERTICAL);

            if(legal >=0){
                for(Point p: list){
                    gameBoard.setTile(p.getRow(),p.getCol(),p.getLetter());
                    removeLetter(p.getLetter().getLetter());
                    points += gameBoard.scoreWord(temp.getRow(),temp.getCol(),charList,word,Direction.VERTICAL);
                }

                return true;
            }
//        }


        return false;
    }

    public boolean playTurn(){
        return false;
    }
    /*this may need to be set up in each individually so that the
     * functionality differs between the computer and the human player
     * */
}
