import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class TextProcessorHarness {
  public static final String dataFileNames[] = {"COPY ROUTE TO FULL FILE PATH AS STRING"};

  public static void main(String args[]) throws IOException {
    for(int i = 0; i < dataFileNames.length; i++) {
      String report = getReport(dataFileNames[i]);
      if(i > 0) System.out.println("*******************************");
      System.out.println(report);
    }
  }

  static String getReport(String fileName) throws IOException {
    List<String> report = new ArrayList<>();
    String suppliedFileName = fileName;
    String message = String.format("Intended file name: %s", suppliedFileName);
    report.add(message);
    TextProcessor tp = new TextProcessor(suppliedFileName);
    String usedFileName = tp.getFileName();
    message = String.format("  Actual file name: %s", usedFileName);
    report.add(message);

    int wordCount = tp.getWordCount();
    message = String.format("Total word count: %,d", wordCount);
    report.add(message);

    int fileLength = tp.getFileLength();
    message = String.format("File length: %,d bytes", fileLength);
    report.add(message);

    String wordSample[] = { "THIS IS A SAMPLE LIST AND CAN BE UPDATED","better", "right", "street", "freedom", "job", "snow", "dog", "rain", "river", "chief" };
    for(int i = 0; i < wordSample.length; i++) {
      wordCount = tp.countThisWord(wordSample[i]);
      message = String.format("Word count for \"%s\": %d", wordSample[i], wordCount);
      report.add(message);

      String mangled = mangle(wordSample[i]);
      wordCount = tp.countThisWord(mangled);
      message = String.format("Word count for \"%s\": %d", mangled, wordCount);
      report.add(message);
    }

    wordCount = 12;
    List<String> words = tp.getWordsHavingCount(wordCount);
    message = String.format("%d word(s) having count %d:", words.size(), wordCount);
    report.add(message);

    List<String> sublist = words.size() > 10 ? words.subList(0, 10) : words;
    for(String w: sublist) {
      message = String.format("\t%s", w);
      report.add(message);
    }

    int length = 10;
    words = tp.getWordsHavingLength(length);
    message = String.format("%d word(s) having length %d:", words.size(), length);
    report.add(message);

    sublist = words.size() > 10 ? words.subList(0, 10) : words;
    for(String w: sublist) {
      message = String.format("\t%s", w);
      report.add(message);
    }

    String word = tp.getMostFrequentWord();
    message = String.format("Most frequent word: %s", word);
    report.add(message);

    wordCount = tp.countThisWord(word);
    message = String.format("Word count for \"%s\": %,d", word, wordCount);
    report.add(message);

    float average = tp.getAverageWordCount();
    message = String.format("Each word in the file appears, on average, %5.2f times.", average);
    report.add(message);

    int uniqueWordCount = tp.getUniqueWordCount();
    message = String.format("There are %,d unique words in %s.", uniqueWordCount, fileName);
    report.add(message);

    return String.join("\r\n", report);
  }

  static String mangle(String oldString) {
    String newString = "";
    for(int i = 0; i < oldString.length(); i++) {
      char c = oldString.charAt(i);
      if(i % 2 == 1) c = Character.toUpperCase(c);
      newString += c;
    }
    return newString;
  }
}