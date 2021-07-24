package com.path_find.UI.EventListeners;

import com.path_find.UI.AppWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearButtonEventListener implements ActionListener {
    private AppWindow _app;

    public ClearButtonEventListener(AppWindow app) {
        _app = app;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        _app.ClearField();
    }
}
