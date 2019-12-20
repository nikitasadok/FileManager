package com.company;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public final class Controller {

    private static final Controller INSTANCE = new Controller();
    private Commander commander = new Commander();

    private Controller() {
    }

    public static Controller getInstance() {
        return INSTANCE;
    }

    String[] getFolderNames(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        List<String> filenames = new ArrayList<String>();
        if (!path.equals("/")) filenames.add("..");
        System.out.println(path);
        for (int i = 0; i < files.length; ++i) {
            if (files[i].isDirectory() && !files[i].getName().startsWith(".")) {
                filenames.add(files[i].getName());
            }
        }
        return filenames.toArray(new String[filenames.size()]);
    }

    String[] getFileNames(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        List<String> filenames = new ArrayList<>(files.length);
        for (int i = 0; i < files.length; ++i) {
            if (!files[i].isDirectory() && !files[i].getName().startsWith(".")) {
                filenames.add(files[i].getName());
            }
        }
        return filenames.toArray(new String[1]);
    }

    void moveFilesOnPattern(String path, String pattern, SubPanel panel, JList list){
        commander.moveFilesOnPattern(path, pattern, panel, list);
    }

    boolean createFile(SubPanel panel, String filename) throws IOException {
        return commander.createFile(panel, filename);
    }

    boolean createDirectory(SubPanel panel, String filename) throws IOException {
        return commander.createDirectory(panel, filename);
    }

    void copyFiles(String from, String to) throws IOException {
        commander.copyFiles(from, to);
    }

    boolean removeFile(SubPanel panel, JList list) {
        return commander.removeFile(panel, list);
    }

    void removeDirectory(SubPanel panel, JList list, String directory) {
        commander.removeDirectory(panel, list, directory);
    }

    boolean moveFile(SubPanel panel, JList list, String filename){
        return commander.moveFile(panel, list, filename);
    }

    boolean renameFileOrDirectory(SubPanel panel, JList list, String newFilename){
        return commander.renameFileOrDirectory(panel, list, newFilename);
    }
    void findFile(MyKeyListener myKeyListener, JList list, String filename, String directory) {
        commander.findFile(myKeyListener, list, filename, directory);

    }

    void findHtml(MyKeyListener myKeyListener, String directory, String searchString, TextEditorViewer textEditorViewer) throws IOException {
        commander.findHtmlOnString(myKeyListener, textEditorViewer, searchString, directory);
    }

    ArrayList<String> findMostUsedWord(String text){
        return commander.findMostUsedWord(text);
    }

}
