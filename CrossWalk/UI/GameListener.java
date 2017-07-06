/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CrossWalk.UI;

import CrossWalk.Menu.ResumeGameMenu;
import CrossWalk.StoreData.ResumeAndLoad;
import CrossWalk.Object.Line;
import CrossWalk.Object.Sheep;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GameListener {

    private final KeyListener KeyListener;

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
                else if (keyCode == KeyEvent.VK_ESCAPE ||keyCode == KeyEvent.VK_P) {
                    InitGame.GameStop = true;
                    ResumeGameMenu menu = new ResumeGameMenu(Lines);
                    menu.show();
                }
                else if (Sheep.AutoMove) {
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

    }

    public KeyListener getKeyListener() {
        return KeyListener;
    }

}
