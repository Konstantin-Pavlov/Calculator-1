package view;

import javax.swing.*;

/**
 * Created by gorobec on 28.02.16.
 */
public class Run {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            UIManager.setLookAndFeel  ("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        CalculatorFrame calculator = new CalculatorFrame();
    }
}
