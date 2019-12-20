package com.company;


import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Pattern;

public class SpellChecker {
     ArrayList<String> dictionary = new ArrayList<>();

     SpellChecker() throws IOException {
         BufferedReader reader = new BufferedReader(new FileReader("/home/nikita/dictionary.txt"));
         String line;
         while((line = reader.readLine()) != null) {
             dictionary.add(line);
         }
         reader.close();
     }

     private int getAsciiValue(String word){
         int sum = 0;
         for (int i = 0; i < word.length(); ++i){
             sum += word.charAt(i);
         }
         return sum;
     }


    boolean checkCorrectness(String word){
         System.out.println(dictionary.contains(word));
         return dictionary.contains(word);
    }

    List<String> checkMissingLetters(String wordInText){
        List<String> workingDictionary = new ArrayList<String>();
        List<String> suggestedWords = new ArrayList<>();

        for (String word : dictionary){
            if (word.length() == wordInText.length() + 1){
                workingDictionary.add(word);
            }
        }

        for (String word : workingDictionary){
            for (int i = 0; i < wordInText.length() + 1; ++i){
                if (wordInText.equals((word.substring(0, i) + word.substring(i + 1))) && !suggestedWords.contains(word)) {
                    System.out.println(word);
                    suggestedWords.add(word);
                }
            }
        }

        return suggestedWords;
    }

    List<String> checkExtraLetters(String wordInText){
        List<String> workingDictionary = new ArrayList<>();
        List<String> suggestedWords = new ArrayList<>();

        for (String word : dictionary){
            if (word.length() == wordInText.length() - 1){
                workingDictionary.add(word);
            }
        }

        for (int i = 0; i < wordInText.length(); ++i){
            if (workingDictionary.contains(wordInText.substring(0, i) + wordInText.substring(i+1)) && !suggestedWords.contains(wordInText.substring(0, i) + wordInText.substring(i+1))){
                System.out.println("Adding " + wordInText.substring(0,i) + wordInText.substring(i + 1));
                suggestedWords.add(wordInText.substring(0, i) + wordInText.substring(i + 1));
            }
        }

        return suggestedWords;
    }

    List<String> checkChangedLetters(String wordInText){
         final char A = 'A';
         final char z = 'z';

         List<String> workingDictionary = new ArrayList<>();
         List<String> suggestedWords = new ArrayList<>();
         List<Character> lettersInAlphabet = new ArrayList<>();

         for(int i = A; i < z; ++i){
             lettersInAlphabet.add((char)i);
         }

         for (String word : dictionary){
             if (word.length() == wordInText.length()){
                 workingDictionary.add(word);
             }
         }

         for (int i = 0; i < wordInText.length(); ++i){
             for (int j = 0; j < lettersInAlphabet.size(); ++j){
                 if (workingDictionary.contains(wordInText.replace( wordInText.charAt(i),lettersInAlphabet.get(j) ))){
                     suggestedWords.add(wordInText.replace(wordInText.charAt(i), lettersInAlphabet.get(j)));
                 }
             }
         }
         return suggestedWords;
    }

    List<String> checkSwappedLetters(String wordInText){
         int swapsNum = 0;

         List<String> workingDictionary = new ArrayList<>();
         List<String> suggestedWords = new ArrayList<>();

         for (String word : dictionary){
             if (getAsciiValue(word) == getAsciiValue(wordInText) && word.length() == wordInText.length()){
                 workingDictionary.add(word);
             }
         }

         for (String word : workingDictionary){
             for (int i = 0; i < wordInText.length(); ++i) {
                 if (word.charAt(i) == wordInText.charAt(i)) {
                     continue;
                 } else {
                     if ((i + 1) < wordInText.length()) {
                         if (wordInText.charAt(i) == word.charAt(i + 1) && wordInText.charAt(i + 1) == word.charAt(i)) {
                             swapsNum++;
                             continue;
                         }
                         else {
                             break;
                         }
                     }
                 }
             }
             if (swapsNum == 1) suggestedWords.add(word);
             swapsNum = 0;
         }

         return suggestedWords;
    }

    List<String> checkCapitalization(String wordInText){
         List<String> res = new ArrayList<>();
         if (dictionary.contains(wordInText.toLowerCase())){
             res.add(wordInText.toLowerCase());
        }
        return res;
    }
}
