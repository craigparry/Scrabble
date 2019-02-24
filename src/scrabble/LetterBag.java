package scrabble;
import java.util.*;

public class LetterBag {
    public List<Letters> getBag(){
        return bag;
    }

    private List<Letters> bag;
    private Random rand;
    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public LetterBag(){
        bag = new LinkedList<>();
//        freqMap = new HashMap<>();
        rand = new Random();
        fillBag();



    }
    public void fillBag(){
        bag.clear();
        //constructs tiles according to the letter frequencies for the letters
        for(int i = 0; i< alphabet.length(); i++){

            int fre = frequency(alphabet.charAt(i));

            for(int j = 0; j < fre; j++){
                bag.add(new Letters(alphabet.charAt(i)));
            }
        }
        //constructs two default blank tiles
        for(int b = 0; b<=1; b++){
            bag.add(new Letters());
        }
    }

    public Letters draw(){
        int hold = rand.nextInt(bag.size());
        return bag.remove(hold);
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
