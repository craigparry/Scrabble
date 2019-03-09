package scrabble;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import java.util.*;



public class ScrabbleGUI extends Application {
    private int size;
    private MainGameLoop game;
    private GridPane playingBoard;
    private HBox tray;
    private Canvas selected;
    private Letters selectLetter;
    private Board copy;
    private List<Canvas> selectedList;
    private List<Point> playsList;
    private Label comp;
    private Label hum;



    private Canvas drawLetter(BoardTile tile) {
        int squareSize = size/15;
        Canvas canvas;
        GraphicsContext gc;
        canvas = new Canvas(squareSize, squareSize);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BEIGE);

        int bonus;
//        System.out.println("letterB:" +tile.getLetterBonus());
//        System.out.println("wordB:" +tile.getWordBonus());
        if((bonus =tile.getLetterBonus()) >0){
//            System.out.println("here");
            if(bonus ==2){

                gc.setFill(Color.LIGHTBLUE);
            }else{
                gc.setFill(Color.CADETBLUE);
            }

        }else if((bonus =tile.getWordBonus()) >0){
            if(bonus == 2){
                gc.setFill(Color.LIGHTPINK);
            }else{
                gc.setFill(Color.RED);
            }
        }


        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
        if(!tile.isEmpty()){
            gc.setStroke(Color.BLACK);
            gc.setFont(new Font("Regular", squareSize/2));
//            gc.strokeText(t,(squareSize/2)-5,(squareSize/2)+5,squareSize););
            gc.strokeText(Character.toString(tile.getPiece().getLetter()),
                    (squareSize/2)-(squareSize/7),(squareSize/2)+5,squareSize);
            gc.setFont(new Font("regular",squareSize/3));
            gc.strokeText(Integer.toString(tile.getPiece().getValue()),6*squareSize/8,
                    7*squareSize/8,squareSize/5);
        }


        return canvas;
    }

    private void drawBoard(){


        int boardSize = game.getGameBoard().getSize();

        for(int row =0; row<boardSize; row++){
            for(int col =0; col<boardSize; col++){
                BoardTile tileTemp = game.getGameBoard().getGameBoard().get(row).get(col);
                Canvas temp = drawLetter(tileTemp);


//                temp.setOnMouseClicked(e->this.dropSelected(temp));
                int finalCol = col;
                int finalRow = row;
                temp.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
//            System . out . println (" pressed "
//                    + event . getX () + " " + event . getY ());
//            System.out.println(domino.toString());
                    //play move and put into game board at this point


                    // add letter selected to the list of selected pieces
                    // remove the cnavas object from the tray
                    //
//                    copy = game.getGameBoard().copyBoard(game.getGameBoard());
//                    get
                    if(selectLetter != null){
                        playsList.add(new Point(finalRow, finalCol,selectLetter));
                        drawLetter(temp, selectLetter);
                        selectLetter =null;

                        if(selected!= null){
                            tray.getChildren().remove(selected);

                            //maybe make a map to hold the locations of
                            // the letters as well
                            // test them for legality
                            selectedList.add(selected);
                            selected =null;
                        }
                    }



                });
                playingBoard.add(temp,col,row);


            }
        }
    }

    public void drawLetter(Canvas can, Letters letter){
        GraphicsContext gc = can.getGraphicsContext2D();
        int squareSize = size/15;

        gc.setStroke(Color.BLACK);
        gc.setFont(new Font("Regular", squareSize/2));
//            gc.strokeText(t,(squareSize/2)-5,(squareSize/2)+5,squareSize););
        gc.strokeText(Character.toString(letter.getLetter()),
                (squareSize/2)-(squareSize/7),(squareSize/2)+5,squareSize);
        gc.setFont(new Font("regular",squareSize/3));
        gc.strokeText(Integer.toString(letter.getValue()),6*squareSize/8,
                7*squareSize/8,squareSize/5);
    }
