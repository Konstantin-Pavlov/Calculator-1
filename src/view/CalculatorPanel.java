package view;


import controller.Calc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by gorobec on 01.03.16.
 */
public class CalculatorPanel extends JPanel implements ActionListener, KeyListener{
    Calc calculator;
    private static final long serialVersionId = 1L;
    private static final int WIDTH = 420;
    private static final int HEIGHT = 280;

    private JButton[] numberButtons;
    private JButton[] optionButtons;

    private JTextField field;

    private GridBagLayout layout;
    private GridBagConstraints gbc;
//[0] = gridx, [1] = gridy, [2] = gridwidth, [3] = gridheight
    private int[][] numConstaints = new int[][]{
            {0, 6, 1, 1},
            {0, 5, 1, 1},
            {1, 5, 1, 1},
            {2, 5, 1, 1},
            {0, 4, 1, 1},
            {1, 4, 1, 1},
            {2, 4, 1, 1},
            {0, 3, 1, 1},
            {1, 3, 1, 1},
            {2, 3, 1, 1},
    };
    
    private int[][] optionConstaints = new int[][]{
            {1, 6, 1, 1},
            {2, 6, 1, 1},
            {3, 6, 2, 1},
            {3, 5, 1, 1},
            {4, 5, 1, 1},
            {3, 4, 1, 1},
            {4, 4, 1, 1},
            {3, 3, 1, 1},
            {4, 3, 1, 1},
            {0, 2, 1, 1},
            {1, 2, 1, 1},
            {2, 2, 1, 1},
            {3, 2, 1, 1},
            {4, 2, 1, 1},
    };
        
    public CalculatorPanel(){
        calculator = new Calc();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        layout = new GridBagLayout();
        layout.columnWidths = new int[]{80, 80, 80, 80, 80};
        layout.rowHeights = new int[]{30, 30, 40, 40, 40, 40, 40};
        setLayout(layout);
        gbc = new GridBagConstraints();

        numberButtons = new JButton[10];
        for (int i = 0; i < numberButtons.length; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));

            numberButtons[i].addActionListener(this);

