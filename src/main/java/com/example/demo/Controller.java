package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import com.example.allClass.*;


public class Controller {

    @FXML TextField searchBar;
    public void searchWords() {
        printResults(DictionaryManagement.dictionaryLookup(searchBar.getText()));
    }

    @FXML TextArea result;
    public void printResults(String text) {
        result.setText(text);
    }


}