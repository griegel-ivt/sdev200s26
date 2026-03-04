package com.sdev200;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ex34_1 extends Application {
    private TextField tfId = new TextField();
    private TextField tfLastName = new TextField();
    private TextField tfFirstName = new TextField();
    private TextField tfMI = new TextField();
    private TextField tfAddress = new TextField();
    private TextField tfCity = new TextField();
    private TextField tfState = new TextField();
    private TextField tfPhone = new TextField();
    private TextField tfEmail = new TextField();

    private Button bView = new Button("View");
    private Button bInsert = new Button("Insert");
    private Button bUpdate = new Button("Update");
    private Button bClear = new Button("Clear");

    private Label lStatus = new Label("Record not found!");

    private void viewRecord() {
        String id = tfId.getText();
        String queryString = "SELECT * FROM Staff WHERE id = ?";

        try {
            statement = connection.prepareStatement(queryString);
            statement.setString(1, id);
            ResultSet rset = statement.executeQuery();

            if (rset.next()) {
                tfLastName.setText(rset.getString(2));
                tfFirstName.setText(rset.getString(3));
                tfMI.setText(rset.getString(4));
                tfAddress.setText(rset.getString(5));
                tfCity.setText(rset.getString(6));
                tfState.setText(rset.getString(7));
                tfPhone.setText(rset.getString(8));
                tfEmail.setText(rset.getString(9));
                lStatus.setText("Record found");
            } else {
                lStatus.setText("Record not found");
            }
        } catch (SQLException ex) {
            lStatus.setText("View failed: " + ex.getMessage());
        }
    }
    
    private void insertRecord() {
        String sql = "INSERT INTO Staff (id, lastName, firstName, mi, address, city, state, telephone, email) "
         + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            statement = connection.prepareStatement(sql);

            statement.setString(1, tfId.getText());
            statement.setString(2, tfLastName.getText());
            statement.setString(3, tfFirstName.getText());
            statement.setString(4, tfMI.getText());
            statement.setString(5, tfAddress.getText());
            statement.setString(6, tfCity.getText());
            statement.setString(7, tfState.getText());
            statement.setString(8, tfPhone.getText());
            statement.setString(9, tfEmail.getText());

            statement.executeUpdate();
            lStatus.setText("Record inserted successfully!");
        } catch (SQLException ex) {
            lStatus.setText("Insert failed: " + ex.getMessage());
        }
    }
    
    private void updateRecord() {
        String sql = "UPDATE Staff SET lastName = ?, firstName = ?, mi = ?, address = ?, city = ?, state = ?, telephone = ?, email = ? "
         + "WHERE id = ?";
        try {
            statement = connection.prepareStatement(sql);

            statement.setString(1, tfLastName.getText());
            statement.setString(2, tfFirstName.getText());
            statement.setString(3, tfMI.getText());
            statement.setString(4, tfAddress.getText());
            statement.setString(5, tfCity.getText());
            statement.setString(6, tfState.getText());
            statement.setString(7, tfPhone.getText());
            statement.setString(8, tfEmail.getText());
            statement.setString(9, tfId.getText());

            int count = statement.executeUpdate();

            if (count > 0) {
                lStatus.setText("Record updated successfully!");
            } else {
                lStatus.setText("Update failed: ID not found.");
            }
        } catch (SQLException ex) {
            lStatus.setText("Update failed: " + ex.getMessage());
        }
    }
    
    private void clearFields() {
        tfId.clear();
        tfLastName.clear();
        tfFirstName.clear();
        tfMI.clear();
        tfAddress.clear();
        tfCity.clear();
        tfState.clear();
        tfPhone.clear();
        tfEmail.clear();
    }

    private Connection connection;
    private PreparedStatement statement;
    private void connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
            lStatus.setText("Database connection succeeded");
        } catch (Exception ex) {
            lStatus.setText("Database connection failed: " + ex.getMessage());
        }
    }

    @Override
    public void start(Stage stage) {
        connectDB();
        VBox mainBox = new VBox(5);

        // Status
        mainBox.getChildren().add(lStatus);

        // Id Row
        HBox idRow = new HBox(5);
        tfId.setPrefColumnCount(12);
        idRow.getChildren().addAll(new Label("ID"), tfId);
        mainBox.getChildren().add(idRow);

        // Name Row
        HBox nameRow = new HBox(5);
        tfLastName.setPrefColumnCount(10);
        tfFirstName.setPrefColumnCount(10);
        tfMI.setPrefColumnCount(2);
        nameRow.getChildren().addAll(new Label("Last Name"), tfLastName, new Label("First Name"), tfFirstName, new Label("MI"), tfMI);
        mainBox.getChildren().add(nameRow);

        // Address Row
        HBox addressRow = new HBox(5);
        tfAddress.setPrefColumnCount(15);
        addressRow.getChildren().addAll(new Label("Address"), tfAddress);
        mainBox.getChildren().add(addressRow);

        // City/State Row
        HBox cityStateRow = new HBox(5);
        tfCity.setPrefColumnCount(12);
        tfState.setPrefColumnCount(10);
        cityStateRow.getChildren().addAll(new Label("City"), tfCity, new Label("State"), tfState);
        mainBox.getChildren().add(cityStateRow);

        // Phone Row
        HBox phoneRow = new HBox(5);
        tfPhone.setPrefColumnCount(12);
        phoneRow.getChildren().addAll(new Label("Telephone"), tfPhone);
        mainBox.getChildren().add(phoneRow);

        // Email Row
        HBox emailRow = new HBox(5);
        tfEmail.setPrefColumnCount(25);
        emailRow.getChildren().addAll(new Label("Email"), tfEmail);
        mainBox.getChildren().add(emailRow);

        // Button Row
        HBox buttonRow = new HBox(5);
        buttonRow.setAlignment(Pos.CENTER);
        buttonRow.getChildren().addAll(bView, bInsert, bUpdate, bClear);
        mainBox.getChildren().add(buttonRow);

        // Button Events
        bView.setOnAction(e -> viewRecord());
        bInsert.setOnAction(e -> insertRecord());
        bUpdate.setOnAction(e -> updateRecord());
        bClear.setOnAction(e -> clearFields());

        // Initialize Scene
        Scene scene = new Scene(mainBox, 600, 400);
        stage.setTitle("Staff");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
/* Local Database Link
    http://localhost/phpmyadmin/index.php?route=/sql&db=addressbook&table=staff&pos=0
*/