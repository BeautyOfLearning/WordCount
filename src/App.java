import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class App {

    public static void writeRandomWordsToFile(int numOfWords, String fileName) {
        String[] vocab = { "hello", "world", "java" };
        String word;
        Random random = new Random();
        try (
                FileWriter fileWriter = new FileWriter(fileName);
                BufferedWriter writer = new BufferedWriter(fileWriter)) {
            for (int i = 0; i < numOfWords; i++) {
                word = vocab[random.nextInt(vocab.length)];
                writer.write(word + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Test: reading file
        try (
                FileReader fileReader = new FileReader(fileName);
                BufferedReader reader = new BufferedReader(fileReader);) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                System.out.println(++i + ": " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // writeRandomWordsToFile(100, "words.txt");
        String inputFileName = "words.txt";
        String outputFileName = "word_count.txt";
        if (args.length > 1){
            inputFileName = args[0];
            outputFileName = args[1];
        } else if (args.length > 0) {
            inputFileName = args[0];
        }

        Map<String, Integer> wordCount = new TreeMap<>();
        try (
                FileReader fileReader = new FileReader(inputFileName);
                BufferedReader reader = new BufferedReader(fileReader);
                FileWriter fileWriter = new FileWriter(outputFileName);
                BufferedWriter writer = new BufferedWriter(fileWriter);) {

            String line, word;
            while ((line = reader.readLine()) != null) {
                word = line.trim().toLowerCase();
                if (wordCount.containsKey(word)) {
                    wordCount.put(word, wordCount.get(word) + 1);
                } else {
                    wordCount.put(word, 1);
                }
            }

            for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
        }
    }
}
