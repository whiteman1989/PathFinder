package com.path_find.UI.components;

import com.path_find.UI.components.FieldDrawComponent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FieldListener implements MouseListener {
    private FieldDrawComponent _field;
    public FieldListener(FieldDrawComponent field) {
        _field = field;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = (int)e.getX();
        int y = (int)e.getY();
        System.out.println("x:"+x+" y:"+y);
        _field.AddWall(y,x);
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
