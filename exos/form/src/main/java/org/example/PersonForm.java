package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonForm extends JFrame {

    private JLabel labTitle, labName, labEmail, labGender;
    private JTextField jtfName, jtfEmail;
    private JRadioButton radioButtonWoman, radioButtonMan, radioButtonNonBinary;
    private JButton addButton, detailsButton;
    private JTable userTable;
    private DefaultTableModel tableModel;
    private ButtonGroup genderGroup;

    public PersonForm() {
        setVisible(true);
        setSize(500, 580);
        setTitle("User Form");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel for input fields and buttons
        JPanel inputPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 10); // Padding

        labTitle = new JLabel("Registration Form");
        labTitle.setFont(new Font("Calibri Light", Font.BOLD, 20));
        labTitle.setHorizontalAlignment(SwingConstants.LEFT);
        inputPanel.add(labTitle, gbc);

        gbc.gridy++;
        labName = new JLabel("Name");
        labName.setFont(new Font("Calibri Light", Font.PLAIN, 14));
        labName.setForeground(Color.black);
        inputPanel.add(labName, gbc);

        gbc.gridy++;
        jtfName = new JTextField(15);
        inputPanel.add(jtfName, gbc);

        gbc.gridy++;
        labEmail = new JLabel("Email");
        labEmail.setFont(new Font("Calibri Light", Font.PLAIN, 14));
        labEmail.setForeground(Color.black);
        inputPanel.add(labEmail, gbc);

        gbc.gridy++;
        jtfEmail = new JTextField(15);
        inputPanel.add(jtfEmail, gbc);

        gbc.gridy++;
        labGender = new JLabel("Gender");
        labGender.setFont(new Font("Calibri Light", Font.PLAIN, 14));
        labGender.setForeground(Color.black);
        inputPanel.add(labGender, gbc);

        // Panel for gender
        gbc.gridy++;
        JPanel genderPanel = new JPanel();
        genderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        radioButtonWoman = new JRadioButton("Woman");
        radioButtonWoman.setFont(new Font("Calibri Light", Font.PLAIN, 14));
        radioButtonMan = new JRadioButton("Man");
        radioButtonMan.setFont(new Font("Calibri Light", Font.PLAIN, 14));
        radioButtonNonBinary = new JRadioButton("Non-binary");
        radioButtonNonBinary.setFont(new Font("Calibri Light", Font.PLAIN, 14));

        genderGroup = new ButtonGroup();
        genderGroup.add(radioButtonWoman);
        genderGroup.add(radioButtonMan);
        genderGroup.add(radioButtonNonBinary);
        genderPanel.add(radioButtonWoman);
        genderPanel.add(radioButtonMan);
        genderPanel.add(radioButtonNonBinary);
        inputPanel.add(genderPanel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
        addButton = new JButton("ADD");
        addButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        addButton.setBackground(Color.white);
        inputPanel.add(addButton, gbc);

        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // Panel for user table
        JPanel userTablePanel = new JPanel(new BorderLayout());
        String[] columnNames = {"Name", "Email", "Gender"};
        tableModel = new DefaultTableModel(columnNames, 0);
        userTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);
        userTablePanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(userTablePanel, BorderLayout.CENTER);

        // Details button
        detailsButton = new JButton("DETAILS");
        detailsButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        detailsButton.setBackground(Color.white);
        mainPanel.add(detailsButton, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);

        // Action listener for Add button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = jtfName.getText();
                String email = jtfEmail.getText();
                String gender = "";

                if (radioButtonWoman.isSelected()) {
                    gender = "Woman";
                } else if (radioButtonMan.isSelected()) {
                    gender = "Man";
                } else if (radioButtonNonBinary.isSelected()) {
                    gender = "Non-binary";
                }

                if (!name.isEmpty() && !email.isEmpty() && !gender.isEmpty()) {
                    tableModel.addRow(new Object[]{name, email, gender});
                    jtfName.setText("");
                    jtfEmail.setText("");
                    // Clear the selection of radio buttons after adding the user
                    genderGroup.clearSelection();
                } else {
                    JOptionPane.showMessageDialog(PersonForm.this, "Please enter name, email, and select gender.");
                }
            }
        });

        // Action listener for Details button
        detailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = userTable.getSelectedRow();
                if (selectedRow != -1) {
                    String name = (String) userTable.getValueAt(selectedRow, 0);
                    String email = (String) userTable.getValueAt(selectedRow, 1);
                    String gender = (String) userTable.getValueAt(selectedRow, 2);
                    JOptionPane.showMessageDialog(PersonForm.this, "Name: " + name + "\nEmail: " + email + "\nGender: " + gender, "User Details", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(PersonForm.this, "Please select a user");
                }
            }
        });
    }

}
