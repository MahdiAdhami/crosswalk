package CrossWalk.Menu;

import CrossWalk.Utilities.Const;
import javax.swing.JOptionPane;

public class TipsMenu extends Menu {

    public TipsMenu() {
        super("نکات", 500, 500);
    }

    @Override
    protected void createPanel() {
         JOptionPane.showMessageDialog(null, Const.TIPS, "نکات",JOptionPane.INFORMATION_MESSAGE);
    }

}
