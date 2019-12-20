package com.company;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TextAreaMouseListener implements MouseListener {
    TextEditorViewer textEditorViewer;
    TextAreaMouseListener(TextEditorViewer textEditorViewer){
        this.textEditorViewer = textEditorViewer;
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (SwingUtilities.isRightMouseButton(mouseEvent)){
            JList list = new JList();
            int offset = textEditorViewer.viewToModel2D(mouseEvent.getPoint());
            int start = 0;
            try {
                start = Utilities.getWordStart(textEditorViewer, offset);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            int end = 0;
            try {
                end = Utilities.getWordEnd(textEditorViewer, offset);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            try {
                JOptionPane.showMessageDialog(null, textEditorViewer.textEditorController.checkSpelling(textEditorViewer,
                        textEditorViewer.getText(start, end - start)));
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
