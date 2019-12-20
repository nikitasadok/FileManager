package com.company;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MenuButtonListener implements ActionListener {
    TextEditorViewer textEditorViewer;
    MenuButtonListener (TextEditorViewer textEditorViewer){
        this.textEditorViewer = textEditorViewer;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(textEditorViewer.save)) {
            try {
                textEditorViewer.textEditorController.saveFile(textEditorViewer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (actionEvent.getSource().equals(textEditorViewer.replace)){
            String toReplace = JOptionPane.showInputDialog(null,"Please enter the sequence you want to replace",
                    "To replace", JOptionPane.QUESTION_MESSAGE);
            String replaceWith = JOptionPane.showInputDialog(null,"Please enter the pattern you want to replace previous one with",
                    "Replace with",JOptionPane.QUESTION_MESSAGE);
            textEditorViewer.textEditorController.replaceSequence(textEditorViewer, toReplace,replaceWith);

        }

        if (actionEvent.getSource().equals(textEditorViewer.checkSpelling)){
            try {
                textEditorViewer.textEditorController.showMistakes(textEditorViewer);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
          /*  String res = textEditorViewer.getText();
            String[] strings = new String[res.length()];
            strings = res.split(" ");
            for (String word : strings) {
                if (!textEditorViewer.textEditorController.spellChecker.checkCorrectness(word)) {
                    System.out.println(textEditorViewer.textEditorController.spellChecker.checkExtraLetters(word));
                    System.out.println(textEditorViewer.textEditorController.spellChecker.checkMissingLetters(word));
                }
            */}
        }
    }

