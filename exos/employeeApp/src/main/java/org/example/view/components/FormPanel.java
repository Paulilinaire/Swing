package org.example.view.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DateFormatter;

import org.example.dao.EmployeeDao;
import org.example.model.Employee;
import org.jdatepicker.impl.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class FormPanel extends JPanel {
    private JTextField jtfEmployeeId, jtfName, jtfAge, jtfBloodGroup, jtfNumber, jtfAddress, jtfImagePath;
    private JButton uploadImage;
    private JRadioButton radioButtonMale, radioButtonFemale;
    private ButtonGroup genderGroup;
    private JLabel imageLabel;
    private EmployeeDao employeeDao;


    public FormPanel() {
        TitledBorder tableBorder = BorderFactory.createTitledBorder("Form");
        setBorder(tableBorder);

        setBounds(100, 100, 450, 550);
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(5, 10, 5, 10));
        setLayout(null);

        // Inputs
        jtfEmployeeId = new JTextField();
        jtfEmployeeId.setBounds(90, 20, 170, 20);
        add(jtfEmployeeId);

        jtfName = new JTextField();
        jtfName.setBounds(90, 50, 170, 20);
        add(jtfName);

        JPanel genderPanel = new JPanel();
        genderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        radioButtonMale = new JRadioButton("Male");
        radioButtonFemale = new JRadioButton("Female");

        genderGroup = new ButtonGroup();
        genderGroup.add(radioButtonMale);
        genderGroup.add(radioButtonFemale);

        genderPanel.add(radioButtonMale);
        genderPanel.add(radioButtonFemale);
        genderPanel.setBounds(90, 80, 170, 30);
        add(genderPanel);

        jtfAge = new JTextField();
        jtfAge.setBounds(90, 110, 170, 20);
        add(jtfAge);

        jtfBloodGroup = new JTextField();
        jtfBloodGroup.setBounds(90, 140, 170, 20);
        add(jtfBloodGroup);

        jtfNumber = new JTextField();
        jtfNumber.setBounds(90, 170, 170, 20);
        add(jtfNumber);

        JComboBox<String> qualificationList = new JComboBox<>(new String[]{"DOCTORAL", "MASTER", "BACHELOR", "ASSOCIATE"});
        qualificationList.setBounds(90, 200, 170, 20);
        add(qualificationList);

        // Display Image
        imageLabel = new JLabel();
        imageLabel.setBounds(670, 20, 210, 240);
        add(imageLabel);

        // Date Picker
        UtilDateModel model = new UtilDateModel();
        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
        JFormattedTextField.AbstractFormatter formatter = new DateFormatter(new SimpleDateFormat("yyyy-MM-dd"));
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, formatter);
        model.setDate(2024, 4, 12);
        datePicker.setBounds(90, 240, 170, 30);
        add(datePicker);

        jtfAddress = new JTextField();
        jtfAddress.setBounds(380, 20, 230, 110);
        add(jtfAddress);

        uploadImage = new JButton("Upload Image");
        uploadImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int returnVal = chooser.showOpenDialog(FormPanel.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    try {
                        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                        Image scaledImage = icon.getImage().getScaledInstance(210, 240, Image.SCALE_SMOOTH);
                        ImageIcon scaledIcon = new ImageIcon(scaledImage);
                        imageLabel.setIcon(scaledIcon);
                        jtfImagePath.setText(file.getAbsolutePath());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(FormPanel.this, "Error loading image", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        uploadImage.setBounds(380, 150, 230, 25);
        add(uploadImage);

        jtfImagePath = new JTextField();
        jtfImagePath.setEditable(false);
        jtfImagePath.setBounds(380, 190, 230, 25);
        add(jtfImagePath);


        // Labels
        JLabel labelEmployeeId = new JLabel("EmployeeId");
        labelEmployeeId.setBounds(10, 20, 80, 15);
        add(labelEmployeeId);

        JLabel labelName = new JLabel("Name");
        labelName.setBounds(10, 50, 80, 15);
        add(labelName);

        JLabel labelGender = new JLabel("Gender");
        labelGender.setBounds(10, 80, 50, 30);
        add(labelGender);

        JLabel labelAge = new JLabel("Age");
        labelAge.setBounds(10, 110, 80, 15);
        add(labelAge);

        JLabel labelBloodGroup = new JLabel("BloodGroup");
        labelBloodGroup.setBounds(10, 140, 80, 15);
        add(labelBloodGroup);

        JLabel labelNumber = new JLabel("ContactNo");
        labelNumber.setBounds(10, 170, 80, 15);
        add(labelNumber);

        JLabel labelQualification = new JLabel("Qualification");
        labelQualification.setBounds(10, 200, 80, 15);
        add(labelQualification);

        JLabel labelStartDate = new JLabel("Start date");
        labelStartDate.setBounds(10, 240, 80, 15);
        add(labelStartDate);

        JLabel labelAddress = new JLabel("Address");
        labelAddress.setBounds(310, 20, 80, 15);
        add(labelAddress);

        JLabel labelImage = new JLabel("Image Path");
        labelImage.setBounds(310, 190, 80, 15);
        add(labelImage);


        setVisible(true);

    }
}

