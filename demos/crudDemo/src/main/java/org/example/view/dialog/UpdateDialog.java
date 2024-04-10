package org.example.view.dialog;

import org.example.dao.ContactDao;
import org.example.model.Contact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateDialog extends JDialog{

    private JPanel inputPanel;
    private JLabel labId, labName, labNumber;
    private JTextField jtfId, jtfName, jtfNumber;


    public UpdateDialog() {
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ContactDao contactDao = new ContactDao();
                int id;
                try {
                    id = Integer.parseInt(jtfId.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid integer ID");
                    return;
                }

                if (jtfId.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter an ID");
                    return;
                }

                Contact contact = contactDao.searchContact(id);
                if (contact != null) {
                    jtfName.setText(contact.getName());
                    jtfNumber.setText(contact.getNumber());
                    jtfName.setEnabled(true);
                    jtfNumber.setEnabled(true);
                    labName.setEnabled(true);
                    labNumber.setEnabled(true);
                    jtfId.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Record not found for given ID !");
                }
            }
        });


        JButton updateButton = new JButton("UPDATE");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    Contact contact=new Contact();
                    contact.setId(Integer.parseInt(jtfId.getText()));
                    contact.setName(jtfName.getText());
                    contact.setNumber(jtfNumber.getText());
                    ContactDao contactDao = new ContactDao();
                    int count= contactDao.update(contact);
                    if(count > 0) {
                        JOptionPane.showMessageDialog(null, "Record updated successfully !");
                    }else{
                        JOptionPane.showMessageDialog(null, "Record can't be updated !");
                    }
                    dispose();
                }
            });



        JButton cancelButton = new JButton("CANCEL");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 10); // Padding

        gbc.gridy++;
        labId = new JLabel("ID");
        labId.setFont(new Font("Calibri Light", Font.PLAIN, 14));
        labId.setForeground(Color.black);
        inputPanel.add(labId, gbc);

        gbc.gridy++;
        jtfId = new JTextField(15);
        inputPanel.add(jtfId, gbc);

        gbc.gridy++;
        inputPanel.add(searchButton, gbc);


        gbc.gridy++;
        labName = new JLabel("Name");
        labName.setFont(new Font("Calibri Light", Font.PLAIN, 14));
        labName.setForeground(Color.black);
        inputPanel.add(labName, gbc);

        gbc.gridy++;
        jtfName = new JTextField(15);
        inputPanel.add(jtfName, gbc);

        gbc.gridy++;
        labNumber = new JLabel("Number");
        labNumber.setFont(new Font("Calibri Light", Font.PLAIN, 14));
        labNumber.setForeground(Color.black);
        inputPanel.add(labNumber, gbc);

        gbc.gridy++;
        jtfNumber = new JTextField(15);
        inputPanel.add(jtfNumber, gbc);

        gbc.gridy++;
        inputPanel.add(updateButton, gbc);

        gbc.gridx++;
        inputPanel.add(cancelButton, gbc);


        setContentPane(inputPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
