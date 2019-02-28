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
    public Board getBoard() {
        return board;
    }
    public Dictionary getDictionary() {
        return dictionary;
    }

    public Player getPlayer() {
        return player;
    }

    public static void main(String[] args){
        WordSolver test = new WordSolver();
        String config;
        String tray;
//        if(args.length > 0){
//            config =test.getBoard().readBoard(args[0]);
            config =test.getBoard().readBoard("test.txt");
            tray =test.getBoard().configBoard(config);
//            System.out.print(config);
//            System.out.print(tray);
            test.getPlayer().setTray(tray);
            test.getPlayer().printTray();
            List<Character> charList = new LinkedList<>();
            for(Letters let: test.getPlayer().getTray()){
                charList.add(let.getLetter());
            }
            //todo implement this completely
            List<String> tempList = test.getDictionary().searchUnordered("",charList);
            for(String s: tempList){
                System.out.println(s);
            }
//            test.getPlayer().playTurn();

//        }


    }


}
