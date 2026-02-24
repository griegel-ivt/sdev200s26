package com.sdev200;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Ex15_7 extends Application {
    @Override
    public void start(Stage primaryStage) {
        StackPane pane = new StackPane();
        Circle circle = new Circle(50);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        pane.getChildren().add(circle);

        circle.setOnMousePressed( e-> {
            circle.setFill(Color.BLACK);
        });
        circle.setOnMouseReleased( e-> {
            circle.setFill(Color.WHITE);
        });

        Scene scene = new Scene(pane, 300, 300);
        primaryStage.setTitle("Ex15_7");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}