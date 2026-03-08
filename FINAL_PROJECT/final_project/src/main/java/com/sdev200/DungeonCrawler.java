package com.sdev200;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DungeonCrawler extends Application {
    private Dungeon dungeon;
    private Player player;
    private VBox mapContainer;
    private Label statusLabel;
    private int bossBuff = 0;

    @Override
    public void start(Stage primaryStage) {
        // --- CHANGE SEED TO ANY (REASONABLE) DESIRED INTEGER ---
        int seed = 12345;

        dungeon = new Dungeon(10, 10, seed);
        dungeon.genWorld(seed);
        
        // Player starts at dungeon start coords
        player = new Player(100, 15, 1);
        player.setPosX(0);
        player.setPosY(0);

        // Initialize UI Components
        mapContainer = new VBox(2); 
        statusLabel = new Label("Use WASD to move | Level: 1 | HP: 100 | XP: 0");
        statusLabel.setStyle("-fx-font-size: 14px; -fx-padding: 10;");

        BorderPane root = new BorderPane();
        root.setCenter(mapContainer);
        root.setBottom(statusLabel);

        // Initial Render
        renderGrid();

        // Handle Keyboard Movement
        Scene scene = new Scene(root, 600, 600);
        scene.setOnKeyPressed(this::handleMovement);

        primaryStage.setTitle("Java Dungeon Crawler - SDEV200 Final");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleMovement(KeyEvent event) {
        int oldX = player.getPosX();
        int oldY = player.getPosY();

        switch (event.getCode()) {
            case W:
                player.setPosY(Math.max(0, oldY - 1));
                break;
            case S:
                player.setPosY(Math.min(9, oldY + 1));
                break;
            case A:
                player.setPosX(Math.max(0, oldX - 1));
                break;
            case D:
                player.setPosX(Math.min(9, oldX + 1));
                break;
            default:
                return;
        }

        Tile currentTile = dungeon.getTile(player.getPosX(), player.getPosY());

        if (!currentTile.isCleared() && !currentTile.getEnemies().isEmpty()) {
            startCombat(currentTile);
        }
        // UPDATE GRAPHICS
        updateStatus();
        renderGrid();
    }

    private void renderGrid() {
        mapContainer.getChildren().clear();
        for (int y = 0; y < 10; y++) {
            HBox row = new HBox(2);
            for (int x = 0; x < 10; x++) {
                Tile tile = dungeon.getTile(x, y);
                Button btn = new Button(String.valueOf(tile.getDifficulty()));
                btn.setPrefSize(50, 50);

                // Styling logic
                if (player.getPosX() == x && player.getPosY() == y) {
                    btn.setText("P");
                    btn.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold;");
                } else {
                    String color = getRoomColor(tile);
                    // White text for elite rooms
                    String textColor = (tile.getDifficulty() > 5) ? "white" : "black";
                    
                    btn.setStyle("-fx-background-color: " + color + "; -fx-text-fill: " + textColor + ";");
                    btn.setText(String.valueOf(tile.getDifficulty()));
                }
                
                row.getChildren().add(btn);
            }
            mapContainer.getChildren().add(row);
        }
    }

    private String getRoomColor(Tile tile) {
        if (tile.isCleared()) return "#4a525b"; // Defeated

        int diff = tile.getDifficulty();
        
        switch (diff) {
            // Normal Rooms
            case 1:  return "#d9cccc"; // Very faint, muted red-gray
            case 2:  return "#ccaeae"; 
            case 3:  return "#bf8f8f";
            case 4:  return "#b37171";
            case 5:  return "#a65252"; // Strong muted red

            // Elite Rooms
            case 6:  return "#ae4242"; // Blood red
            case 7:  return "#a12d2d";
            case 8:  return "#941b1b";
            case 9:  return "#870c0c";
            case 10: return "#750000"; // Deep blood red

            // Boss Room
            case 11: return "#ff7300"; // Chouinard Orange
            
            default: return "#a6a6a6"; // Backup gray
        }
    }

    private void updateStatus() {
        statusLabel.setText(String.format("Level: %d | HP: %d | Damage: %d | XP: %d", 
            player.getLevel(), player.getHealth(), player.getDamage(), player.getXp()));
    }

    private void startCombat(Tile tile) {
        Stage combatStage = new Stage();
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        
        int enemyCount = tile.getEnemies().size();
        String encounterText = "You have encountered " + enemyCount + " "
         + (enemyCount > 1 ? "enemies" : "enemy") + "!";
        
        Label infoLabel = new Label(encounterText);
        TextArea log = new TextArea("Beginning battle...\n");
        log.setWrapText(true);
        log.setEditable(false);
        log.setPrefHeight(300); 
        log.setPrefWidth(450);
        log.setFocusTraversable(false);

        Button attackBtn = new Button("Attack!");

        Scene combatScene = new Scene(layout, 500, 350);

        if (tile.getType().equals("The Office")) {
            log.appendText("You have attracted too much attention, and Mr. Chouinard has noticed you.\n");
        }

        combatScene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (tile.isCleared()) {
                combatStage.close();
            }
        });

        attackBtn.setOnAction(e -> {
            // Player attacks first enemy
            Enemy target = tile.getEnemies().get(0);
            String playerMsg = player.attack(target);
            log.appendText(playerMsg + "\n");

            // Health check
            if (target.getHealth() > 0) {
                log.appendText("Level " + target.getLevel() + " " + target.getType() + " has " + target.getHealth() + " health remaining!\n");
            } else {
                log.appendText("Level " + target.getLevel() + " " + target.getType() + " defeated!\n");
                player.setXp(player.getXp() + target.getRewardXp());
                tile.getEnemies().remove(0);
                
                String levelMsg = player.checkForLevelUp();
                if (!levelMsg.isEmpty()) {
                    log.appendText("------------------------------\n" + levelMsg + "------------------------------\n");
                }
            }

            // Victory check
            if (tile.getEnemies().isEmpty()) {
                tile.setCleared(true);

                // BOSS DETECTION
                if (tile.getType().equals("The Office")) {
                    log.appendText("\nMR. CHOUINARD HAS BEEN DEFEATED!");
                    log.appendText("\nYou have conquered the dungeon!");
                    attackBtn.setDisable(true);
                    return; 
                }
                String lootMsg = "\nRoom Cleared! ";
                if (!tile.getRewards().isEmpty()) {
                    Item reward = tile.getRewards().remove(0);
                    if (reward.getExtraHealth() > 0) {
                        player.setHealth(player.getHealth() + reward.getExtraHealth());
                        lootMsg += "Found " + reward.getName() + "! HP increased by " + reward.getExtraHealth();
                    } else if (reward.getExtraDamage() > 0) {
                        player.setDamage(player.getDamage() + reward.getExtraDamage());
                        lootMsg += "Found " + reward.getName() + "! Damage increased by " + reward.getExtraDamage();
                    }
                }
                
                attackBtn.setDisable(true);
                log.appendText(lootMsg + "\nPress any key to return to the map.");
                layout.requestFocus();
            } else {

                // Enemy attacks back
                for (Enemy enemy : tile.getEnemies()) {
                    String enemyMsg = enemy.attack(player);
                    log.appendText(enemyMsg);

                    if (enemy.getType().equals("Mr. Chouinard")) {
                        bossBuff += 10; 
                        enemy.setDamage(enemy.getDamage() + bossBuff);
                        log.appendText("Mr. Chouinard's patience runs thinner. His damage has increased by " + bossBuff + "!\n");
                    }
                }

                // NEW GAME OVER CHECK
                if (player.getHealth() <= 0) {
                    player.setHealth(0); // Cap at 0 for UI
                    log.appendText("\nGAME OVER! You have been defeated.");
                    attackBtn.setDisable(true);
                }
                log.appendText("\n");
            }
            updateStatus(); // Update main UI stats
        });

        layout.getChildren().addAll(infoLabel, log, attackBtn);
        combatStage.setScene(combatScene);
        attackBtn.requestFocus();
        combatStage.showAndWait(); 
        renderGrid();
    }

    public static void main(String[] args) {
        launch(args);
    }
}