package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MyMenuButtonListenerMainFrame implements ActionListener {
    FileManagerFrame fileManagerFrame;
    MyMenuButtonListenerMainFrame(FileManagerFrame fileManagerFrame) throws IOException {
        this.fileManagerFrame = fileManagerFrame;
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == fileManagerFrame.about){
            JOptionPane.showMessageDialog(null, "Hello and welcome! This is the file " +
                    "manager made by Nikita Sadok.\nThis simple program can help you to manipulate files and " +
                    "directories, help you to create, move, rename and delete those.\nText editor is also built + " +
                    "inside this app. This can be distributed under GPL.");
        }
        if (actionEvent.getSource() == fileManagerFrame.hotkeys){
            JOptionPane.showMessageDialog(null, "F1 - copy file/directory\n" + "F2 - find html files which contain following test\n" + "F3 - open text editor\n" +
                    "F4 - move file/directory\n" + "F5 - move group of files which match particular pattern\n" + "F6 - rename file/directory\n" +
                    "F7/F8 - create file/directory\n" + "F9/F10 - remove file/directory\n");
        }
    }
}
