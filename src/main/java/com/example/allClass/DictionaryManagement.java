package com.example.allClass;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class DictionaryManagement {
    public static Scanner scanner = new Scanner(System.in);

    public static void insertFromCommanline() {

        System.out.println("Nhập số lượng từ vựng: ");
        int amountWord = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < amountWord; i++) {
            System.out.println("Nhập từ tiếng anh: ");
            String wordEng = scanner.nextLine();
            System.out.println("Nhập từ tiếng việt: ");
            String wordVie = scanner.nextLine();

            Word element = new Word(wordEng, wordVie);
            Dictionary.getWords().add(element);
        }
    }

    public static void insertFromFile() throws IOException {
        FileReader fileReader = new FileReader("dictionaries.txt");
        try {
            BufferedReader buffer = new BufferedReader(fileReader);
            String line = " ";
            while (true) {
                line = buffer.readLine();
                if (line == null) {
                    break;
                }
                String[] txt = line.split("\t");
                String wordEng = txt[0];
                String wordVie = txt[1];
                Dictionary.getWords().add(new Word(wordEng, wordVie));
            }
        } catch (IOException checkBug) {
            checkBug.printStackTrace();
        }
    }

    public static String dictionaryLookup(String tuCanTra) {
        int i;
        for (i = 0; i < Dictionary.getWords().size(); i++) {
            if (Dictionary.getWords().get(i).getWord_target().equalsIgnoreCase(tuCanTra))
                return Dictionary.getWords().get(i).getWord_explain();
            }
        return "Từ này chưa có trong từ điển!";
    }

    public static String addData(String enText, String vieText) {
        int k = 0;
        for (int i = 0; i < Dictionary.getWords().size(); i++) {
            if (enText.equalsIgnoreCase(Dictionary.getWords().get(i).getWord_target())) {
                return "Xin lỗi! Từ này đã tồn tại ";
                k = 1;
            }
        }
        if(k == 0) {
            Word addWord = new Word(enText,vieText);
            Dictionary.getWords().add(addWord);
            return "Từ mới đã được thêm";
        }
    }

    public static String fixData(String replaceTarget, String fixWordTarget,String fixWordExplain) {
        Word fixWord = new Word(fixWordTarget,fixWordExplain);
        int k = 0;
        for (int i = 0; i < Dictionary.getWords().size(); i++) {
            if(replaceTarget.equalsIgnoreCase(Dictionary.getWords().get(i).getWord_target())) {
                Dictionary.getWords().set(i, fixWord);
                k = 1;
            }
        } if ( k == 0) {
            return "Không tìm thấy từ muốn sửa!! ";
        } else return "Từ đã được sửa  !!";
    }

    public static String deleteData(String deleteWordTarget) {
        int k = 0;
        for (int i = 0; i < Dictionary.getWords().size(); i++) {
            if (deleteWordTarget.equalsIgnoreCase(Dictionary.getWords().get(i).getWord_target())) {
                Dictionary.getWords().remove(i);
                k = 1;
            }
        }
        if (k == 0) {
            return "Không tìm thấy từ cần xóa !!";
        } else return "Đã xoá từ !!";
    }

    public static void sortData() {
        ArrayList<Word> sortData = Dictionary.getWords();
        sortData.sort(new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return o1.getWord_target().compareTo(o2.getWord_target());
            }
        });
    }

    public static void dictionarySearcher(String wordSearch) {
        int i;
        for (i = 0; i < Dictionary.getWords().size(); i++) {
            if (Dictionary.getWords().get(i).getWord_target().toLowerCase().startsWith(wordSearch))
                System.out.println(Dictionary.getWords().get(i).getWord_target());
        }
    }

    public static void dictionaryExportToFile() throws IOException {
        try {
            FileWriter fileWriter = new FileWriter("dictionaries.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < Dictionary.getWords().size(); i++) {
                bufferedWriter.write(Dictionary.getWords().get(i).getWord_target() +
                        "\t" + Dictionary.getWords().get(i).getWord_explain() + "\n");
            }
            bufferedWriter.close();
        } catch(IOException o) {
            o.printStackTrace();
        }
    }

    public static void speechTarget(String Engtext) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin");
            voice.allocate();
            voice.speak(Engtext);
            voice.deallocate();
    }

    public static void main (String[]args) throws IOException {
        insertFromFile();
//        DictionaryCommandline.showAllWords();
//        insertFromCommanline();
//        System.out.print(dictionaryLookup("comfortable"));
//        addData("HELLO", "XIN CHAO");
//        deleteData();
//        fixData();
          dictionarySearcher("he");
//        speechTarget("come");
//        dictionaryExportToFile();
//        sortData();
//        DictionaryCommandline.showAllWords();
    }
}

