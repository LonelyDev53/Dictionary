package com.example.allClass;

import java.io.IOException;
import java.util.ArrayList;

public class DictionaryCommandline {

    public static void showAllWords() {
        ArrayList<Word> hienThi = Dictionary.getWords();
        System.out.printf("%-20s |%-20s |%-10s\n","No","English","Vietnamese");
        for(int i = 0; i < hienThi.size(); i++) {
            System.out.printf("%-20s |%-20s |%-10s\n",i+1,hienThi.get(i).getWord_target(),hienThi.get(i).getWord_explain());
        }
    }

    public static void dictionaryBasic() {
        DictionaryManagement.insertFromCommanline();
        showAllWords();
    }

    public static void dictionaryAdvanced() throws IOException {
        DictionaryManagement.insertFromFile();
        showAllWords();
//        DictionaryManagement.dictionaryLookup();
    }
}

