package org.example;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class FifteenPuzzleGame {

    private int size = 3;
    private JPanel gamePanel;
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JTextField textField;
    private JButton startButton;
    private FlowLayout flowLayout;
    private GridBagLayout gridBagLayout;
    private GridBagConstraints gridBagConstraints;

    private JLabel headerLabel;
    private int[] numbers;

    public FifteenPuzzleGame() {
        mainPanel = new JPanel(new BorderLayout());
        createHeader();
        createBoard();
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        mainPanel.setSize(200, 200);
    }

    private void createHeader(){
        flowLayout = new FlowLayout();
        headerPanel = new JPanel();
        headerPanel.setLayout(flowLayout);
        headerLabel = new JLabel("Taille Jeu");
        textField = new JTextField();
        textField.setColumns(4);
        startButton = new JButton("Démarer");
        startButton.addActionListener(e -> {
            size = Integer.valueOf(textField.getText());
            mainPanel.remove(gamePanel);
            createBoard();
        });
    }

    private void createBoard(){

    }
}