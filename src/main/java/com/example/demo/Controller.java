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
import javafx.scene.text.Text;
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
    @FXML Button add, edit, delete, volume;
    @FXML TextField engWord, vietWord;
    @FXML Text addResult;
    public void confirmAddWord(ActionEvent event) throws IOException{
        addResult.setText(DictionaryManagement.addData(engWord.getText(),vietWord.getText()));
        Stage stage = (Stage) add.getScene().getWindow();
        stage.close();
    }

    @FXML TextField engFix, vietFix;
    @FXML Text editResult;
    public void confirmEditWord(ActionEvent event) throws IOException{
        editResult.setText(DictionaryManagement.fixData(engFix.getText(),engFix.getText(),vietFix.getText()));
        Stage stage = (Stage) edit.getScene().getWindow();
    }

    @FXML TextField deleteWord;
    @FXML Text deleteResult;
    public void confirmDeleteWord(ActionEvent event) throws IOException{
        deleteResult.setText(DictionaryManagement.deleteData(deleteWord.getText()));
        Stage stage = (Stage) delete.getScene().getWindow();
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