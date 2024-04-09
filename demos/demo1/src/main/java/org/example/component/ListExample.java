package org.example.component;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

public class ListExample extends JFrame{

    public ListExample() {

        String[] listItems = {"cake", "cupcake", "brownie", "ice cream"};

        JList<String> list = new JList<>(listItems);

        JLabel label = new JLabel("No element selected");

        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedItem = list.getSelectedValue();
                label.setText("Selected element: " + selectedItem);
            }
        });

        this.getContentPane().add(new JScrollPane(list), BorderLayout.CENTER);
        this.getContentPane().add(label, BorderLayout.SOUTH);

        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            ListExample listExample = new ListExample();
            listExample.setVisible(true);
        });

    }


}
