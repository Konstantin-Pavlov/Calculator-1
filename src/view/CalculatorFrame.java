package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by gorobec on 28.02.16.
 */
public class CalculatorFrame extends JFrame{
    public CalculatorFrame() {
        setLayout(new BorderLayout());
        setTitle("Calculator");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(new CalculatorPanel(), BorderLayout.CENTER);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
