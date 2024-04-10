package org.example.view.dialog;

import org.example.dao.ContactDao;
import org.example.model.Contact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteDialog extends JDialog {

    private JPanel inputPanel;
    private JLabel labId;
    private JTextField jtfId;

    public DeleteDialog(){
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());


        JButton deleteButton = new JButton("DELETE");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id=Integer.parseInt(jtfId.getText());
                ContactDao contactDao=new ContactDao();
                int count = contactDao.delete(id);
                System.out.println("Result : "+count);
                if(count>0)
                {
                    JOptionPane.showMessageDialog(null, "Record deleted successfully ");
                }else{
                    JOptionPane.showMessageDialog(null, "Record can't be deleted !");
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
        inputPanel.add(deleteButton, gbc);

        gbc.gridx++;
        inputPanel.add(cancelButton, gbc);


        setContentPane(inputPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

}
