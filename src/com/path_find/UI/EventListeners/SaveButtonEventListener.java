package com.path_find.UI.EventListeners;

import com.path_find.UI.AppWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class SaveButtonEventListener implements ActionListener {
    private AppWindow _app;

    public SaveButtonEventListener(AppWindow app) {
        _app = app;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        _app.Save();
    }
}
