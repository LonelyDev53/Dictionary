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

    public static void addData(String enText, String vieText) {
        int k = 0;
        for (int i = 0; i < Dictionary.getWords().size(); i++) {
            if (enText.equalsIgnoreCase(Dictionary.getWords().get(i).getWord_target())) {
                System.out.println("Xin lỗi! Từ này đã tồn tại ");
                k = 1;
            }
        }
        if(k == 0) {
            Word addWord = new Word(enText,vieText);
            Dictionary.getWords().add(addWord);
        }
    }

    public static void fixData() {
        System.out.println("Nhập từ tiếng Anh sai: ");
        String replaceTarget = scanner.nextLine();
        System.out.println("Nhập từ tiếng Việt sai: ");
        String replaceExplain = scanner.nextLine();
        System.out.println("Nhập từ tiếng Anh muốn sửa: ");
        String fixwordtarget = scanner.nextLine();
        System.out.println("Nhập từ tiếng Việt muốn sửa: ");
        String fixwordexplain = scanner.nextLine();
        Word fixword = new Word(fixwordtarget,fixwordexplain);
        int k = 0;
        for (int i = 0; i < Dictionary.getWords().size(); i++) {
            if(replaceTarget.equalsIgnoreCase(Dictionary.getWords().get(i).getWord_target()) ||
                    replaceExplain.equalsIgnoreCase(Dictionary.getWords().get(i).getWord_target())) {
                Dictionary.getWords().set(i, fixword);
                k = 1;
            }
        } if ( k == 0) {
            System.out.println("Không tìm thấy từ muốn sửa!! ");
        }
        System.out.println("Từ điển mới là:");
        DictionaryCommandline.showAllWords();
    }

    public static void deleteData() {
        System.out.println("Nhập từ muốn xóa : ");
        String deleteWordTarget = scanner.nextLine();
        int k = 0;
        for (int i = 0; i < Dictionary.getWords().size(); i++) {
            if (deleteWordTarget.equalsIgnoreCase(Dictionary.getWords().get(i).getWord_target())) {
                Dictionary.getWords().remove(i);
                k = 1;
            }
        }
        if (k == 0) {
            System.out.print("Không tìm thấy từ cần xóa !!");
        }
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

    public static void dictionarySearcher() {
        System.out.println("Nhập kí tự cần tra: ");
        String wordSearch = scanner.nextLine();
        for (int i = 0; i < Dictionary.getWords().size(); i++) {
            if (Dictionary.getWords().get(i).getWord_target().contains(wordSearch)) {
                System.out.println(Dictionary.getWords().get(i).getWord_target());
            }
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
        int k = 0;
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin");
        for(int i = 0; i < Dictionary.getWords().size(); i++) {
            if(!Engtext.equalsIgnoreCase(Dictionary.getWords().get(i).getWord_target()))
                voice.allocate();
                voice.speak("Can't find Word");
                voice.deallocate();
                k = 1;
        }
        if(k == 0) {
            voice.allocate();
            voice.speak(Engtext);
            voice.deallocate();
        }
    }

    public static void main (String[]args) throws IOException {
//        insertFromFile();
//        DictionaryCommandline.showAllWords();
//        insertFromCommanline();
//        System.out.print(dictionaryLookup("hello"));
//        addData("HELLO", "XIN CHAO");
//        deleteData();
//        fixData();
//        dictionarySearcher();
        speechTarget("come");
//        dictionaryExportToFile();
//        sortData();
//        DictionaryCommandline.showAllWords();
    }
}

