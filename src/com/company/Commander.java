package com.company;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.*;


class Commander {
    boolean createFile(SubPanel panel, String filename) throws IOException {
        File file = new File(panel.currentPath + "/" + filename);
        return file.createNewFile();
    }

    boolean createDirectory(SubPanel panel, String filename) {
        return ((new File(panel.currentPath + "/" + filename)).mkdir());
    }

    boolean removeFile(SubPanel panel, JList list) {
        System.out.println(list);
        File file = new File(panel.currentPath + "/" + list.getSelectedValue());
        return file.delete();
    }

    void removeDirectory(SubPanel panel, JList list, String directory) {
        for (File file : new File(directory).listFiles()) {
            if (file.isDirectory()) {
                directory = file.getAbsolutePath();
                removeDirectory(panel, list, directory);
                file.delete();
            } else {
                file.delete();
            }
        }
    }

    private List<File> selectFiles(String pattern, String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        List<File> toSelect = new ArrayList<File>(files.length);
        for (int i = 0; i < files.length; ++i) {
            if (!files[i].isDirectory() && !files[i].getName().startsWith(".") && files[i].getName().contains(pattern)) {
                toSelect.add(files[i]);
            }
        }
        return toSelect;
    }

    boolean moveFile(SubPanel panel, JList list, String path) {
        File file = new File(panel.currentPath + "/" + list.getSelectedValue());
        if (file.renameTo(new File(path + "/" + file.getName()))) {
            file.delete();
            System.out.println("file moved");
            return true;
        } else return false;
    }

    void moveFilesOnPattern(String path, String pattern, SubPanel panel, JList list) {
        List<File> toMove = selectFiles(pattern, panel.currentPath);
        JOptionPane.showMessageDialog(null, "You have moved " + toMove.size() + " files");
        for (File file : toMove) {
            if (file.renameTo(new File(path + "/" + file.getName()))) {
                file.delete();
            }

        }
    }

    void copyFiles(String from, String to) {
        var fileFrom = new File(from);
        var fileTo = new File(to);
        if (fileFrom.isFile()) {
            try {
                FileUtils.copyFile(fileFrom, fileTo);
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Unable to copy a file!");
                System.err.println("EROROROROR!");
            }
        }
        if (fileFrom.isDirectory()) {
            try {
                FileUtils.copyDirectoryToDirectory(fileFrom, fileTo);
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Unable to copy a directory!");
            }
        }
    }

    boolean renameFileOrDirectory(SubPanel panel, JList list, String newName) {
        File file = new File(panel.currentPath + "/" + list.getSelectedValue());
        if (file.renameTo(new File(panel.currentPath + "/" + newName))) {
            return true;
        } else return false;
    }

    void findFile(MyKeyListener myKeyListener, JList list, String filename, String directory) {
        for (File file : (new File(directory).listFiles())) {
            if (file.getName().equals(filename)) {
                myKeyListener.updateList(file.getAbsolutePath());
            }
            if (file.isDirectory() && file.canRead()) {
                directory = file.getAbsolutePath();
                findFile(myKeyListener, list, filename, directory);
            }
        }
    }

    void findHtmlOnString(MyKeyListener myKeyListener, TextEditorViewer textEditorViewer, String searchString, String directory) throws IOException {
        for (File file : (new File(directory).listFiles())) {
            if (file.getName().matches(".*.html")) {
                BufferedReader bf = new BufferedReader(new FileReader(file.getAbsolutePath()));
                String htmlText = "";
                String temp = null;
                while ((temp = bf.readLine()) != null) {
                    htmlText += temp;
                }
                if (htmlText.contains(searchString)){
                    myKeyListener.updateHtmlList(file.getAbsolutePath());
                }
                else {
                    String htmlTextWithoutTags = Jsoup.parse(file, "UTF-8").text();
                    htmlTextWithoutTags = htmlTextWithoutTags.replaceAll("<\\s*a[^>]*>(.*?)<\\s*/\\s*a>", "");
                    if (htmlTextWithoutTags.contains(searchString)) {
                        myKeyListener.updateHtmlList(file.getAbsolutePath());
                    }
                }
            }
            if (file.isDirectory() && file.canRead()) {
                directory = file.getAbsolutePath();
                findHtmlOnString(myKeyListener, textEditorViewer, searchString, file.getAbsolutePath());
            }
        }
    }

    ArrayList<String> findMostUsedWord(String text){
        HashMap<String,Integer>wc = new HashMap<>();
        for (String word : text.split("\\W+")){
            if (wc.containsKey(word)){
                wc.put(word, wc.get(word) + 1);
            }
            else{
                wc.put(word, 1);
            }
        }
        int max = 0;
        ArrayList<String> mostUsedWords = new ArrayList<>();
        for(String word : wc.keySet()){
            for(Integer count : wc.values()){
                if (count > max){
                    max = count;
                }
            }
        }
        for(String word : wc.keySet()){
            if (wc.get(word) == max){
                mostUsedWords.add(word);
            }
        }
        return mostUsedWords;
    }
}

//}
