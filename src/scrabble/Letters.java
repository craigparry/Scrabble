package scrabble;

import java.util.List;

public class Letters {

    private char letter;
    private int value;
    private boolean blank;

    public Letters(){
        blank =true;
        letter = '*';
    }
    public Letters(char let){
        letter = let;
        value = letterValue(let);

    }

    public char getLetter() {
        return letter;
    }

    public int getValue() {
        return value;
    }
    public boolean getBlank(){
        return blank;
    }

    public void setBlankValue(char let){
        value = letterValue(let);
        letter = Character.toUpperCase(let);

    }
//    public int scoreWord(String word){
//        int length = word.length(){
//
//        }
//
//    }

//    public int scoreWord(List<Character> boardChar, List<Character> tray, String word){
//        int length = word.length();
//        int score =0;
//        int trayChars =0;
//
//        for(int i = 0; i< length; i++){
//            char temp = word.charAt(i);
//            if(boardChar.contains(temp) || tray.contains(temp)){
//                score += letterValue(temp);
//            } else{
//                trayChars++;
//            }
//        }
//        return score;
//    }
    @Override
    public String toString(){
        return Character.toString(letter);
    }


    public int letterValue(char letter){
        int val;

        switch(letter){
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
            case 'l':
            case 'n':
            case 's':
            case 't':
            case 'r': val =1;
            break;

            case 'd':
            case 'g': val =2;
            break;

            case 'b':
            case 'c':
            case 'm':
            case 'p': val =3;
            break;

            case 'f':
            case 'h':
            case 'v':
            case 'w':
            case 'y': val =4;
            break;

            case 'k': val =5;
            break;

            case 'j':
            case 'x': val =8;
            break;

            case 'q':
            case 'z': val =10;
            break;

            default: val =0;

        }

        return val;
    }


}
