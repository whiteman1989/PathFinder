package com.path_find.UI.Components;

import com.path_find.UI.AppWindow;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FieldListener implements MouseListener {
    private AppWindow _app;
    public FieldListener(AppWindow app) {
        _app = app;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if(e.getButton() == MouseEvent.BUTTON1) {
            _app.ClickOnField(x,y);
        }
        if(e.getButton() == MouseEvent.BUTTON3) {
            _app.RightClickOnField(x,y);
        }
        System.out.println("pressed |" + e.getButton() + "| mouse button");
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
