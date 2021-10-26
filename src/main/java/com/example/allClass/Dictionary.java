package com.example.allClass;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {

    protected static ArrayList<Word> Words = new ArrayList<Word>();

    public static ArrayList<Word> getWords() {
        return Words;
    }

    public static void setWords(ArrayList<Word> words) {
        Words = words;
    }

    public static List<String> listWordInListview = new ArrayList<>();
}

