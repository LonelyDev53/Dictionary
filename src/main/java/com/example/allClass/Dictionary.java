package com.example.allClass;

import java.util.ArrayList;

public class Dictionary {
    private static ArrayList<Word> Words = new ArrayList<Word>();

    public static ArrayList<Word> getWords() {
        return Words;
    }

    public static void setWords(ArrayList<Word> words) {
        Words = words;
    }
}

