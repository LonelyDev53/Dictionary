package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import com.example.allClass.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller {
    @FXML private Button addButton;
    @FXML private Button editButton;
    @FXML private Button deleteButton;

    @FXML
    private void HandleButtonAction(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        if (event.getSource() == addButton) {
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("addWord.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("AddWord");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(addButton.getScene().getWindow());
            stage.showAndWait();
        } else if (event.getSource() == editButton) {
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("editWord.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("EditWord");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(editButton.getScene().getWindow());
            stage.showAndWait();
        } else if (event.getSource() == deleteButton) {
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("deleteWord.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("DeleteWord");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(deleteButton.getScene().getWindow());
            stage.showAndWait();
        }
    }

    @FXML TextField searchBar;
    @FXML TextArea result;
    public void searchWords(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String meaning;
            meaning = DictionaryManagement.dictionaryLookup(searchBar.getText());
            result.setText(meaning);
        }
    }

    @FXML TextField engWord;
    @FXML TextField vietWord;
    @FXML Button add, edit, delete, volume;
    public void confirmAddWord(ActionEvent event) throws IOException{
        DictionaryManagement.addData(engWord.getText(),vietWord.getText());
        Stage stage = (Stage) add.getScene().getWindow();
        stage.close();
    }

    public void confirmEditWord(ActionEvent event) throws IOException{
        DictionaryManagement.fixData();
        Stage stage = (Stage) edit.getScene().getWindow();
        stage.close();
    }

    public void confirmDeleteWord(ActionEvent event) throws IOException{
        DictionaryManagement.deleteData(engWord.getText());
        Stage stage = (Stage) delete.getScene().getWindow();
        stage.close();
    }

    public void confirmVolume(ActionEvent event) throws IOException{
        DictionaryManagement.speechTarget(searchBar.getText());
        volume.getScene().getWindow();
    }

    @FXML TextArea search;
    public void searchDictionaryWords(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String temp;
//           search.setText(temp) = DictionaryManagement.dictionarySearcher(searchBar.getText());
        }
    }

}