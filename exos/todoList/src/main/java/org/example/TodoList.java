package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.HashMap;
import java.util.Map;

public class TodoList extends JFrame {

    private final JTextField jtfTask;
    private JButton addButton, deleteButton, completeButton;
    private final DefaultListModel<String> listModel;
    private JList<String> taskList;

    public TodoList() {
        setVisible(true);
        setSize(500, 580);
        setTitle("TodoList");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        jtfTask = new JTextField();
        mainPanel.add(jtfTask, BorderLayout.NORTH);

        // Initialize the taskList
        taskList = new JList<>();
        listModel = new DefaultListModel<>();
        taskList.setModel(listModel);

        // Panel for tasks
        JPanel taskListPanel = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(taskList);
        taskListPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(taskListPanel, BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Use FlowLayout for buttonPanel

        // Initialize the buttons
        addButton = new JButton();
        addButton.setBackground(Color.WHITE);
        deleteButton = new JButton();
        deleteButton.setBackground(Color.WHITE);
        completeButton = new JButton();
        completeButton.setBackground(Color.WHITE);

        // Resize icons for each button
        resizeIcon(addButton, "src/plus.jpg", 25, 25);
        resizeIcon(deleteButton, "src/trash.jpg", 25, 25);
        resizeIcon(completeButton, "src/v-icon.jpg", 25, 25);

        // Add buttons to the buttonPanel
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(completeButton);


        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);


        // Action listener for Add button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = jtfTask.getText();

                if (!task.isEmpty()) {
                    listModel.addElement(task);
                    jtfTask.setText("");
                } else {
                    JOptionPane.showMessageDialog(TodoList.this, "Please select a task.");
                }
            }
        });

        // Action listener for Delete button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTask = taskList.getSelectedValue();

                if (selectedTask != null) {
                    listModel.removeElement(selectedTask);
                } else {
                    JOptionPane.showMessageDialog(TodoList.this, "Please select a task.");
                }
            }
        });

        // Action listener for Complete button

        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();

                if (selectedIndex != -1) {
                    String selectedTask = listModel.getElementAt(selectedIndex);

                    String htmlText = "<html><strike>" + selectedTask + "</strike></html>";

                    listModel.setElementAt(htmlText, selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(TodoList.this, "Please select a task.");
                }
            }
        });

    }

    public void resizeIcon(JButton button, String path, int height, int width) {
        ImageIcon icon = new ImageIcon(path); // Load the icon from the specified path
        Image originalImage = icon.getImage();
        // Resize the image
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        // Create a new ImageIcon with the scaled image
        Icon scaledIcon = new ImageIcon(scaledImage);
        // Set the scaled icon for the button
        button.setIcon(scaledIcon);
    }


}
