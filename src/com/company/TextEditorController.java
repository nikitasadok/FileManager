package com.company;

import javax.swing.text.BadLocationException;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TextEditorController {
    String currentlyOpened = " ";
    SpellChecker spellChecker = new SpellChecker();

    TextEditorController() throws IOException {
    }

    BufferedReader openFile(String filename) throws FileNotFoundException {
        currentlyOpened = filename;
        BufferedReader input = new BufferedReader(new FileReader(new File(filename)));
        return input;
    }

    boolean saveFile(TextEditorViewer textEditorViewer) throws IOException {
        FileWriter out = new FileWriter(textEditorViewer.textEditorController.currentlyOpened);
        textEditorViewer.write(out);
        return true;
    }

    void replaceSequence(TextEditorViewer textEditorViewer, String toReplace, String replaceWith) {
        var zar = textEditorViewer.getText();
        System.out.println(zar);
        String newText = zar.replaceAll(Pattern.quote(toReplace), replaceWith);
        System.out.println(zar);
        textEditorViewer.setText(newText);
    }

    void showMistakes(TextEditorViewer textEditorViewer) throws BadLocationException {
        String res = textEditorViewer.getText();
        String[] strings = new String[res.length()];
        strings = res.split("\\W+");
        for (String word : strings) {
            if (!textEditorViewer.textEditorController.spellChecker.checkCorrectness(word)) {
                textEditorViewer.highlightWord(word, Color.RED);
            }
        }
    }

    List<String> checkSpelling(TextEditorViewer textEditorViewer, String word) throws BadLocationException {
        List<String> suggestedWords = new ArrayList<>();
        if (!textEditorViewer.textEditorController.spellChecker.checkCorrectness(word)) {
            suggestedWords.addAll(textEditorViewer.textEditorController.spellChecker.checkExtraLetters(word));
            suggestedWords.addAll(textEditorViewer.textEditorController.spellChecker.checkMissingLetters(word));
            suggestedWords.addAll(textEditorViewer.textEditorController.spellChecker.checkCapitalization(word));
            suggestedWords.addAll(textEditorViewer.textEditorController.spellChecker.checkChangedLetters(word));
            suggestedWords.addAll(textEditorViewer.textEditorController.spellChecker.checkSwappedLetters(word));
        }
        return suggestedWords;
    }
}

//}
