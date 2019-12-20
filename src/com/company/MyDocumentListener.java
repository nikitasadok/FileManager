package com.company;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MyDocumentListener implements DocumentListener {
    private boolean changed = false;

    public boolean isChanged(){
        return changed;
    }

    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
        changed = true;
    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        changed = true;
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
        changed = true;
    }
}
