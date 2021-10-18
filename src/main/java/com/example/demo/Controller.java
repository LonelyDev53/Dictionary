package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import com.example.allClass.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {

    private Stage mainStage, secondStage;
    private Scene mainScene, secondScene;
    private Parent root;

    @FXML
    public void loadMainScene(ActionEvent event) throws IOException{
        root =  FXMLLoader.load(getClass().getResource("View.fxml"));
        mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainScene = new Scene(root);
        mainStage.setTitle("Dictionary");
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    @FXML
    public void loadAddWordScene(ActionEvent event) throws IOException{
        root =  FXMLLoader.load(getClass().getResource("addWord.fxml"));
        secondStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        secondScene = new Scene(root);
        secondStage.setTitle("Thêm từ mới vào từ điển");
        secondStage.setScene(secondScene);
        secondStage.initModality(Modality.APPLICATION_MODAL);
        secondStage.showAndWait();
    }

    @FXML
    public void loadEditWordScene(ActionEvent event) throws IOException{
        root =  FXMLLoader.load(getClass().getResource("editWord.fxml"));
        secondStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        secondScene = new Scene(root);
        secondStage.setTitle("Sửa từ");
        secondStage.setScene(secondScene);
        secondStage.show();
    }

    @FXML
    public void loadDeleteWordScene(ActionEvent event) throws IOException{
        root =  FXMLLoader.load(getClass().getResource("deleteWord.fxml"));
        secondStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        secondScene = new Scene(root);
        secondStage.setTitle("Xóa từ");
        secondStage.setScene(secondScene);
        secondStage.show();
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
    public void confirmAddWord(ActionEvent event) throws IOException{
        DictionaryManagement.adddata(engWord.getText(),vietWord.getText());
        loadMainScene(event);
    }

    @FXML
    public void confirmEditWord(ActionEvent event) throws IOException{
        DictionaryManagement.fixdata();
    }

}