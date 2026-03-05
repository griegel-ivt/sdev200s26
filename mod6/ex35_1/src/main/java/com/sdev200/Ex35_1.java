package com.sdev200;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ex35_1 extends Application {
    private Label lStatus = new Label("Prepared to insert");
    private Button connectButton = new Button("Connect to Database");
    private TextArea timeResults = new TextArea();
    private Button batchButton = new Button("Batch Update");
    private Button nonBatchButton = new Button("Non-Batch Update");

    private Connection connection;
    private PreparedStatement statement;

    private void connectDB() {
        Stage dialog = new Stage();
        dialog.setTitle("Connect to DB");

        Label lConnectionStatus = new Label("Prepared to connect");
        TextField tfDriver = new TextField("com.mysql.cj.jdbc.Driver");
        TextField tfURL = new TextField("jdbc:mysql://localhost/randomnumbers?rewriteBatchedStatements=true");
        TextField tfUser = new TextField("root");
        PasswordField tfPass = new PasswordField();
        Button bConnect = new Button("Connect to DB");
        Button bClose = new Button("Close Dialog");

        bConnect.setOnAction(e -> {
            try {
                Class.forName(tfDriver.getText().trim());
                connection = DriverManager.getConnection(tfURL.getText().trim(), tfUser.getText().trim(), tfPass.getText().trim());
                lConnectionStatus.setText("Connected to " + tfURL.getText());
            } catch (Exception ex) {
                lConnectionStatus.setText("Connection failed: " + ex.getMessage());
            }
        });
        bClose.setOnAction(e -> {
            dialog.close();
        });

        VBox dialogBox = new VBox(5);
        dialogBox.setPadding(new javafx.geometry.Insets(2));
        
        dialogBox.getChildren().add(lConnectionStatus);

        HBox driveBox = new HBox(5);
        tfDriver.setPrefColumnCount(30);
        driveBox.getChildren().addAll(new Label("JDBC Driver"), tfDriver);
        dialogBox.getChildren().add(driveBox);

        HBox urlBox = new HBox(5);
        tfURL.setPrefColumnCount(30);
        urlBox.getChildren().addAll(new Label("Database URL"), tfURL);
        dialogBox.getChildren().add(urlBox);

        HBox userBox = new HBox(5);
        tfUser.setPrefColumnCount(30);
        userBox.getChildren().addAll(new Label("Username"), tfUser);
        dialogBox.getChildren().add(userBox);

        HBox passBox = new HBox(5);
        tfPass.setPrefColumnCount(30);
        passBox.getChildren().addAll(new Label("Password"), tfPass);
        dialogBox.getChildren().add(passBox);

        HBox connectBox = new HBox(5);
        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        connectBox.getChildren().addAll(spacer, bConnect);
        dialogBox.getChildren().add(connectBox);

        HBox closeBox = new HBox(5);
        closeBox.setAlignment(Pos.CENTER);
        closeBox.getChildren().add(bClose);
        dialogBox.getChildren().add(closeBox);

        Scene dialogScene = new Scene(dialogBox, 500, 300);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private void batchUpdate() {
        long startTime = System.currentTimeMillis();
        try {
            String sql = "INSERT INTO Temp (num1, num2, num3) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < 1000; i++) {
                statement.setDouble(1, Math.random());
                statement.setDouble(2, Math.random());
                statement.setDouble(3, Math.random());
                statement.addBatch();
            }
            statement.executeBatch();
            long endTime = System.currentTimeMillis();
            timeResults.appendText("Batch update completed\nElapsed Time: " + (endTime - startTime) + "ms\n");
            lStatus.setText("Batch update succeeded");
        } catch (SQLException ex) {
            lStatus.setText("Batch update failed: " + ex.getMessage());
        }
    }

    private void standardUpdate() {
        long startTime = System.currentTimeMillis();
        try {
            String sql = "INSERT INTO Temp (num1, num2, num3) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < 1000; i++) {
                statement.setDouble(1, Math.random());
                statement.setDouble(2, Math.random());
                statement.setDouble(3, Math.random());
                statement.executeUpdate();
            }
            long endTime = System.currentTimeMillis();
            timeResults.appendText("Non-Batch update completed\nElapsed Time: " + (endTime - startTime) + "ms\n");
            lStatus.setText("Non-Batch update succeeded");
        } catch (SQLException ex) {
            lStatus.setText("Non-Batch update failed: " + ex.getMessage());
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        VBox mainBox = new VBox(5);

        HBox firstRow = new HBox(5);
        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        firstRow.setPadding(new javafx.geometry.Insets(2));
        firstRow.getChildren().addAll(lStatus, spacer, connectButton);
        firstRow.setAlignment(Pos.CENTER_LEFT);
        mainBox.getChildren().add(firstRow);

        timeResults.setPrefColumnCount(40);
        timeResults.setPrefRowCount(6);
        mainBox.getChildren().add(timeResults);

        HBox buttonRow = new HBox(5);
        buttonRow.getChildren().addAll(batchButton, nonBatchButton);
        buttonRow.setAlignment(Pos.CENTER);
        mainBox.getChildren().add(buttonRow);

        connectButton.setOnAction(e -> connectDB());
        batchButton.setOnAction(e -> batchUpdate());
        nonBatchButton.setOnAction(e -> standardUpdate());

        Scene scene = new Scene(mainBox, 600, 400);
        stage.setTitle("Batch vs Non-Batch Updates");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}