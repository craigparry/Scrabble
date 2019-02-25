package scrabble;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.text.*;

public class ScrabbleGUI extends Application {
    private int size;
    private MainGameLoop game;
    private GridPane playingBoard;
    private HBox tray;


    private Canvas drawLetter(BoardTile tile) {
        int squareSize = size/15;
        Canvas canvas;
        GraphicsContext gc;
        canvas = new Canvas(squareSize, squareSize);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BEIGE);
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
                Canvas temp = drawLetter(game.getGameBoard().getGameBoard().get(row).get(col));
                playingBoard.add(temp,col,row);

            }
        }
    }

    private void drawTray(){
        game.getHuman().getTray();
        for(Letters letter: game.getHuman().getTray()){
            tray.getChildren().add(drawLetter(new BoardTile(letter)));
        }
    }

    /**
     * ovverides the start method of application to start the scene of the GUI
     * @param stage
     * @return void
     */
    @Override
    public void start(Stage stage) {
        game = new MainGameLoop();


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
        Button play = new Button("Play");
        Button pass = new Button("Pass");

        rightSide.getChildren().addAll(play,pass);
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
