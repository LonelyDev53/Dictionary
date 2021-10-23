package com.example.demo;

import com.example.allClass.DictionaryManagement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DictionaryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DictionaryManagement.insertFromFile();
        Parent root =  FXMLLoader.load(getClass().getResource("View.fxml"));
        Scene mainScene = new Scene(root);
        stage.setTitle("Dictionary");
        stage.setScene(mainScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}