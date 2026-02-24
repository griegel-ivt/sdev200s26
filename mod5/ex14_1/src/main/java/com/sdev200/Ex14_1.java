package com.sdev200;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Ex14_1 extends Application {
    @Override
    public void start(Stage primaryStage) {
        
        GridPane pane = new GridPane();

        Image image1 = new Image(getClass().getResourceAsStream("flag1.gif"));
        Image image2 = new Image(getClass().getResourceAsStream("flag2.gif"));
        Image image3 = new Image(getClass().getResourceAsStream("flag6.gif"));
        Image image4 = new Image(getClass().getResourceAsStream("flag7.gif"));

        pane.add(new ImageView(image1), 0, 0);
        pane.add(new ImageView(image2), 1, 0);
        pane.add(new ImageView(image3), 0, 1);
        pane.add(new ImageView(image4), 1, 1);
        pane.setHgap(10);
        pane.setVgap(10);
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Ex14_1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}