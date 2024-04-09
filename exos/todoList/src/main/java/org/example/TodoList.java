package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
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

        ImageIcon icon = new ImageIcon("src/plus.jpg");
        Image originalImage = icon.getImage();
        // Resize the image
        Image scaledImage = originalImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        // Create a new ImageIcon with the scaled image
        Icon scaledIcon = new ImageIcon(scaledImage);
        // Create the JButton with the scaled icon
        addButton = new JButton(scaledIcon);
        addButton.setBackground(Color.WHITE);
        buttonPanel.add(addButton);

        icon = new ImageIcon("src/trash.jpg");
        Image originalTrash = icon.getImage();
        Image scaledTrash = originalTrash.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        Icon scaledDelete = new ImageIcon(scaledTrash);
        deleteButton = new JButton(scaledDelete);
        deleteButton.setBackground(Color.WHITE);
        buttonPanel.add(deleteButton);

        icon = new ImageIcon("src/v-icon.jpg");
        Image originalV = icon.getImage();
        Image scaledV = originalV.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        Icon scaledComplete = new ImageIcon(scaledV);
        completeButton = new JButton(scaledComplete);
        completeButton.setBackground(Color.WHITE);
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
                String selectedTask = taskList.getSelectedValue();

                if (selectedTask != null) {
                    int selectedIndex = taskList.getSelectedIndex();

                    Font font = taskList.getFont();
                    Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
                    attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
                    Font strikethroughFont = new Font(attributes);
                    taskList.setFont(strikethroughFont);

                    listModel.setElementAt(selectedTask, selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(TodoList.this, "Please select a task.");
                }
            }
        });

    }
}
