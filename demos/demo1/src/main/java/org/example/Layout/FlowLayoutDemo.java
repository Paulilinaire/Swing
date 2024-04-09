package org.example.Layout;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class FlowLayoutDemo {

    private JPanel jPanel;
    private JLabel resultLabel;
    private JComboBox<String> comboBox;

    public FlowLayoutDemo() {

        jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));
        JButton button = new JButton("Button1");
        button.setBackground(Color.MAGENTA);
        button.addActionListener(e -> System.out.println("click me!"));
        jPanel.add(button);

        jPanel.add(new JButton("don't click me"));
        jPanel.add(new JButton("whatever"));

        jPanel.add(new JLabel("Label"));

        JTextField input = new JTextField();
        jPanel.add(input);

        comboBox = new JComboBox<>(new String[]{"go shopping", "drink a beer", "eat burger", "eveything"});
        jPanel.add(comboBox);

        resultLabel = new JLabel("Result and Selection will be display here");
        jPanel.add(resultLabel);

        JButton validateButton = new JButton("Submit");

        validateButton.addActionListener(e -> {
            String selected = (String) comboBox.getSelectedItem();
            String inputText = input.getText();

            resultLabel.setText("Input data : " + inputText + "Selected Data : " + selected);

        });

        jPanel.add(validateButton);

    }



}