            gbc.gridx = numConstaints[i][0];
            gbc.gridy = numConstaints[i][1];
            gbc.gridwidth = numConstaints[i][2];
            gbc.gridheight = numConstaints[i][3];
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(3, 3, 3, 3);
            add(numberButtons[i], gbc);
        }
        optionButtons = new JButton[14];
        optionButtons[0] = new JButton(".");
        optionButtons[1] = new JButton("^");
        optionButtons[2] = new JButton("=");
        optionButtons[3] = new JButton("cos");
        optionButtons[4] = new JButton("sin");
        optionButtons[5] = new JButton("(");
        optionButtons[6] = new JButton(")");
        optionButtons[7] = new JButton("+");
        optionButtons[8] = new JButton("-");
        optionButtons[9] = new JButton("C");
        optionButtons[10] = new JButton("<-");
        optionButtons[11] = new JButton("+/-");
        optionButtons[12] = new JButton("*");
        optionButtons[13] = new JButton("/");

        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i].addActionListener(this);
            gbc.gridx = optionConstaints[i][0];
            gbc.gridy = optionConstaints[i][1];
            gbc.gridwidth = optionConstaints[i][2];
            gbc.gridheight = optionConstaints[i][3];
            gbc.fill = GridBagConstraints.BOTH;
            add(optionButtons[i], gbc);
        }

        field = new JTextField();
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        field.setBackground(Color.WHITE);
        field.setEditable(false);
        field.setFont(new Font("Arial", Font.ITALIC, 24));
        field.addKeyListener(this);
        field.setHorizontalAlignment(SwingConstants.RIGHT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.gridheight = 2;
        add(field, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < numberButtons.length; i++) {
            if(e.getSource() == numberButtons[i]){
                field.setText(field.getText() + i);
            }
        }
        if(!field.getText().isEmpty()) {
            if (e.getSource() == optionButtons[0]) {
                field.setText(changeLast(field.getText(), "."));
            }else if (e.getSource() == optionButtons[1]) {
                field.setText(checkValidity(field.getText(), "^"));
            }else if (e.getSource() == optionButtons[2]) {
                makeResult();
            }else if (e.getSource() == optionButtons[6]) {
                field.setText(field.getText() + ")");

            }else if (e.getSource() == optionButtons[7]) {
                field.setText(checkValidity(field.getText(), "+"));
            }else if (e.getSource() == optionButtons[8]) {
                field.setText(checkValidity(field.getText(), "-"));
            }else if (e.getSource() == optionButtons[9]) {
                field.setText("");
            }else if (e.getSource() == optionButtons[10]) {
                backSpace();
            }else if (e.getSource() == optionButtons[12]) {
                field.setText(checkValidity(field.getText(), "*"));
            }else if (e.getSource() == optionButtons[13]) {
                field.setText(checkValidity(field.getText(), "/"));
            }
        }if (e.getSource() == optionButtons[5]) {
            field.setText(field.getText() + "(");
        }else if (e.getSource() == optionButtons[11]){
            String expression = changeLast(field.getText(), "-");
            field.setText(expression);
        }else if (e.getSource() == optionButtons[3]) {
            field.setText(field.getText() + "cos");
        }else if (e.getSource() == optionButtons[4]) {
            field.setText(field.getText() + "sin");
        }
    }

    private String checkValidity(String text, String operand) {
        char[] symbols = text.toCharArray();
        int lastSymbol = symbols.length - 1;
        if (lastSymbol == -1) {
            return text;
        }
        char[] operands = {'^', '/', '*', '-', '+'};
        if(isOperand(symbols[lastSymbol], operands)){
          backSpace();
        }
        return field.getText() + operand;
    }

    private boolean isOperand(char symbol, char[] operands) {
        for (char operand : operands) {
            if(operand == symbol){
                return true;
            }
        }
        return false;
    }


    private void makeResult() {
        double result = calculator.calculate(field.getText());
        if(result % 1 == 0){
            field.setText("" + (int)result);
        }else {
            field.setText("" + result);
        }
    }

    private String changeLast(String expression, String operand) {
//        todo
        String[] numbers = expression.split("[-+*/]");
        System.out.println(numbers[0]);
        String last = numbers[numbers.length - 1];
        if(operand.equals("-") && !last.contains(operand)){
            last = "-" + last;
            return expression.substring(0, expression.length() - last.length() + 1) + last;
        } else if(operand.equals(".") && !last.contains(operand)) {
            last =  last + ".";
            return expression.substring(0, expression.length() - last.length() + 1) + last;
        }
//        todo
        return expression;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_NUMPAD0 || e.getKeyCode() == KeyEvent.VK_0){
            field.setText(field.getText() + 0);
        } else if(e.getKeyCode() == KeyEvent.VK_NUMPAD1 || e.getKeyCode() == KeyEvent.VK_1){
            field.setText(field.getText() + 1);
        } else if(e.getKeyCode() == KeyEvent.VK_NUMPAD2 || e.getKeyCode() == KeyEvent.VK_2){
            field.setText(field.getText() + 2);
        } else if(e.getKeyCode() == KeyEvent.VK_NUMPAD3 || e.getKeyCode() == KeyEvent.VK_3){
            field.setText(field.getText() + 3);
        } else if(e.getKeyCode() == KeyEvent.VK_NUMPAD4 || e.getKeyCode() == KeyEvent.VK_4){
            field.setText(field.getText() + 4);
        } else if(e.getKeyCode() == KeyEvent.VK_NUMPAD5 || e.getKeyCode() == KeyEvent.VK_5){
            field.setText(field.getText() + 5);
        } else if(e.getKeyCode() == KeyEvent.VK_NUMPAD6 || e.getKeyCode() == KeyEvent.VK_6){
            field.setText(field.getText() + 6);
        } else if(e.getKeyCode() == KeyEvent.VK_NUMPAD7 || e.getKeyCode() == KeyEvent.VK_7){
            field.setText(field.getText() + 7);
        } else if(e.getKeyCode() == KeyEvent.VK_NUMPAD8 || e.getKeyCode() == KeyEvent.VK_8){
            field.setText(field.getText() + 8);
        } else if(e.getKeyCode() == KeyEvent.VK_NUMPAD9 || e.getKeyCode() == KeyEvent.VK_9){
            field.setText(field.getText() + 9);
        } else if(e.getKeyCode() == KeyEvent.VK_ADD){
            field.setText(checkValidity(field.getText(), "+"));
        } else if(e.getKeyCode() == KeyEvent.VK_MINUS){
            field.setText(checkValidity(field.getText(), "-"));
        } else if(e.getKeyCode() == KeyEvent.VK_MULTIPLY){
            field.setText(checkValidity(field.getText(), "*"));
        } else if(e.getKeyCode() == KeyEvent.VK_DIVIDE){
            field.setText(checkValidity(field.getText(), "/"));
        } else if(e.getKeyCode() == KeyEvent.VK_ENTER){
            makeResult();
        } else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            field.setText("");
        } else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            backSpace();
        }
    }

    private void backSpace() {
        String symbols = field.getText();
        field.setText(symbols.length() > 0 ? symbols.substring(0, symbols.length() - 1) : "");
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
