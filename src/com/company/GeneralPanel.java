package com.company;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class GeneralPanel extends JPanel {
    Controller controller = Controller.getInstance();
    SubPanel leftPanel;
    SubPanel rightPanel;
    String lastClicked;
    TextEditorViewer textEditorViewer;
    ArrayList<JList> generalPanelLists = new ArrayList<JList>();

    GeneralPanel() throws IOException {

        leftPanel = new SubPanel(this);
        rightPanel = new SubPanel(this);

        generalPanelLists.add(leftPanel.fileList);
        generalPanelLists.add(leftPanel.folderList);
        generalPanelLists.add(rightPanel.fileList);
        generalPanelLists.add(rightPanel.folderList);

        textEditorViewer = new TextEditorViewer();
    }

    void setInfo(){
        leftPanel.folderList.setListData(controller.getFolderNames(leftPanel.currentPath));
        leftPanel.fileList.setListData(controller.getFileNames(leftPanel.currentPath));
        rightPanel.folderList.setListData(controller.getFolderNames(rightPanel.currentPath));
        rightPanel.fileList.setListData(controller.getFileNames(rightPanel.currentPath));
        leftPanel.folderScrollPane.setColumnHeaderView(new JLabel(leftPanel.currentPath));
        rightPanel.folderScrollPane.setColumnHeaderView(new JLabel(rightPanel.currentPath));
    }

    void refreshPanel(){
        setInfo();
    }

    void updateSelector(JList list){
        for (JList l : generalPanelLists){
            if (l != list) l.clearSelection();
        }
    }
}
