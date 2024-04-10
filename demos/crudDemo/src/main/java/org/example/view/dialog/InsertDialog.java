package org.example.view.dialog;

import lombok.Data;
import org.example.dao.ContactDao;
import org.example.model.Contact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Data
public class InsertDialog extends JDialog {

    private JPanel inputPanel;
    private JLabel labName, labNumber;
    private JTextField jtfName, jtfNumber;

    public InsertDialog() {
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());

        JButton addButton = new JButton("ADD");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Contact contact = new Contact();
                contact.setName(jtfName.getText());
                contact.setNumber(jtfNumber.getText());

                ContactDao contactDao = new ContactDao();
                    int count = contactDao.addContact(contact);
                    if (count > 0) {
                        JOptionPane.showMessageDialog(null, "Add successfully !");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error !");
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
        inputPanel.add(addButton, gbc);

        gbc.gridx++;
        inputPanel.add(cancelButton, gbc);

        setContentPane(inputPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}