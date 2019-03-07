/**Craig Parry This class is used for the scrabble game and is the WordSolver for
 * testing the Scrabble game
 * Use with:
 * Board.java, Computer.java, Letters.java, BoardTile.java, Direction.java,
 * Human.java, LetterBag.java, Player.java, MainGameLoop.java, ScrabbleGUI.java,
 * and Dictionary.java
 */

package scrabble;
import java.util.*;
public class WordSolver {

    private Player player;
    private Board board;
    private Dictionary dictionary;
    private LetterBag bag;



    public WordSolver(){
        bag =new LetterBag();
        dictionary = new Dictionary();
        board = new Board(dictionary);
        player = new Computer(bag,board,dictionary);
    }
    public WordSolver(String file){
        bag =new LetterBag();
        dictionary = new Dictionary(file);
        board = new Board(dictionary);
        player = new Computer(bag,board,dictionary);
    }

    /** returns the board used for the wordsolver
     *
     * @return Board
     */
    public Board getBoard() {
        return board;
    }

    /** Returns the dictionary used for the worldSolver
     *
     * @return Dictionary
     */
    public Dictionary getDictionary() {
        return dictionary;
    }

    /** returns the player used for the wordsolver
     *
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    public static void main(String[] args){
        // todo: fix the input streams to work with the jars and in intellij
        // make scanner for the transcript of boards to be read for the graders to test
        // set up GUI
        // finish comments on code
        // finish readMe
        // finish project diagrams
        // move jars to Git even if not totally functional

        WordSolver test;
        String config;
        String tray;
        if(args.length > 0){
//            test.getDictionary().readDictFile(args[0]);
            test = new WordSolver(args[0]);
        } else{
            test = new WordSolver();
        }

        Scanner sc = new Scanner(System.in);
        String line;
        String boardConfig ="";
        while((line = sc.nextLine()) != null && sc.hasNextLine()){
            if(!line.equals("")){
                boardConfig +=line +"\n";
            }else {
//                boardConfig+="\n";
            }


            if((line.equals("\n") ||line.equals("")) && !boardConfig.equals("")){


                tray = test.getBoard().configBoard(boardConfig);
                test.getPlayer().setTray(tray);
                System.out.print(test.getBoard().toString());
                test.getPlayer().playTurn();
                System.out.println("Move Scored: "+test.getPlayer().getPoints());
                System.out.println(test.getBoard().toString());
                boardConfig ="";
                test.getPlayer().clearPoints();

//                while(line.equals("\n")&& sc.hasNextLine()){
//                    line = sc.nextLine();
//                }

                //todo display points for the move and display the board then
                //reset the players score, tray and boardConfig string for the next
                // board to be read in
                // run test print output
                //

            }

        }
//        System.out.println(boardConfig);











//            config =test.getBoard().readBoard(args[0]);
//            config =test.getBoard().readBoard("test2.txt");
//            tray =test.getBoard().configBoard(config);
//            System.out.print(config);
//            System.out.print(tray);

//            test.getPlayer().setTray(tray);
//            test.getPlayer().printTray();
//            List<Character> charList = new LinkedList<>();
//            for(Letters let: test.getPlayer().getTray()){
//                charList.add(let.getLetter());
//            }
////        System.out.println(test.getBoard().isLegal(7,0,"tacnodes", charList, Direction.HORIZONTAL));
//           // System.out.println(test.getBoard().isLegal(0,7,"goldiest",Direction.VERTICAL));
//            long longb = System.currentTimeMillis();
//           test.getPlayer().playTurn();
//           long longa = System.currentTimeMillis();
//           System.out.println("Time: "+ (longa -longb));
//
//
////            System.out.println("score goldiest: "+ test.getBoard().scoreWord(0,7,charList,"goldiest",Direction.VERTICAL));
////        System.out.println("score doggies: "+ test.getBoard().scoreWord(8,6,charList,"doggies",Direction.HORIZONTAL));
//            System.out.println("Move Scored: "+test.getPlayer().getPoints());
//            System.out.print(test.getBoard().toString());
//            //todo implement this completely
//            charList.add('t');
//            List<String> tempList = test.getDictionary().searchUnordered(new LinkedList<String>(),charList,null);
//            for(String s: tempList){
//                System.out.println(s);
//            }
//            test.getPlayer().playTurn();

//        }


    }


}
