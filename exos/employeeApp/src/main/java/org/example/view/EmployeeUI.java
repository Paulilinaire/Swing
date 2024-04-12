package org.example.view;

import org.example.dao.EmployeeDao;
import org.example.view.components.ActionsPanel;
import org.example.view.components.EmployeeTableModel;
import org.example.view.components.FormPanel;

import javax.swing.*;
import java.awt.*;

public class EmployeeUI extends JPanel {

    private FormPanel formPanel;
    private ActionsPanel actionsPanel;
    private EmployeeDao employeeDao;

    public EmployeeUI() {
        setLayout(new GridLayout(3, 1));
        employeeDao = new EmployeeDao();

        formPanel = new FormPanel();
        add(formPanel);

        actionsPanel = new ActionsPanel(employeeDao);
        add(actionsPanel);

        JTable employeeTable = new JTable(new EmployeeTableModel(employeeDao.findAll()));
        employeeTable.setPreferredScrollableViewportSize(new Dimension(500, 70));

        JScrollPane scrollPane = new JScrollPane(employeeTable);

        add(scrollPane);

        setVisible(true);
    }
}
