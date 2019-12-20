package com.company;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;

public class TextEditorViewer extends JTextArea{
    JScrollPane editorPane;
    JFrame editorFrame;
    JMenuBar menuBar;
    TextEditorController textEditorController;
    JMenuItem save, replace, checkSpelling;

    TextEditorViewer() throws IOException {
        TextEditorViewer textEditorViewer = this;
        editorFrame = new JFrame();
        editorPane = new JScrollPane(this);
        menuBar = new JMenuBar();

        MyDocumentListener myDocumentListener = new MyDocumentListener();
        this.getDocument().addDocumentListener(myDocumentListener);

        editorFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        editorFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                 int res = JOptionPane.showConfirmDialog(null, "Do you want to save file " +
                            "before exiting?");
                    if (res == 0) {
                        try {
                            textEditorController.saveFile(textEditorViewer);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                editorFrame.dispose();


            }
        });

        textEditorController = new TextEditorController();

        editorFrame.setJMenuBar(menuBar);

        JMenu file = new JMenu("File");
        menuBar.add(file);

        save = new JMenuItem("Save");
        replace = new JMenuItem("Replace");
        checkSpelling = new JMenuItem("Check Spelling");

        save.addActionListener(new MenuButtonListener(this));
        replace.addActionListener(new MenuButtonListener(this));
        checkSpelling.addActionListener(new MenuButtonListener(this));

        this.addMouseListener(new TextAreaMouseListener(this));
        file.add(save);
        file.add(replace);
        file.add(checkSpelling);
    }

    void viewFile(BufferedReader input) throws IOException {
        editorFrame.add(editorPane);
        editorFrame.setVisible(true);
        editorFrame.setMinimumSize(new Dimension(300,300));
        editorFrame.setLocation(500,500);


        if (input != null) {
            this.read(input, null);
        }
    }

    void highlightWord(String word, Color color) throws BadLocationException {
        var redPainter = new DefaultHighlighter.DefaultHighlightPainter(color);

        Highlighter highlighter = this.getHighlighter();
        int lastIndex = 0;
        int from = 0;
        while ((from = getText().indexOf(word, lastIndex)) != -1) {
            int to = from + word.length();
            highlighter.addHighlight(from, to, redPainter);
            lastIndex = to + 1;
        }

    }
    }


