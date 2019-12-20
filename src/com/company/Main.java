package com.company;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        FileManagerFrame.main(null);
        /*System.out.println("Hello, Losha!");
        JFrame frame = new JFrame();
        frame.setSize(300,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setSize(150,190);
        JLabel label = new JLabel("nikita_sadok");
        panel.add(label);
        File file = new File("/home/nikita/Downloads");
        File[] files = file.listFiles();
        String[] filenames = new String[5];
        for (int i = 0; i < 5; ++i){
            filenames[i] = files[i].getName();
            System.out.println(files[i]);
        }
        JList list = new JList(filenames);
        panel.add(list);
        frame.add(panel);
        frame.setVisible(true);*/
    }
}
