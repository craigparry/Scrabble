package scrabble;


public enum Direction {
    VERTICAL,HORIZONTAL;



    public int getX(Direction dir){
        if(dir == VERTICAL){
            return 0;
        }else{
            return 1;
        }
    }

    public int getY(Direction dir){
        if(dir == VERTICAL){
            return -1;
        }else{
            return 0;
        }
    }
}
