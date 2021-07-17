package com.path_find.UI.components;

import com.path_find.UI.AppWindow;
import com.path_find.UI.components.FieldDrawComponent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FieldListener implements MouseListener {
    private AppWindow _app;
    public FieldListener(AppWindow app) {
        _app = app;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = (int)e.getX();
        int y = (int)e.getY();
        System.out.println("x:"+x+" y:"+y);
        _app.ClickOnField(x,y);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
