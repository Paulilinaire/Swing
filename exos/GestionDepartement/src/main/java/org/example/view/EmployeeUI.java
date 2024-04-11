package org.example.view;

import org.example.controller.EmployeeController;
import org.example.dao.EmployeeDao;
import org.example.model.Employee;
import org.example.model.Role;
import org.example.utils.EmployeeTableModel;
import org.example.view.dialog.AddDialog;
import org.example.view.dialog.DeleteDialog;
import org.example.view.dialog.EditDialog;

import javax.swing.*;
import java.awt.*;
import java.io.ObjectInputStream;
import java.util.List;


public class EmployeeUI extends JPanel {

    public EmployeeUI() {
        super(new BorderLayout());
        EmployeeController employeeController = new EmployeeController(new EmployeeDao());
        JTable employeeTable = new JTable(new EmployeeTableModel(employeeController.getAllEmployees()));
        employeeTable.setPreferredScrollableViewportSize(new Dimension(500, 70));

        JScrollPane scrollPane = new JScrollPane(employeeTable);

        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // ADD
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            AddDialog dialog = new AddDialog();
            dialog.setVisible(true);});
        buttonPanel.add(addButton);


        // EDIT
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> {
            int selectedRow = employeeTable.getSelectedRow();
            if (selectedRow >= 0) {
                int employeeId = (int) employeeTable.getValueAt(selectedRow, 0); // Assuming ID is in the first column
                String lastname = (String) employeeTable.getValueAt(selectedRow, 1);
                String firstname = (String) employeeTable.getValueAt(selectedRow, 2);
                String role = (String) employeeTable.getValueAt(selectedRow, 3); // Assuming Role is in the fourth column
                EditDialog dialog = new EditDialog();
                dialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Please select an employee");
            }
        });
        buttonPanel.add(editButton);




        // DELETE
        JButton deleteButton = new JButton("Supprimer");
        deleteButton.addActionListener(e -> {
            DeleteDialog dialog = new DeleteDialog();
            dialog.setVisible(true);
        });
        buttonPanel.add(deleteButton);

        // DEPARTMENT
        JButton departmentButton = new JButton("Département");
        departmentButton.addActionListener(e -> {
            DepartmentUI departmentUI = new DepartmentUI();
            departmentUI.setVisible(true);
        });
        buttonPanel.add(departmentButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

}
