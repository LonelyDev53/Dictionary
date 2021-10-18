package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import com.example.allClass.*;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML TextField searchBar;
    public void searchWords() {
        printResults(DictionaryManagement.dictionaryLookup(searchBar.getText()));
    }

    @FXML TextArea result;
    public void printResults(String text) {
        result.setText(text);
    }

    @FXML
    public void loadMainScene(ActionEvent event) throws IOException{
        root =  FXMLLoader.load(getClass().getResource("View.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Dictionary");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void loadAddWordScene(ActionEvent event) throws IOException{
        root =  FXMLLoader.load(getClass().getResource("addWord.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Thêm từ mới vào từ điển");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void loadEditWordScene(ActionEvent event) throws IOException{
        root =  FXMLLoader.load(getClass().getResource("editWord.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Thêm từ mới vào từ điển");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void loadDeleteWordScene(ActionEvent event) throws IOException{
        root =  FXMLLoader.load(getClass().getResource("deleteWord.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Thêm từ mới vào từ điển");
        stage.setScene(scene);
        stage.show();
    }
}