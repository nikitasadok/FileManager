package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class FileManagerFrame extends JFrame {
    GeneralPanel generalPanel;
    JMenuBar menuBar;
    JMenuItem about, hotkeys, help;

    FileManagerFrame() throws IOException {
        System.out.println("loop here");
        generalPanel = new GeneralPanel();
        this.setMinimumSize(new Dimension(1000,600));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(generalPanel);

        generalPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;

        help = new JMenu("Help");
        menuBar = new JMenuBar();
        menuBar.add(help);

        about = new JMenuItem("About");
        hotkeys = new JMenuItem("Hotkeys");

        about.addActionListener(new MyMenuButtonListenerMainFrame(this));
        hotkeys.addActionListener(new MyMenuButtonListenerMainFrame(this));

        help.add(about);
        help.add(hotkeys);

        this.setJMenuBar(menuBar);
        c.gridx = 0;
        c.gridy = 0;
        generalPanel.add(generalPanel.leftPanel, c);
        c.gridx = 1;
        c.gridy = 0;
        generalPanel.add(generalPanel.rightPanel, c);
        this.setContentPane(generalPanel);
        this.setVisible(true);
        System.out.println("norm");
    }

    public static void main(String args[]) throws IOException {
        FileManagerFrame fileManagerFrame = new FileManagerFrame();

    }
}
