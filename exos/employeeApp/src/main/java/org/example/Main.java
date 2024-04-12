package org.example;

import org.example.view.EmployeeUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame.setDefaultLookAndFeelDecorated(true);

            JFrame frame = new JFrame("Employee App");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            EmployeeUI employeeUI = new EmployeeUI();
            frame.add(employeeUI);
            frame.setSize(1000, 700);

            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        });
    }
}