package view;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/**
 * Created by gorobec on 28.02.16.
 */
public class CalculatorFrame extends JFrame{
    private MenuBar menuBar;
    private CalculatorPanel panel;

    public CalculatorFrame() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        menuBar = new MenuBar();
        panel = new CalculatorPanel();
        menuBar.getCopy().addActionListener(e -> {
            copyTextField();
        });
        menuBar.getPaste().addActionListener(e -> {
            pasteTextField();
        });

        setLayout(new BorderLayout());
        setTitle("Calculator");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setJMenuBar(menuBar);
        add(panel, BorderLayout.CENTER);
//        todo keyListener
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_C) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                    copyTextField();
                    System.out.println("woot!");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void pasteTextField() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(null);
        try {
//                todo validation
           panel.getField().setText((String) contents.getTransferData(DataFlavor.stringFlavor));
        } catch (UnsupportedFlavorException | IOException e1) {
            e1.printStackTrace();
        }
    }

    private void copyTextField() {
        Clipboard clbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection strsel = new StringSelection(panel.getField().getText());
        clbrd.setContents(strsel, null);
    }
}
