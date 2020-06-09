package com.raghib.connectfour;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    private  Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        GridPane gridPane = loader.load();
        controller=loader.getController();
        primaryStage.setTitle("Connect Four");
        primaryStage.setScene(new Scene(gridPane));
         controller.createPlayground();
        Pane menuPane = (Pane) gridPane.getChildren().get(0);
        MenuBar menuBar = creatMenu();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        menuPane.getChildren().addAll(menuBar);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private MenuBar creatMenu() {

        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();

        //file menu
        Menu fileMenu = new Menu("File");
        MenuItem newGame = new MenuItem("New game");
        newGame.setOnAction(event -> resetGame());
        MenuItem resetGame = new MenuItem("Reset game");
        resetGame.setOnAction(event -> resetGame());

        MenuItem exitGame = new MenuItem("Exit game");
        exitGame.setOnAction(event -> exitGame());

        fileMenu.getItems().addAll(newGame, resetGame, separatorMenuItem, exitGame);

        //help menu
        Menu helpMenu = new Menu("Help");
        MenuItem aboutGame = new Menu("About Game");
        aboutGame.setOnAction(event -> {
           aboutConnectFour();
        });
        MenuItem aboutDeveloper = new MenuItem("About Developer");
          aboutDeveloper.setOnAction(event -> aboutMe());
        helpMenu.getItems().addAll(aboutGame,aboutDeveloper);



        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, helpMenu);
        return menuBar;
    }

    private void exitGame() {
        Platform.exit();
        System.exit(0);
    }

    private void resetGame() {
        //to do
    }


    private void aboutMe() { Alert aboutMeAlert = new Alert(Alert.AlertType.INFORMATION);
        aboutMeAlert.setTitle("About Me");
        aboutMeAlert.setHeaderText("Mohd Raghib");
        aboutMeAlert.setContentText("Hi There,\n I am Mohd Raghib. I am doing my Engineering from SLIET LONGOWAL.I love  coding.");

        aboutMeAlert.show();
    }

    private void aboutConnectFour() {
        Alert aboutGameAlert = new Alert(Alert.AlertType.INFORMATION);
        aboutGameAlert.setTitle("About Game");
        aboutGameAlert.setHeaderText("Connect Four");
        aboutGameAlert.setContentText("Connect Four is a two-player connection game in which the players first choose a color and then take turns" +
                " dropping colored discs from the top into a seven-column, six-row vertically suspended grid. The pieces fall straight down, occupying the next" +
                " available space within the column. The objective of the game is to be the first to form a horizontal, vertical, or diagonal line of four of one's own discs. Connect Four is a solved game." +
                " The first player can always win by playing the right moves.");

        aboutGameAlert.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
