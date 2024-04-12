package org.example.view.components;

import org.example.dao.EmployeeDao;
import org.example.model.Employee;
import org.example.model.Qualification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionsPanel extends JPanel {

    private JTextField jtfSearch;
    private EmployeeDao employeeDao;

    public ActionsPanel(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;

        setLayout(new FlowLayout());
        setSize(40, 120);

        JLabel lblSearch = new JLabel("Search:");
        jtfSearch = new JTextField(15);
        add(lblSearch);
        add(jtfSearch);

        JButton btnNew = new JButton("New");
        JButton btnSave = new JButton("Save");
        JButton btnEdit = new JButton("Edit");
        JButton btnClear = new JButton("Clear");
        JButton btnPrint = new JButton("Print");

        Dimension btnDimension = new Dimension(100, 30);
        btnNew.setPreferredSize(btnDimension);
        btnSave.setPreferredSize(btnDimension);
        btnEdit.setPreferredSize(btnDimension);
        btnClear.setPreferredSize(btnDimension);
        btnPrint.setPreferredSize(btnDimension);

        add(btnNew);
        add(btnSave);
        add(btnEdit);
        add(btnClear);
        add(btnPrint);


        // Add action listeners to buttons
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement new employee creation
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement employee editing
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement form field clearing
            }
        });

        btnPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement printing
            }
        });
    }
}