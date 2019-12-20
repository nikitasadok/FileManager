package com.company;

import javax.swing.*;
import java.awt.*;

public class SubPanel extends JPanel{
    JList<String> folderList;
    JList<String> fileList;
    JScrollPane folderScrollPane;
    JScrollPane fileScrollPane;
    final String ROOT = "/";
    String currentPath = ROOT;

    SubPanel(GeneralPanel panel){
        folderList = new JList<>(panel.controller.getFolderNames(ROOT));
        fileList = new JList<>(panel.controller.getFileNames(ROOT));

        folderScrollPane = new JScrollPane(folderList);
        fileScrollPane = new JScrollPane(fileList);

        folderScrollPane.setColumnHeaderView(new JLabel(currentPath));

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1;


        c.gridx = 0;
        c.gridy = 0;
        this.add(folderScrollPane, c);
        c.gridx = 1;
        c.gridy = 0;
        this.add(fileScrollPane, c);
        this.setFocusable(true);
        folderList.addMouseListener(new MyMouseListener(panel,this, folderList));
        fileList.addMouseListener(new MyMouseListener(panel,this,fileList));
        folderList.addKeyListener(new MyKeyListener(panel, this, folderList));
        fileList.addKeyListener(new MyKeyListener(panel,this, fileList));

        /*folderList.setBounds(0,0,200,200);
        fileList.setBounds(200,0,200,200);*/

        //this.add(folderScrollPane);
        //this.add(fileScrollPane);
    }


}
