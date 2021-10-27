package com.example.allClass;

import java.io.*;
import java.util.*;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DictionaryManagement extends Dictionary {


    private static final String CLIENT_ID = "FREE_TRIAL_ACCOUNT";
    private static final String CLIENT_SECRET = "PUBLIC_SECRET";
    private static final String ENDPOINT = "http://api.whatsmate.net/v1/translation/translate";
    public static Scanner scanner = new Scanner(System.in);

    //Nhập dữ liệu bằng commanline
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

    //Lấy dữ liệu từ điển từ file
    public static void insertFromFile() throws IOException {
        FileReader fileReader = new FileReader("dictionaries.txt");
        try {
            BufferedReader buffer = new BufferedReader(fileReader);
            String line = " ";

            // Doc tung dong cho den ki tu null
            while (true) {
                line = buffer.readLine();
                if (line == null) {
                    break;
                }
                // Doc txt roi chia thanh 2 list ngan cach boi dau tab
                String[] txt = line.split("\t");
                String wordEng = txt[0];
                String wordVie = txt[1];
                Dictionary.getWords().add(new Word(wordEng, wordVie));
            }

            for (int i = 0; i < getWords().size(); i++) {
                listWordInListview.add(getWords().get(i).getWord_target());
            }
        } catch (IOException checkBug) {
            checkBug.printStackTrace();
        }
    }

    // So sanh tu duoc nhap voi tu tieng Anh trong List
    public static String dictionaryLookup(String tuCanTra) {
        int i;
        for (i = 0; i < Dictionary.getWords().size(); i++) {
            if (Dictionary.getWords().get(i).getWord_target().equalsIgnoreCase(tuCanTra))
                return Dictionary.getWords().get(i).getWord_explain();
        }
        return "Từ này chưa có trong từ điển!";
    }


    // Them tu vao list
    public static String addData(String enText, String vieText) {
        int k = 0;
        for (int i = 0; i < Dictionary.getWords().size(); i++) {
            if (enText.equalsIgnoreCase(Dictionary.getWords().get(i).getWord_target())) {
                k = 1;
            }
        }
        if (k == 0) {
            Word addWord = new Word(enText, vieText);
            Dictionary.getWords().add(addWord);
            return "Từ mới đã được thêm !!";
        }
        else return "Xin lỗi! Từ này đã tồn tại !!";
    }

    // Nhap tu tieng Anh can sua va nghia tieng Anh viet sua
    public static String fixData(String replaceTarget, String fixWordTarget, String fixWordExplain) {
        Word fixWord = new Word(fixWordTarget, fixWordExplain);
        int k = 0;
        for (int i = 0; i < Dictionary.getWords().size(); i++) {
            if (replaceTarget.equalsIgnoreCase(Dictionary.getWords().get(i).getWord_target())) {
                Dictionary.getWords().set(i, fixWord);
                k = 1;
            }
        }
        if (k == 0) {
            return "Không tìm thấy từ muốn sửa !! ";
        } else return "Từ đã được sửa  !!";
    }

    // Nhap tu can xoa de so sanh voi tu co trong list
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

    public static List<String> dictionarySearcher(String wordSearch) {
        int i;
        List<String> search = new ArrayList<>();
        for (i = 0; i < Dictionary.getWords().size(); i++) {
            // So sánh từ gần giống từ vị trí bắt đầu
            if (Dictionary.getWords().get(i).getWord_target().toLowerCase().startsWith(wordSearch)) {
                System.out.println(Dictionary.getWords().get(i).getWord_target());
                search.add(Dictionary.getWords().get(i).getWord_target());
            }

        }
        return search;
    }

    public static void dictionaryExportToFile() throws IOException {
        try {
            FileWriter fileWriter = new FileWriter("dictionaries.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            // Ghi dữ liệu từ List vào file
            for (int i = 0; i < Dictionary.getWords().size(); i++) {
                bufferedWriter.write(Dictionary.getWords().get(i).getWord_target() +
                        "\t" + Dictionary.getWords().get(i).getWord_explain() + "\n");
            }
            bufferedWriter.close();
        } catch (IOException o) {
            o.printStackTrace();
        }
    }

    // Xây dựng hàm phát âm với người đọc là Kevin
    public static void speechTarget(String Engtext) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin");
        voice.allocate();
        voice.speak(Engtext);
        voice.deallocate();
    }

    
    // Xây dựng hàm trans API
    public static String translateAPI(String text) throws Exception {
        // TODO: Should have used a 3rd party library to make a JSON string from an object
        String jsonPayload = new StringBuilder()
                .append("{")
                .append("\"fromLang\":\"")
                .append("en")
                .append("\",")
                .append("\"toLang\":\"")
                .append("vi")
                .append("\",")
                .append("\"text\":\"")
                .append(text)
                .append("\"")
                .append("}")
                .toString();

        URL url = new URL(ENDPOINT);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("X-WM-CLIENT-ID", CLIENT_ID);
        conn.setRequestProperty("X-WM-CLIENT-SECRET", CLIENT_SECRET);
        conn.setRequestProperty("Content-Type", "application/json");

        OutputStream os = conn.getOutputStream();
        os.write(jsonPayload.getBytes());
        os.flush();
        os.close();

        int statusCode = conn.getResponseCode();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(
                (statusCode == 200) ? conn.getInputStream() : conn.getErrorStream()
        ));
        String output;
        while ((output = br.readLine()) != null) {
            System.out.println(output);
            return output;
        }
        conn.disconnect();
        return "";
    }


    public static void main(String[] args) throws IOException {
//        insertFromFile();
//        DictionaryCommandline.showAllWords();
        insertFromCommanline();
//        System.out.print(dictionaryLookup("comfortable"));
//        System.out.println(addData("HELLoO", "XIN CHAO"));
//        deleteData();
//        fixData();
//        dictionarySearcher("he");
//        speechTarget("come");
//        dictionaryExportToFile();
        sortData();
        DictionaryCommandline.showAllWords();
    }
}

