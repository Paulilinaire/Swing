package org.example.view.dialog;

import org.example.dao.EmployeeDao;
import org.example.model.Employee;
import org.example.model.Role;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditDialog extends JDialog {
    private JPanel contentPanel;
    private JTextField txtLastName, txtFirstname;
    private JComboBox<String> roleList;
    private JComboBox<String> employeeList;

    public EditDialog() {
        contentPanel = new JPanel();
        setTitle("Edit employee");
        setBounds(100, 100, 350, 220);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        txtLastName = new JTextField();
        txtLastName.setBounds(100, 20, 150, 20);
        contentPanel.add(txtLastName);

        txtFirstname = new JTextField();
        txtFirstname.setBounds(100, 50, 150, 20);
        contentPanel.add(txtFirstname);

        JLabel labelFirstname = new JLabel("First name");
        labelFirstname.setBounds(10, 20, 80, 15);
        contentPanel.add(labelFirstname);

        JLabel labelLastname = new JLabel("Last name");
        labelLastname.setBounds(10, 50, 80, 15);
        contentPanel.add(labelLastname);

        JLabel labelRole = new JLabel("Role");
        labelRole.setBounds(10, 80, 50, 20);
        contentPanel.add(labelRole);

        String[] roles = { "Manager", "Employee", "Intern" };
        roleList = new JComboBox<>(roles);
        roleList.setBounds(100, 80, 150, 30);
        contentPanel.add(roleList);

        JPanel jPanelButton = new JPanel();
        jPanelButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(jPanelButton, BorderLayout.SOUTH);

        JButton jButtonOK = new JButton("OK");
        jButtonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee employee = new Employee();
                employee.setLastname(txtLastName.getText());
                employee.setFirstname(txtFirstname.getText());
                String selectedRole = (String) roleList.getSelectedItem();
                employee.setRole(Role.valueOf(selectedRole.toUpperCase()));

                EmployeeDao employeeDao = new EmployeeDao();
                int count = employeeDao.update(employee);
                if (count > 0) {
                    JOptionPane.showMessageDialog(null, "Employee updated with success !");
                } else {
                    JOptionPane.showMessageDialog(null, "Error updating employee !");
                }
                dispose();
            }
        });
        jPanelButton.add(jButtonOK);

        JButton jButtonCancel = new JButton("Cancel");
        jButtonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        jPanelButton.add(jButtonCancel);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
