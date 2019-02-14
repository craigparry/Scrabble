package scrabble;
import java.util.*;

public class LetterBag {
    private Collection<Letters> bag;
    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
//    private Map<Character, Integer> freqMap;

    public LetterBag(){
        bag = new LinkedList<>();
//        freqMap = new HashMap<>();
        fillBag();


    }
    public void fillBag(){
        bag.clear();
        for(int i = 0; i< alphabet.length(); i++){

            int fre = frequency(alphabet.charAt(i));

            for(int j = 0; j < fre; j++){
                bag.add(new Letters(alphabet.charAt(i)));
            }
        }

    }


    protected int frequency(char letter){
        int freq;

        switch(letter){
            case 'z':
            case 'x':
            case 'j':
            case 'k':
            case 'q': freq = 1;
            break;

            case 'b':
            case 'c':
            case 'f':
            case 'h':
            case 'm':
            case 'p':
            case 'v':
            case 'w':
            case 'y': freq = 2;
            break;

            case 'g': freq = 3;
            break;

            case 's':
            case 'd':
            case 'l':
            case 'u': freq = 4;
            break;

            case 'n':
            case 'r':
            case 't': freq = 6;
            break;

            case 'o': freq = 8;
            break;

            case 'a':
            case 'i': freq = 9;
            break;

            case 'e': freq = 12;
            break;

            default: freq =2;

        }

        return freq;
    }


}
