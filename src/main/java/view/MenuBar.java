package view;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by gorobec on 09.03.16.
 */
public class MenuBar extends JMenuBar {
    private JMenu file;
    private JMenu edit;
    private JMenu help;
    private JMenuItem close;
    private JMenuItem copy;
    private JMenuItem paste;
    private JMenuItem about;
    private Font menuFont;
    public MenuBar(){
        file = new JMenu(" File ");
        edit = new JMenu(" Edit ");
        help = new JMenu(" Help ");
        menuFont = new Font("Arial", Font.ITALIC, 14);
        file.setFont(menuFont);
        edit.setFont(menuFont);
        help.setFont(menuFont);
        close = new JMenuItem(" Close ");
        copy = new JMenuItem(" Copy ");
        paste = new JMenuItem(" Paste ");
        about = new JMenuItem(" About ");
        about.addActionListener(e -> {
            JOptionPane.showMessageDialog(this.getParent(),
                    "Created by Yevhenij Vorobiei\n\nyevhenijvorobiei@gmail.com");
        });
        close.setFont(menuFont);
        copy.setFont(menuFont);
        paste.setFont(menuFont);
        about.setFont(menuFont);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        file.add(close);
        edit.add(copy);
        edit.add(new JSeparator(JPopupMenu.Separator.HORIZONTAL));
        edit.add(paste);
        help.add(about);
        add(file);
        add(edit);
        add(help);
    }

    public JMenuItem getCopy() {
        return copy;
    }

    public JMenuItem getPaste() {
        return paste;
    }
}
