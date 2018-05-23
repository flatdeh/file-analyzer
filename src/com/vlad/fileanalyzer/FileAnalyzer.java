package com.vlad.fileanalyzer;

import java.io.*;

public class FileAnalyzer {
    public static void main(String[] args) throws IOException {
//        if (args.length != 2) {
//            throw new IllegalArgumentException("Invalid number of argument!");
//        }

        //String path = args[0];
        String path = "file";
        //String word = args[1];
        String word = "год";
        int wordCount = 0;

        File file = new File(path);
        StringBuilder sentence = new StringBuilder();

        try (InputStream inputStream = new FileInputStream(file);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {

            String text;
            while ((text = bufferedReader.readLine()) != null) {
                int startIndex = 0;
                int textEnd = text.length() - 1;

                for (int i = 0; i <= textEnd; i++) {
                    char currentChar = text.charAt(i);
                    if (currentChar == '.' || currentChar == '!' || currentChar == '?') {
                        sentence.append(text, startIndex, i + 1);

                        int countInSentence = countOfWord(sentence.toString(), word);

                        wordCount += countInSentence;

                        if (countInSentence > 0) {
                            System.out.println(sentence.toString().trim());
                        }

                        sentence.delete(0, sentence.length());
                        startIndex = i + 1;
                    } else if (i == textEnd) {
                        sentence.append(text, startIndex, i + 1);
                        sentence.append(" ");
                    }
                }
            }
        }

        System.out.println(wordCount);
    }

    private static int countOfWord(String text, String word) {
        int wordCount = 0;
        int fromIndex = 0;
        while ((fromIndex = text.indexOf(word, fromIndex)) != -1) {
            fromIndex += word.length();
            wordCount++;
        }
        return wordCount;
    }

}
