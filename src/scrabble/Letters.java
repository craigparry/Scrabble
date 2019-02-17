package scrabble;

public class Letters {



    private char letter;
    private int value;
    private boolean blank;

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
