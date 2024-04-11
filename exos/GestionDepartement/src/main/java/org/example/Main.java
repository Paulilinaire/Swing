package org.example;

import org.example.view.DepartmentUI;
import org.example.view.EmployeeUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Gestion Départements/Salariés");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        EmployeeUI newContentPane = new EmployeeUI();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setSize(400, 480);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}