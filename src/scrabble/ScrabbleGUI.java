package scrabble;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.canvas.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.*;

public class ScrabbleGUI extends Application {
    private int size;

    /**
     * ovverides the start method of application to start the scene of the GUI
     * @param stage
     * @return void
     */
    @Override
    public void start(Stage stage) {
        size =500;
        stage.setTitle("Scrabble Game");
        BorderPane screen = new BorderPane();

        // might want to use a grid pane as opposed to the canvas drawing
        Canvas playingBoard = new Canvas();
        VBox rightSide = new VBox();
        HBox tray = new HBox();

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
