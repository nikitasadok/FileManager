package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

public class MyKeyListener implements KeyListener {
    private GeneralPanel generalPanel;
    private SubPanel panel;
    private JList list;
    private TextEditorViewer textEditorViewer;
    private JFrame frame = new JFrame();
    private JFrame htmlFrame = new JFrame();
    private JList<String> filesFound = new JList<>(new DefaultListModel<String>());
    private JList<String> htmlFilesFound = new JList<>(new DefaultListModel<>());


    MyKeyListener(GeneralPanel generalPanel, SubPanel panel){
        this.generalPanel = generalPanel;
        this.panel = panel;
    }

    MyKeyListener(GeneralPanel generalPanel, SubPanel panel, JList list){
        this.generalPanel = generalPanel;
        this.panel = panel;
        this.list = list;

        frame.setMinimumSize(new Dimension(500,300));
        frame.setLocationRelativeTo(null);
        JScrollPane js = new JScrollPane(filesFound);
        JScrollPane js1 = new JScrollPane(htmlFilesFound);

        JPanel panel1 = new JPanel();
        JPanel panel2= new JPanel();

        panel1.add(js);
        panel2.add(js1);

        htmlFrame.setMinimumSize(new Dimension(500,300));
        htmlFrame.setLocationRelativeTo(null);

        frame.add(panel1);
        htmlFrame.add(panel2);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == keyEvent.VK_F1){
            String path = (String)JOptionPane.showInputDialog(null, "Please enter the full path to which you want to copy your file");
            if (new File(path + "/" +  list.getSelectedValue()).exists()){
                JOptionPane.showMessageDialog(null, "This file already exists! You cannot copy it!");
            }
            try {
                sendUpdateSig();
                generalPanel.controller.copyFiles(panel.currentPath + "/" + list.getSelectedValue(), path + "/" + list.getSelectedValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (keyEvent.getKeyCode() == keyEvent.VK_F2){
            String pattern = (String)JOptionPane.showInputDialog(null, "Please enter some words in the html find you want " +
                    "to find");
            if (pattern == null){
                return;
            }
            else{
                try {
                    generalPanel.controller.findHtml(this, panel.currentPath, pattern, textEditorViewer);
                    htmlFilesFound.addKeyListener(new HtmlFrameKeyListener(generalPanel,htmlFilesFound, pattern));
                    htmlFrame.setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_F3) {
            try {
                TextEditorViewer te = new TextEditorViewer();
                te.viewFile(te.textEditorController.openFile(panel.currentPath + "/" + list.getSelectedValue().toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (keyEvent.getKeyCode() == keyEvent.VK_F4){
            String path = (String)JOptionPane.showInputDialog(null,"Please choose the directory you want to move your file or " +
                    "directory to", "Make your choice",JOptionPane.QUESTION_MESSAGE);
            if (path.equals(null)) return;
            if (new File( "/" + path + "/" + list.getSelectedValue()).exists()){
                int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to override "+
                        "file?");
                if (res == 0){
                    if (generalPanel.controller.moveFile(panel, list, path)) {
                        sendUpdateSig();
                        JOptionPane.showMessageDialog(null, "File moved successfully!");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Unable to move file");
                    }
                }
                else{
                    return;
                }
            }
            else {
                if (generalPanel.controller.moveFile(panel, list, path)) {
                    sendUpdateSig();
                    JOptionPane.showMessageDialog(null, "File moved successfully!");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Unable to move file");
                }
            }
        }
        if (keyEvent.getKeyCode() == keyEvent.VK_F5){

            String pattern = (String)JOptionPane.showInputDialog(null, "Please enter the pattern");
            if (pattern.equals(null)) return;
            String path = (String)JOptionPane.showInputDialog(null, "Please enter the directory where you want to move files");
            if (path.equals(null)) return;
            generalPanel.controller.moveFilesOnPattern(path, pattern, panel, list);
            sendUpdateSig();
        }
        if (keyEvent.getKeyCode() == keyEvent.VK_F6){
            String newName = (String)JOptionPane.showInputDialog(null, "Please enter new" +
                    " filename");
            if (newName.equals(null)){
                return;
            }
            else if (new File(panel.currentPath + "/" + newName).exists()){
                int res = JOptionPane.showConfirmDialog(null, "File with this name exists. Are "+
                        "you sure you want to override it?");
                if (res == 0){
                    return;
                }
                else{ generalPanel.controller.renameFileOrDirectory(panel,list, newName);
                sendUpdateSig();
            }}
            else {
                generalPanel.controller.renameFileOrDirectory(panel, list, newName);
                sendUpdateSig();
            }
        }

        if (keyEvent.getKeyCode() == keyEvent.VK_F7){
            Object[] possibilities = {"left", "right"};
            String directory = (String)JOptionPane.showInputDialog(null, "Please choose the directory where you want to create a file:", "Make your choice",
                    JOptionPane.PLAIN_MESSAGE,null,possibilities,"left");
            if (directory == null){
                return;
            }
            if (directory == "left"){
                panel = generalPanel.leftPanel;
            }
            if (directory == "right"){
                panel = generalPanel.rightPanel;
            }
            try {
                String filename = (String) JOptionPane.showInputDialog(null,"Please enter the name of your file:","New filename",0 );
                if (filename == null){
                    return;
                }
                if (new File(panel.currentPath + "/" + filename).exists()) {
                    JOptionPane.showMessageDialog(null,"Unable to create file! The file exists!");
                    return;
                }

                if (generalPanel.controller.createFile(panel, filename)) {
                    sendUpdateSig();
                    JOptionPane.showMessageDialog(null, "File created successfully!");
                }
                else JOptionPane.showMessageDialog(null, "Unable to create file!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (keyEvent.getKeyCode() == keyEvent.VK_F8){
            Object[] possibilities = {"left", "right"};
            String directory = (String)JOptionPane.showInputDialog(null, "Please choose the directory where you want to make a new one",
                    "Make your choice",
                    JOptionPane.PLAIN_MESSAGE,null,possibilities,"left");
            if (directory == null){
                return;
            }
            if (directory == "left"){
                panel = generalPanel.leftPanel;
            }
            if (directory == "right"){
                panel = generalPanel.rightPanel;
            }
            try {
                String filename = (String) JOptionPane.showInputDialog(null,"Please enter the name of your directory:",
                        "New directory",0 );
                if (new File(panel.currentPath + "/" + filename).exists()) {
                    JOptionPane.showMessageDialog(null, "Unable to create directory! It already exists!");
                    return;
                }
                if (generalPanel.controller.createDirectory(panel, filename)) {
                    sendUpdateSig();
                    JOptionPane.showMessageDialog(null, "Directory created successfully!");
                }
                else JOptionPane.showMessageDialog(null, "Unable to create directory!");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (keyEvent.getKeyCode() == keyEvent.VK_F9){
            int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this file?");

            if (res == 0) {
                if (generalPanel.controller.removeFile(panel, list)) {
                    sendUpdateSig();
                    JOptionPane.showMessageDialog(null, "File removed successfully!");
                }
                else JOptionPane.showMessageDialog(null, "Unable to delete file!");
            }
            else return;
        }
        if (keyEvent.getKeyCode() == keyEvent.VK_F10) {
            int res = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this directory?");
            if (res == 0){
                generalPanel.controller.removeDirectory(panel,list, panel.currentPath + "/" + list.getSelectedValue());
                    sendUpdateSig();
                    JOptionPane.showMessageDialog(null, "Directory removed successfully!");
            }
                else JOptionPane.showMessageDialog(null, "Unable to delete directory!");
            }


        if (keyEvent.getKeyCode() == keyEvent.VK_F11){
            String directory;
            String[] possibilities = {"left", "right"};
            String dir = (String)JOptionPane.showInputDialog(null, "Please choose the directory where you want to find a file",
                    "Make your choice",
                    JOptionPane.PLAIN_MESSAGE,null,possibilities,"left");
            if (dir.equals(null)){return;}

           // JList<String> filesFound = new JList<String>();

            if (dir.equals("left")){
                directory = generalPanel.leftPanel.currentPath;
            }
            else if (dir.equals("right")){
                directory = generalPanel.rightPanel.currentPath;
            }
            else {directory = null;}
            if (directory != null) {
                String filename = JOptionPane.showInputDialog(null, "Please enter the filename you want to find?");
                generalPanel.controller.findFile(this, list, filename, directory);
                frame.setVisible(true);
            }
            }
    }

    public void updateList(String listData){
        ((DefaultListModel)filesFound.getModel()).addElement(listData);
    }

    public void updateHtmlList(String listData) { ((DefaultListModel)htmlFilesFound.getModel()).addElement(listData);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    void sendUpdateSig(){
        generalPanel.refreshPanel();
    }
}
