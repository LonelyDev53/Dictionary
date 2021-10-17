package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import com.example.allClass.*;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.*;

public class Controller {

    @FXML TextField searchBar;
    public void searchWords() {
        System.out.println(searchBar.getText());
        DictionaryManagement.dictionaryLookup(searchBar.getText());

    }

    @FXML TextArea result;
    System.setOut(new PrintStream(new OutputStream() {

        @Override
        public void write(int b) throws IOException {
            result.appendText("" + ((char)b));
        }

        @Override
        public void write(byte[] b) throws IOException {
            result.appendText(new String(b));
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            result.appendText(new String(b, off, len));
        }
    }));

}