/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javagame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GameListener {

    public KeyListener KeyListener;
    public MouseListener MouseListener;
    static public ArrayList<Line> Lines;

    public GameListener(ArrayList<Line> Lines) {
        this.Lines = Lines;

        KeyListener = new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                InitGraphic.Sheep.keyPressed(e);
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };

        MouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getX() >= 2 && e.getX() < 90 && e.getY() > 0 && e.getY() < 60) {
                    InitGame.GameStop = !InitGame.GameStop;
                } else if (e.getX() > 100 && e.getX() < 320 && e.getY() > 0 && e.getY() < 60) {
                    SaveAndLoad saveGame = new SaveAndLoad();
                    saveGame.SaveGameForResume(Lines);
                    InitGame.GameStop = !InitGame.GameStop;
                }
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

        };
    }
}