//    private void dropSelected(Canvas can ){
//        can = drawLetter()
//    }
    private void drawTray(){
        tray.getChildren().clear();
        game.getHuman().getTray();
        for(Letters letter: game.getHuman().getTray()){
            Canvas temp =drawLetter(new BoardTile(letter));
            // add listener here
//            if( selected != null && (selected != letter)){
//                temp.setOnMouseMoved(e->this.clearSelected(temp));
//            }

            temp.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
//            System . out . println (" pressed "
//                    + event . getX () + " " + event . getY ());
//            System.out.println(domino.toString());
                //play move and put into game board at this point

                // on click make select the piece


                if(selected != null){
                    clearSelected(selected);
                }
                selected = temp;
                selectLetter = letter;
                GraphicsContext gc = temp.getGraphicsContext2D();
                gc.setStroke(Color.RED);
                gc.setLineWidth(2);
                gc.strokeRect(0, 0, temp.getWidth(), temp.getHeight());

            });

            tray.getChildren().add(temp);
        }
    }
    private void clearSelected(Canvas can){

        GraphicsContext gc = can.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeRect(0, 0, can.getWidth(), can.getHeight());
    }

    private void clearPlayed(){

    }
    private void removeLet(Canvas let){
        tray.getChildren().remove(let);
    }

    /**
     * ovverides the start method of application to start the scene of the GUI
     * @param stage
     * @return void
     */
    @Override
    public void start(Stage stage) {
        game = new MainGameLoop();
        selectedList = new LinkedList<>();
        selected = null;
        selectLetter = null;
        playsList = new LinkedList<>();
        hum = new Label("Player: 0");
        comp = new Label("Computer: 0");

        size =700;
        stage.setTitle("Scrabble Game");
        BorderPane screen = new BorderPane();

        // might want to use a grid pane as opposed to the canvas drawing
        playingBoard = new GridPane();
//        playingBoard.getChildren().add(drawLetter(new Letters('c')));
        drawBoard();
        VBox rightSide = new VBox();
        rightSide.setSpacing(size/100);
        rightSide.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,
                null, null)));
        rightSide.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        tray = new HBox();
        tray.setAlignment(Pos.CENTER);
        tray.setSpacing(size/100);
        tray.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        tray.setBackground(new Background(new BackgroundFill(Color.SADDLEBROWN,
                null, null)));
        drawTray();
        Button reset = new Button("New Game");
        Button clear = new Button("Clear");
        clear.setOnAction(event -> {});
        reset.setOnAction(event -> {});

        Button play = new Button("Play");
        play.setOnAction(event -> {
            // draw domino from graveyard and put in player tray
//            int x=-1;
//            int y =-1;
//
//            for(Point p: playsList){
//                int tempX= p.getCol();
//                int tempY= p.getRow();
//                if(x == -1){
//                    x = tempX;
//                }
//                if(y == -1){
//                    y=tempY;
//                }
//
//            }

//            if(game.getHuman().playMove(playsList)){
//                System.out.print("human play move working ");
//
//            }

//            boolean legal = false;
            if(!playsList.isEmpty() && game.getHuman().playMove(playsList)){

                // play the turn
                // update score and label
                drawBoard();
                game.getHuman().drawTray(game.getBag());
                drawTray();
                hum.setText("Player: "+game.getHuman().getPoints());
                // then the computer turn
                // update computer score and label

                game.getComputer().playTurn();
                comp.setText("Computer: "+game.getComputer().getPoints());
                drawBoard();
                playsList.clear();
                selectLetter =null;
                selected =null;

            } else{
                // redraw tray
//                playingBoard.getChildren().clear();

                drawBoard();
                drawTray();
                playsList.clear();
                selectLetter =null;
                selected =null;
            }
        });

        Button pass = new Button("Pass");
        pass.setOnAction(event -> {
            // draw domino from graveyard and put in player tray
            playingBoard.getChildren().clear();
            game.getComputer().playTurn();
            comp.setText("Computer: "+game.getComputer().getPoints());
            drawBoard();
            playsList.clear();
            selectLetter =null;
            selected =null;

        });

        rightSide.getChildren().addAll(reset,play,clear,pass,hum,comp);
//        rightSide.setMinWidth(size/10);
        screen.setMinSize(size,size);

        BorderPane.setAlignment(playingBoard,Pos.CENTER);
        BorderPane.setAlignment(rightSide, Pos.CENTER_RIGHT);
        BorderPane.setAlignment(tray,Pos.BOTTOM_CENTER);

        screen.setCenter(playingBoard);
        screen.setRight(rightSide);
        screen.setBottom(tray);


        stage.setScene(new Scene(new StackPane(screen)));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
