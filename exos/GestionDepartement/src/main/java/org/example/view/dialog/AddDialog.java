package org.example.view.dialog;

import org.example.dao.DepartmentDao;
import org.example.dao.EmployeeDao;
import org.example.model.Department;
import org.example.model.Employee;
import org.example.model.Role;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddDialog extends JDialog {

    private JPanel contentPanel;
    private JTextField txtLastName, txtFirstname;
    private JRadioButton radioButtonManager, radioButtonEmployee, radioButtonIntern;
    private ButtonGroup roleGroup;
    private JComboBox<String> departmentList;


    public AddDialog(){
        contentPanel = new JPanel();
        setTitle("Add employee");
        setBounds(100,100, 350,220);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5,5,5,5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        txtLastName = new JTextField();
        txtLastName.setBounds(80, 20,150, 20);
        contentPanel.add(txtLastName);

        txtFirstname = new JTextField();
        txtFirstname.setBounds(80, 50,150, 20);
        contentPanel.add(txtFirstname);

        JPanel rolePanel = new JPanel();
        rolePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        radioButtonManager = new JRadioButton("Manager");
        radioButtonEmployee = new JRadioButton("Employee");
        radioButtonIntern = new JRadioButton("Intern");

        roleGroup = new ButtonGroup();
        roleGroup.add(radioButtonManager);
        roleGroup.add(radioButtonEmployee);
        roleGroup.add(radioButtonIntern);

        rolePanel.add(radioButtonManager);
        rolePanel.add(radioButtonEmployee);
        rolePanel.add(radioButtonIntern);
        rolePanel.setBounds(80, 80, 300, 30);
        contentPanel.add(rolePanel);

        DepartmentDao departmentDao = new DepartmentDao();
        List<Department> departments = departmentDao.getAll();
        String[] departmentNames = departments.stream().map(Department::getName).toArray(String[]::new);
        departmentList = new JComboBox<>(departmentNames);
        departmentList.setBounds(90, 110, 150, 20);
        contentPanel.add(departmentList);

        JLabel labelFirstname = new JLabel("First name");
        labelFirstname.setBounds(10,20,80,15);
        contentPanel.add(labelFirstname);

        JLabel labelLastname = new JLabel("Last name");
        labelLastname.setBounds(10,50,80,15);
        contentPanel.add(labelLastname);

        JLabel labelRole = new JLabel("Role");
        labelRole.setBounds(10,80,50,30);
        contentPanel.add(labelRole);

        JLabel labelDepartment = new JLabel("Department");
        labelDepartment.setBounds(10,110,80,15);
        contentPanel.add(labelDepartment);

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
                int selectedDepartmentIndex = departmentList.getSelectedIndex();
                if (selectedDepartmentIndex != -1) {
                    Department selectedDepartment = departments.get(selectedDepartmentIndex);
                    employee.setDepartment(selectedDepartment);
                } else {
                    System.out.println("No department selected");
                }

                String selectedRole = "";
                if (radioButtonManager.isSelected()) {
                    selectedRole = "MANAGER";
                } else if (radioButtonEmployee.isSelected()) {
                    selectedRole = "EMPLOYEE";
                } else if (radioButtonIntern.isSelected()) {
                    selectedRole = "INTERN";
                }
                employee.setRole(Role.valueOf(selectedRole));

                EmployeeDao employeeDao = new EmployeeDao();
                int count = employeeDao.save(employee);
                if(count>0){
                    JOptionPane.showMessageDialog(null, "Employee added with success !");
                }else{
                    JOptionPane.showMessageDialog(null, "Error !");
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
