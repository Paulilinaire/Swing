package org.example;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator implements ActionListener {

    private JTextField screen;
    private String displayValue = "0";
    private char operator = ' ';
    private double firstValue = 0;

    public Calculator() {
        JFrame frame = new JFrame("Calculator");
        frame.setPreferredSize(new Dimension(400, 600));

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        JPanel operationPanel = new JPanel(new GridLayout(5, 1));
        JPanel resultPanel = new JPanel();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        JButton b1 = createButton("1", Color.LIGHT_GRAY);
        JButton b2 = createButton("2", Color.LIGHT_GRAY);
        JButton b3 = createButton("3", Color.LIGHT_GRAY);
        JButton b4 = createButton("4", Color.LIGHT_GRAY);
        JButton b5 = createButton("5", Color.LIGHT_GRAY);
        JButton b6 = createButton("6", Color.LIGHT_GRAY);
        JButton b7 = createButton("7", Color.LIGHT_GRAY);
        JButton b8 = createButton("8", Color.LIGHT_GRAY);
        JButton b9 = createButton("9", Color.LIGHT_GRAY);
        JButton b0 = createButton("0", Color.LIGHT_GRAY);
        JButton comma = createButton(",", Color.LIGHT_GRAY);

        JButton clear = createButton("C", Color.LIGHT_GRAY);
        JButton minusPlus = createButton("+/-", Color.LIGHT_GRAY);
        JButton percent = createButton("%", Color.LIGHT_GRAY);

        addButtonToPanel(buttonPanel, clear, gbc, 0, 0);
        addButtonToPanel(buttonPanel, minusPlus, gbc, 1, 0);
        addButtonToPanel(buttonPanel, percent, gbc, 2, 0);
        addButtonToPanel(buttonPanel, b7, gbc, 0, 1);
        addButtonToPanel(buttonPanel, b8, gbc, 1, 1);
        addButtonToPanel(buttonPanel, b9, gbc, 2, 1);
        addButtonToPanel(buttonPanel, b4, gbc, 0, 2);
        addButtonToPanel(buttonPanel, b5, gbc, 1, 2);
        addButtonToPanel(buttonPanel, b6, gbc, 2, 2);
        addButtonToPanel(buttonPanel, b1, gbc, 0, 3);
        addButtonToPanel(buttonPanel, b2, gbc, 1, 3);
        addButtonToPanel(buttonPanel, b3, gbc, 2, 3);
        gbc.gridwidth = 2; // Span two columns
        addButtonToPanel(buttonPanel, b0, gbc, 0, 4);
        gbc.gridwidth = 1; // Reset gridwidth
        addButtonToPanel(buttonPanel, comma, gbc, 2, 4);

        JButton plus = createButton("+", Color.ORANGE);
        JButton minus = createButton("-", Color.ORANGE);
        JButton multiply = createButton("*", Color.ORANGE);
        JButton divide = createButton("/", Color.ORANGE);
        JButton equal = createButton("=", Color.ORANGE);

        operationPanel.add(divide);
        operationPanel.add(multiply);
        operationPanel.add(minus);
        operationPanel.add(plus);
        operationPanel.add(equal);

        screen = new JTextField();
        screen.setEditable(false);
        screen.setBackground(Color.BLACK);
        screen.setPreferredSize(new Dimension(380, 170));
        screen.setHorizontalAlignment(JTextField.RIGHT);
        screen.setForeground(Color.WHITE);
        screen.setFont(new Font("Segoe UI", Font.PLAIN, 40));



        mainPanel.add(screen, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(operationPanel, BorderLayout.EAST);
        mainPanel.add(resultPanel, BorderLayout.SOUTH);
        mainPanel.setBackground(Color.BLACK);

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
        frame.pack();
    }

    private JButton createButton(String label, Color color) {
        JButton button = new JButton(label);
        button.addActionListener(this);
        button.setBackground(color);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        button.setPreferredSize(new Dimension(100, 100));

        return button;
    }



    private void addButtonToPanel(JPanel panel, Component component, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(component, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        String actionCommand = evt.getActionCommand();

        if (Character.isDigit(actionCommand.charAt(0))) {
            handleNumberInput(actionCommand.charAt(0));
        } else if (actionCommand.equals("+") || actionCommand.equals("-") || actionCommand.equals("*")
                || actionCommand.equals("/") || actionCommand.equals("^") || actionCommand.equals("%")) {
            handleOperatorInput(actionCommand.charAt(0));
        } else if (actionCommand.equals("=")) {
            handleEqual();
        } else if (actionCommand.equals("C")) {
            handleClear();
        } else if (actionCommand.equals(",")) {
            handleComma();
        }
    }

    private void handleComma() {
        if (!displayValue.contains(".")) {
            displayValue += ".";
            screen.setText(displayValue);
        }
    }



    private void handleNumberInput(char num) {
        if (displayValue.equals("0")) {
            displayValue = String.valueOf(num);
        } else {
            displayValue += num;
        }
        screen.setText(displayValue);
    }

    private void handleOperatorInput(char op) {
        operator = op;
        firstValue = Double.parseDouble(displayValue);
        displayValue = "0";
    }

    private void handleEqual() {
        double secondValue = Double.parseDouble(displayValue);
        double result = 0;

        switch (operator) {
            case '+':
                result = firstValue + secondValue;
                break;
            case '-':
                result = firstValue - secondValue;
                break;
            case '*':
                result = firstValue * secondValue;
                break;
            case '/':
                result = firstValue / secondValue;
                break;
            case '^':
                result = Math.pow(firstValue, secondValue);
                break;
            case '%':
                result = firstValue % secondValue;
                break;
        }

        displayValue = String.valueOf(result);
        screen.setText(displayValue);
        operator = ' ';
        firstValue = 0;
    }

    private void handleClear() {
        displayValue = "0";
        operator = ' ';
        firstValue = 0;
        screen.setText(displayValue);
    }

}
