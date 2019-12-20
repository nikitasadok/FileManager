package com.company;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class MyMouseListener implements MouseListener {
    private JScrollPane pane;
    private JList list;
    private SubPanel panel;
    private Controller control = Controller.getInstance();
    private String side;
    private GeneralPanel generalPanel;

    MyMouseListener(GeneralPanel gpanel, SubPanel panel){
        this.generalPanel = gpanel;
        this.panel = panel;
    }


    MyMouseListener(GeneralPanel gpanel, SubPanel panel, JList<String> list){
        this.generalPanel = gpanel;
        this.panel = panel;
        this.list = list;
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (!new File(panel.currentPath + "/" +  list.getSelectedValue().toString()).canRead() && list.getSelectedValue() != ".." && mouseEvent.getClickCount()
        == 2){
            JOptionPane.showMessageDialog(null,"Unable to open the directory or file. You don't seem to have enough rights",
                    "Warning!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (mouseEvent.getClickCount() == 2){
            if (list.getSelectedValue() == ".."){
                if (panel.currentPath.equals(panel.ROOT)){
                    return;
                }
                panel.currentPath = (new File(panel.currentPath)).getParent();
            } else {
                panel.currentPath += "/";
                panel.currentPath += list.getSelectedValue();
                //panel.currentPath += "/";
            }
            sendUpdateSig();
        }
        if (mouseEvent.getClickCount() == 1){
            generalPanel.updateSelector(list);
        }
        }

    public void sendUpdateSig(){
         generalPanel.refreshPanel();
    }

    public void mousePressed(MouseEvent mouseEvent){
    }

    public void mouseReleased(MouseEvent mouseEvent) {
    }

    public void mouseEntered(MouseEvent mouseEvent) {
    }

    public void mouseExited(MouseEvent mouseEvent) {
    }


    }

