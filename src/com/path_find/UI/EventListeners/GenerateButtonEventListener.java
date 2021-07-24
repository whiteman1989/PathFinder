package com.path_find.UI.EventListeners;

import com.path_find.UI.AppWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateButtonEventListener implements ActionListener {
    AppWindow _app;

    public GenerateButtonEventListener(AppWindow app) {
        _app = app;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        _app.Generate();
    }
}
