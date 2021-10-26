package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import com.example.allClass.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;

    @FXML
    public ListView listView = new ListView();

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

    @FXML
    TextField searchBar;
    @FXML
    TextArea result;

    public void searchWords(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String meaning;
            meaning = DictionaryManagement.dictionaryLookup(searchBar.getText());
            result.setText(meaning);
        }
    }

    @FXML
    Button add, edit, delete, volume;
    @FXML
    TextField engWord, vietWord;
    @FXML
    Text addResult;

    public void confirmAddWord(ActionEvent event) throws IOException {
        addResult.setText(DictionaryManagement.addData(engWord.getText(), vietWord.getText()));
        add.getScene().getWindow();
        DictionaryManagement.dictionaryExportToFile();
    }

    @FXML
    TextField engBug,engFix, vietFix;
    @FXML
    Text editResult;

    public void confirmEditWord(ActionEvent event) throws IOException {
        editResult.setText(DictionaryManagement.fixData(engBug.getText(), engFix.getText(), vietFix.getText()));
        edit.getScene().getWindow();
        DictionaryManagement.dictionaryExportToFile();
    }

    @FXML
    TextField deleteWord;
    @FXML
    Text deleteResult;

    public void confirmDeleteWord(ActionEvent event) throws IOException {
        deleteResult.setText(DictionaryManagement.deleteData(deleteWord.getText()));
        delete.getScene().getWindow();
        DictionaryManagement.dictionaryExportToFile();
    }

    public void confirmVolume(ActionEvent event) throws IOException {
        DictionaryManagement.speechTarget(searchBar.getText());
        volume.getScene().getWindow();
    }

    public void searchDictionaryWords(KeyEvent event) {

        String temp = searchBar.getText().toString();
        List<String> search;
        search = DictionaryManagement.dictionarySearcher(temp);
        Collections.sort(search);
        ObservableList<String> input = FXCollections.observableArrayList(search);
        listView.setItems(input);
    }

    public void mouseClicked(MouseEvent e) {
        String wordClick = listView.getSelectionModel().getSelectedItem().toString();
        searchBar.setText(wordClick);
        result.setText(DictionaryManagement.dictionaryLookup(wordClick));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Collections.sort(Dictionary.listWordInListview);
        ObservableList<String> data = FXCollections.observableArrayList(Dictionary.listWordInListview);
        listView.setItems(data);
    }

}
