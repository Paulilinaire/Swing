package org.example.view;

import org.example.dao.ContactDao;
import org.example.view.dialog.DeleteDialog;
import org.example.view.dialog.InsertDialog;
import org.example.view.dialog.UpdateDialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JPanel jPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setTitle("Main Frame");
                mainFrame.setSize(425, 200);
                mainFrame.setLocationRelativeTo(null);
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public MainFrame() {
        jPanel = new JPanel();
        jPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(jPanel);
        jPanel.setLayout(null);

        JButton btnInsert = new JButton("Insert");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnSelect = new JButton("Select");

        // Adding action listeners to buttons
        btnInsert.addActionListener(e -> {
            InsertDialog dialog = new InsertDialog();
            dialog.setLocationRelativeTo(jPanel);
            dialog.setVisible(true);
        });

        btnUpdate.addActionListener(e -> {
            UpdateDialog dialog = new UpdateDialog();
            dialog.setLocationRelativeTo(jPanel);
            dialog.setVisible(true);
        });

        btnDelete.addActionListener(e -> {
            DeleteDialog dialog = new DeleteDialog();
            dialog.setLocationRelativeTo(jPanel);
            dialog.setVisible(true);
        });

        btnSelect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = new DefaultTableModel();
                JTable table = new JTable(tableModel);
                ContactDao contactDao = new ContactDao();
                contactDao.getAll(tableModel);
                JOptionPane.showMessageDialog(null, new JScrollPane(table));
            }
        });

        btnInsert.setBounds(36,23, 100,25);
        btnUpdate.setBounds(156,23, 100,25);
        btnDelete.setBounds(276,23, 100,25);
        btnSelect.setBounds(156,73, 100,25);

        jPanel.add(btnInsert);
        jPanel.add(btnUpdate);
        jPanel.add(btnDelete);
        jPanel.add(btnSelect);


    }
}
