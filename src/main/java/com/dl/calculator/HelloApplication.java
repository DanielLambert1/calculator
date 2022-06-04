package com.dl.calculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static java.lang.String.valueOf;

/*
    Simple calculator built with JavaFX that uses the shunting yard algorithm and expression parsing
 */

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {// throws IOException {
        stage.setTitle("cflulator!");

        Button[] btns = new Button[16];
        TextField tf = new TextField("");

        GridPane grid = new GridPane();
        BorderPane parent = new BorderPane();

        parent.setTop(tf);
        tf.setMaxWidth(Double.MAX_VALUE);
        tf.setMaxHeight(Double.MAX_VALUE);

        parent.setCenter(grid);

        //SET UP BUTTONS
        for (int i = 0; i < 1; i++) {
            btns[0] = new Button("0");
            btns[1] = new Button("1");
            btns[2] = new Button("2");
            btns[3] = new Button("3");
            btns[4] = new Button("4");
            btns[5] = new Button("5");
            btns[6] = new Button("6");
            btns[7] = new Button("7");
            btns[8] = new Button("8");
            btns[9] = new Button("9");
            btns[10] = new Button("<-");
            btns[11] = new Button("=");
            btns[12] = new Button("+");
            btns[13] = new Button("-");
            btns[14] = new Button("*");
            btns[15] = new Button("/");

            grid.getChildren().addAll(  btns[0], btns[1], btns[2], btns[3],
                    btns[4], btns[5], btns[6], btns[7],
                    btns[8], btns[9], btns[10], btns[11],
                    btns[12], btns[13], btns[14], btns[15]);

            //positioning of buttons
            GridPane.setColumnIndex(btns[0], 0);
            GridPane.setRowIndex(btns[0], 4);
            GridPane.setColumnIndex(btns[1], 0);
            GridPane.setRowIndex(btns[1], 3);
            GridPane.setColumnIndex(btns[2], 1);
            GridPane.setRowIndex(btns[2], 3);
            GridPane.setColumnIndex(btns[3], 2);
            GridPane.setRowIndex(btns[3], 3);
            GridPane.setColumnIndex(btns[4], 0);
            GridPane.setRowIndex(btns[4], 2);
            GridPane.setColumnIndex(btns[5], 1);
            GridPane.setRowIndex(btns[5], 2);
            GridPane.setColumnIndex(btns[6], 2);
            GridPane.setRowIndex(btns[6], 2);
            GridPane.setColumnIndex(btns[7], 0);
            GridPane.setRowIndex(btns[7], 1);
            GridPane.setColumnIndex(btns[8], 1);
            GridPane.setRowIndex(btns[8], 1);
            GridPane.setColumnIndex(btns[9], 2);
            GridPane.setRowIndex(btns[9], 1);
            GridPane.setColumnIndex(btns[10], 1);
            GridPane.setRowIndex(btns[10], 4);
            GridPane.setColumnIndex(btns[11], 2);
            GridPane.setRowIndex(btns[11], 4);
            GridPane.setColumnIndex(btns[12], 3);
            GridPane.setRowIndex(btns[12], 4);
            GridPane.setColumnIndex(btns[13], 3);
            GridPane.setRowIndex(btns[13], 3);
            GridPane.setColumnIndex(btns[14], 3);
            GridPane.setRowIndex(btns[14], 1);
            GridPane.setColumnIndex(btns[15], 3);
            GridPane.setRowIndex(btns[15], 2);

            for (Button btn : btns) btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        }

        //defining button behaviour
        btns[0].setOnAction(actionEvent -> tf.appendText("0"));
        btns[1].setOnAction(actionEvent -> tf.appendText("1"));
        btns[2].setOnAction(actionEvent -> tf.appendText("2"));
        btns[3].setOnAction(actionEvent -> tf.appendText("3"));
        btns[4].setOnAction(actionEvent -> tf.appendText("4"));
        btns[5].setOnAction(actionEvent -> tf.appendText("5"));
        btns[6].setOnAction(actionEvent -> tf.appendText("6"));
        btns[7].setOnAction(actionEvent -> tf.appendText("7"));
        btns[8].setOnAction(actionEvent -> tf.appendText("8"));
        btns[9].setOnAction(actionEvent -> tf.appendText("9"));

        //remove 1 character
        btns[10].setOnAction(actionEvent -> {
            if (!(tf.getText().isEmpty()))
                tf.setText(tf.getText().substring(0, tf.getText().length() - 1));

        });

        //call string parser to get result
        btns[11].setOnAction(actionEvent -> {
            Integer result = stringParser.evaluate(tf.getText());
            if (result == -1) { tf.setText("Syntax ERROR"); }
            else tf.setText(valueOf(result));

        });

        btns[12].setOnAction(actionEvent -> tf.appendText("+"));
        btns[13].setOnAction(actionEvent -> tf.appendText("-"));
        btns[14].setOnAction(actionEvent -> tf.appendText("*")); //x
        btns[15].setOnAction(actionEvent -> tf.appendText("/")); //÷

        //To fill full grid size with buttons
        for (int i = 0; i < 5; i++){
            RowConstraints rc = new RowConstraints();
            rc.setVgrow(Priority.ALWAYS) ;
            rc.setFillHeight(true);
            grid.getRowConstraints().add(rc);
        }
        for (int i = 0; i < 4; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setHgrow(Priority.ALWAYS); // allow column to grow
            cc.setFillWidth(true); // ask nodes to fill space for column
            grid.getColumnConstraints().add(cc);
        }

        stage.setScene(new Scene(parent, 640, 480));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}