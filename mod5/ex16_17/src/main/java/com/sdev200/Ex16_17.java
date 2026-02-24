package com.sdev200;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ex16_17 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Text text = new Text("Show Colors");
        Slider sRed = new Slider(0, 1.0, 0.5);
        Slider sGreen = new Slider(0, 1.0, 0.5);
        Slider sBlue = new Slider(0, 1.0, 0.5);
        Slider sOpacity = new Slider(0, 1.0, 0.5);
        VBox sBox = new VBox(10);

        sBox.setAlignment(Pos.CENTER);
        sBox.getChildren().addAll(text, new Label("Red"), sRed, new Label("Green"), sGreen, new Label("Blue"), sBlue, new Label("Opacity"), sOpacity);
        sRed.valueProperty().addListener(ov -> updateColor(text, sRed, sGreen, sBlue, sOpacity));
        sGreen.valueProperty().addListener(ov -> updateColor(text, sRed, sGreen, sBlue, sOpacity));
        sBlue.valueProperty().addListener(ov -> updateColor(text, sRed, sGreen, sBlue, sOpacity));
        sOpacity.valueProperty().addListener(ov -> updateColor(text, sRed, sGreen, sBlue, sOpacity));

        updateColor(text, sRed, sGreen, sBlue, sOpacity);
        Scene scene = new Scene(sBox, 300, 300);
        primaryStage.setTitle("Ex16_17");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void updateColor(Text text, Slider r, Slider g, Slider b, Slider o) {
        Color color = new Color(r.getValue(), g.getValue(), b.getValue(), o.getValue());
        text.setFill(color);
    }
    public static void main(String[] args) {
        launch(args);
    }
}