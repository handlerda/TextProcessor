import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class TextProcessor {

    private HashMap <String, Integer> charCountMap;
    private BufferedReader br = null;
    private int totalWords = 0;
    private int fileLength = 0;
    private String fileName = "";
    public StreamTokenizer st;

    public TextProcessor(String fileName) throws IOException {
        this.totalWords = 0;
        this.fileLength = 0;
        this.fileName = "";
        charCountMap = new HashMap<String, Integer>();
        processFile(fileName);
    }

    private void processFile(String fileName) throws FileNotFoundException, IOException {

        File file = new File(fileName);
        fileLength = (int) file.length();
        fileName = file.getName();
        br = new BufferedReader(new FileReader(file));
        String line;


        while ((line = br.readLine()) != null) {
            characterCount(line);
        }

        br.close();
    }


    public void characterCount(String inputString) {
        String[] strArray;
        StringTokenizer st = new StringTokenizer(inputString, ".!?, ");
        strArray = new String[st.countTokens()];

        int count = 0;
        while (st.hasMoreTokens()) {

            strArray[count] = st.nextToken();
            count++;
        }
        for (String c : strArray) {
            if (!c.trim().equals("")) {
                totalWords++;
                if (charCountMap.containsKey(c.toLowerCase())) {

                    charCountMap.put(c.toLowerCase(), charCountMap.get(c.toLowerCase()) + 1);
                } else {
                    charCountMap.put(c.toLowerCase(), 1);
                }
            }
        }
    }

    public int getWordCount() {
        // 3
        return totalWords;
    }

    public int getFileLength() {
        // 4
        return fileLength;
    }

    public String getFileName() {
        // 5
        return fileName;
    }

    public int countThisWord(String word) {
        // 6
        word = word.toLowerCase();
        if (charCountMap.containsKey(word)) {
            return charCountMap.get(word);
        }
        return 0;
    }

    public List<String> getWordsHavingCount(int count) {
        //7
        ArrayList<String> words = new ArrayList<String>();
        for (Map.Entry entry : charCountMap.entrySet()) {
            if ((int) entry.getValue() == count) {
                words.add((String) entry.getKey());
            }
        }
        return words;
    }

    public List<String> getWordsHavingLength (int length) {
        // 8
        ArrayList<String> words = new ArrayList<String>();
        for (String word : charCountMap.keySet()) {
            if (word.length() == length) {
                words.add(word);
            }
        }
        return words;
    }

    public String getMostFrequentWord() {
        // 9
        String word = "";
        int max = 0;
        for (Map.Entry entry : charCountMap.entrySet()) {
            if ((int) entry.getValue() > max) {
                word = (String) entry.getKey();
            }
        }
        return word;
    }

    public float getAverageWordCount() {
        // 10
        return (float) totalWords / (float) charCountMap.size();
    }

    public int getUniqueWordCount() {
        //11
        return charCountMap.size();
    }

}