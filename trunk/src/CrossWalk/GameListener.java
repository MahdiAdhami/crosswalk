/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CrossWalk;

import CrossWalk.StoreData.ResumeAndLoad;
import CrossWalk.Object.Line;
import CrossWalk.Object.MoveableObject.Sheep;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GameListener {

    public KeyListener KeyListener;
    public MouseListener MouseListener;

    public GameListener(ArrayList<Line> Lines) {

        KeyListener = new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                if (keyCode == KeyEvent.VK_F2 || keyCode == KeyEvent.VK_T) {
                    InitGame.GameStop = !InitGame.GameStop;
                    ResumeAndLoad saveGame = new ResumeAndLoad();
                    saveGame.SaveGameForResume(Lines);
                    JOptionPane.showMessageDialog(null, "بازی با موفقیت ذخیره شد و در منوی اصلی قابل بازیابی است!", "ذخیره شد", JOptionPane.INFORMATION_MESSAGE);
                }
                if (Sheep.AutoMove) {
                    return;
                }

                InitGraphic.Sheep.move(keyCode);
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
                    ResumeAndLoad saveGame = new ResumeAndLoad();
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