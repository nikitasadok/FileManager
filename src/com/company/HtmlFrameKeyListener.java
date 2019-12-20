package com.company;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.lang.reflect.GenericSignatureFormatError;

public class HtmlFrameKeyListener implements KeyListener {
    TextEditorViewer textEditorViewer = new TextEditorViewer();
    GeneralPanel generalPanel;
    SubPanel panel;
    JList list;
    String pattern;

    public HtmlFrameKeyListener(GeneralPanel generalPanel, JList list, String pattern) throws IOException {
        this.generalPanel = generalPanel;
        this.list = list;
        this.pattern = pattern;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == keyEvent.VK_F3){
            try {
                System.out.println(list.getSelectedValue());
                textEditorViewer.viewFile(textEditorViewer.textEditorController.openFile((String)list.getSelectedValue()));
                textEditorViewer.highlightWord(pattern, Color.GREEN);
                for (String mostUsedWord : generalPanel.controller.findMostUsedWord(textEditorViewer.getText())){
                    for (String word : textEditorViewer.getText().split("\\W+")) {
                        if (word.equals(mostUsedWord)) {
                            System.out.println("need to highlight");
                            textEditorViewer.highlightWord(word, Color.BLUE);
                        }
                    }
                }
            } catch (IOException | BadLocationException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
